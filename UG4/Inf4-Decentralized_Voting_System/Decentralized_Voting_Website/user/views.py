from django.shortcuts import render
from django.views.generic import TemplateView
from django.contrib.auth import authenticate, login
from rest_framework.views import APIView
from rest_framework.response import Response
from rest_framework.permissions import IsAuthenticated
from django.contrib.auth.views import get_user_model
from django.contrib.auth.models import Group
from django.contrib.auth.views import LogoutView

from .serializers import LoginSerializer, RegisterSerializser


# Create your views here.


class LoginTemplateView(TemplateView):
    template_name = 'auth/login.html'


class LoginAPIView(APIView):

    def post(self, request, *args, **kwargs):
        ser = LoginSerializer(data=request.data)
        if ser.is_valid():
            user = authenticate(**ser.validated_data)
            if user:
                login(request, user)
                return Response(data={
                    'code': 0,
                    'msg': '登录成功',
                    'data': {}
                })
            return Response(data={
                'code': 1,
                'msg': '用户名或密码错误',
                'data': {}
            })
        return Response(data=ser.errors)


class RegisterTemplateView(TemplateView):
    template_name = 'auth/register.html'


class RegisterAPIView(APIView):
    def post(self, request, *args, **kwargs):
        ser = RegisterSerializser(data=request.data)
        if ser.is_valid():
            data = ser.validated_data.copy()

            password1 = data.pop('password1')
            if password1 != data['password']:
                return Response(data={
                    'code': 1,
                    'msg': 'The password entered twice is incorrect',
                    'data': {}
                })

            role = data.pop('role')

            group, _ = Group.objects.get_or_create(name=role)

            try:
                get_user_model().objects.get(username=data['username'])
            except get_user_model().DoesNotExist:
                user = get_user_model().objects.create_user(**data)
                user.groups.add(group)
                return Response(data={
                    'code': 0,
                    'msg': '注册成功',
                    'data': {}
                })

            return Response(data={
                'code': 1,
                'msg': '用户名已存在',
                'data': {}
            })

        return Response(data=ser.errors)
