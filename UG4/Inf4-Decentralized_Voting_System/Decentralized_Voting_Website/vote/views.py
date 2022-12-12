import csv

from django.http import HttpResponse
from django.shortcuts import render, redirect, get_object_or_404
from django.views.generic.base import TemplateView, View
from rest_framework.views import APIView
from rest_framework.response import Response
from rest_framework.generics import ListAPIView, RetrieveAPIView
from django.contrib.auth.mixins import LoginRequiredMixin
from django.contrib.auth.decorators import login_required

from utils.auth import get_role
from .serializers import SetElectionRegistrationSerializer, ElectionRegistrationListSerializer, ElectionRegistrationDetailSerializer
from .models import EaVoteUser, EaElection
from eovote.models import EoVoterUser, EoCredentialUser


# Create your views here.


@login_required
def role_dispatch(request):
    role = get_role(request.user)
    if role == 'ea':
        return redirect('ea-index')
    elif role == 'eo':
        return redirect('eo:index')
    return HttpResponse('No Role')


class EaIndexView(LoginRequiredMixin, TemplateView):
    template_name = 'vote/ea-index.html'


class SetElectionRegistration(LoginRequiredMixin, TemplateView):
    template_name = 'vote/set-election-registration.html'


class EaListTemplateView(LoginRequiredMixin, TemplateView):
    template_name = 'vote/ea-list.html'


class EaDetailTemplateView(LoginRequiredMixin, TemplateView):
    template_name = 'vote/ea-detail.html'

    def get_context_data(self, **kwargs):
        context = super(EaDetailTemplateView, self).get_context_data(**kwargs)

        context['pk'] = self.kwargs.get('pk')

        return context


class SetElectionRegistrationAPIView(APIView):
    def post(self, request, *args, **kwargs):
        ser = SetElectionRegistrationSerializer(data=request.data)
        if ser.is_valid():

            if EaElection.objects.filter(pub_user=request.user).exists():
                return Response(data={
                    'code': 1,
                    'msg': 'Election Already Existed',
                    'data': {}
                })

            instance = ser.save(pub_user=request.user)
            instance.pub_user = request.user

            with instance.voter_list.open('r') as f:
                rows = csv.reader(f)
                rows = iter(rows)
                next(rows)  # 跳过表头
                for row in rows:
                    EaVoteUser.objects.create(email=row[1], name=row[0], ea_election=instance)

            return Response(data={
                'code': 0,
                'msg': 'Success',
                'data': {}
            })
        return Response(data=ser.errors)


class SetElectionRegistrationListAPIView(ListAPIView):
    serializer_class = ElectionRegistrationListSerializer
    queryset = EaElection.objects.all()

    def get_queryset(self):
        queryset = super(SetElectionRegistrationListAPIView, self).get_queryset()
        return queryset.filter(pub_user=self.request.user)


class EaDetailAPIView(RetrieveAPIView):
    serializer_class = ElectionRegistrationDetailSerializer
    queryset = EaElection.objects.all()


class ContactTemplateView(TemplateView):
    template_name = 'vote/contact.html'


class HelpTemplateView(TemplateView):
    template_name = 'vote/help.html'


class VoteUserCallback(APIView):
    def post(self, request, *args, **kwargs):
        type = request.GET.get('type')
        userid = request.GET.get('userid')
        if type == '1':
            model = EaVoteUser
        elif type == '2':
            model = EoVoterUser
        else:
            model = EoCredentialUser
        obj = get_object_or_404(model.objects.all(), userid=userid)
        if obj.public_key:
            return Response(data={
                'code': 0,
                'msg': 'Has been callback!',
                'data': {}
            })
        public_key = request.data.get('public_key')
        if not public_key:
            return Response(data={
                'code': 0,
                'msg': 'Public Key is required',
                'data': {}
            })

        obj.public_key = public_key
        obj.save()
        return Response(data={
            'code': 0,
            'data': {},
            'msg': 'success'
        })
