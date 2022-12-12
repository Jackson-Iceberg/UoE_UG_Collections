import csv

from django.shortcuts import render
from django.views.generic import TemplateView, DetailView
from rest_framework.generics import ListAPIView
from rest_framework.views import APIView
from rest_framework.response import Response
from django.contrib.auth.mixins import LoginRequiredMixin

from .serializers import EoElectionSerializer, EoElectionListSerializer
from .models import EoElection, EoVoterUser, EoCredentialUser
from . import signals

# Create your views here.


class EoIndexTemplateView(LoginRequiredMixin, TemplateView):
    template_name = 'eo/index.html'


class StartNewElection(LoginRequiredMixin, TemplateView):
    template_name = 'eo/startnewelection.html'


class StartNewElectionAPIView(APIView):
    def post(self, request, *args, **kwargs):
        ser = EoElectionSerializer(data=request.data)
        if ser.is_valid():

            instance = ser.save(pub_user=request.user)

            with instance.candidates_list.open('r') as f:
                rows = csv.reader(f)
                rows = iter(rows)
                next(rows)  # 跳过表头
                for row in rows:
                    EoCredentialUser.objects.create(name=row[0], eo_election=instance)

            with instance.voter_list.open('r') as f:
                rows = csv.reader(f)
                rows = iter(rows)
                next(rows)  # 跳过表头
                for row in rows:
                    EoVoterUser.objects.create(email=row[1], name=row[0], eo_election=instance)

            return Response(data={
                'code': 0,
                'msg': 'Success',
                'data': {}
            })
        return Response(data=ser.errors)


class EoListTemplateView(TemplateView):
    template_name = 'eo/eo-list.html'


class ViewExistElectionListAPIView(ListAPIView):
    queryset = EoElection.objects.all()
    serializer_class = EoElectionListSerializer

    def get_queryset(self):
        queryset = super(ViewExistElectionListAPIView, self).get_queryset()
        return queryset.filter(pub_user=self.request.user)


class ViewExistElection(LoginRequiredMixin, DetailView):
    template_name = 'eo/viewexistelection.html'
    model = EoElection
