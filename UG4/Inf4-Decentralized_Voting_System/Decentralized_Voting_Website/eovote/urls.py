from django.urls import path

from . import views


app_name = 'eo'


urlpatterns = [
    path('index/', views.EoIndexTemplateView.as_view(), name='index'),
    path('startnewelection/', views.StartNewElection.as_view(), name='startnewelection'),
    path('startnewelectionapi/', views.StartNewElectionAPIView.as_view(), name='startnewelection-api'),
    path('viewexistelection/<slug:pk>/', views.ViewExistElection.as_view(), name='viewexistelection'),
    path('eolistapi/', views.ViewExistElectionListAPIView.as_view(), name='eo-listapi'),
    path('eolist/', views.EoListTemplateView.as_view(), name='eo-list')
]
