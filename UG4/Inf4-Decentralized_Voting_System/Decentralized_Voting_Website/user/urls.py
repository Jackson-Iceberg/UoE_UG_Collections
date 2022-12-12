from django.urls import path

from . import views


urlpatterns = [
    path('login/', views.LoginTemplateView.as_view(), name='login'),
    path('loginapi/', views.LoginAPIView.as_view(), name='login-api'),
    path('register/', views.RegisterTemplateView.as_view(), name='register'),
    path('registerapi/', views.RegisterAPIView.as_view(), name='register-api'),
    path('logout/', views.LogoutView.as_view(), name='logout')
]
