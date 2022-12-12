from django.urls import path

from . import views


urlpatterns = [
    path('', views.role_dispatch, name='index'),
    path('ea/', views.EaIndexView.as_view(), name='ea-index'),
    path('ea/setelectionregistration/', views.SetElectionRegistration.as_view(), name='set-election-registration'),
    path('ea/setelectionregistrationapi/', views.SetElectionRegistrationAPIView.as_view(),
         name='set-election-registration-api'),
    path('ea/setelectionregistrationlist/', views.SetElectionRegistrationListAPIView.as_view(),
         name='election-registration-list-api'),
    path('ea/eadetail/<slug:pk>/', views.EaDetailTemplateView.as_view(), name='ea-detail'),
    path('ea/eadetailapi/<slug:pk>/', views.EaDetailAPIView.as_view(), name='ea-detail-api'),
    path('ea/ealist/', views.EaListTemplateView.as_view(), name='ea-list'),
    path('contact/', views.ContactTemplateView.as_view(), name='contact'),
    path('help/', views.HelpTemplateView.as_view(), name='help'),

    path('votecallback/', views.VoteUserCallback.as_view(), name='vote_callback')
]
