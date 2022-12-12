import uuid

from django.db import models
from django.urls import reverse_lazy
from django.contrib.sites.shortcuts import get_current_site
from django.contrib.auth.views import get_user_model

# Create your models here.


class EaElection(models.Model):
    title = models.CharField(max_length=255)
    registration_from = models.DateTimeField()
    registration_to = models.DateTimeField()
    voter_list = models.FileField()
    created_time = models.DateTimeField(auto_now_add=True)

    pub_user = models.ForeignKey(get_user_model(), on_delete=models.CASCADE)

    def __str__(self):
        return self.title

    class Meta:
        verbose_name = 'Ea'
        verbose_name_plural = verbose_name


class EaVoteUser(models.Model):
    public_key = models.CharField(max_length=255, null=True, blank=True)
    email = models.EmailField()
    name = models.CharField(max_length=255)
    userid = models.UUIDField(primary_key=True, auto_created=True, default=uuid.uuid4,
                              editable=False)

    ea_election = models.ForeignKey(EaElection, on_delete=models.CASCADE)

    created_time = models.DateTimeField(auto_now_add=True)
    update_time = models.DateTimeField(auto_now=True)

    def __str__(self):
        return self.userid

    def get_callback_data(self):
        url = get_current_site(None)
        return {
            'url': '{}://{}{}?userid={}%type={}'.format('http', url.domain, reverse_lazy('vote_callback'), self.userid, 1),
            'registration_period_from': self.ea_election.registration_from.strftime('%Y-%m-%d %H:%M:%S'),
            'registration_period_to': self.ea_election.registration_to.strftime('%Y-%m-%d %H:%M:%S'),
            'title': self.ea_election.title
        }

    def get_title(self):
        return self.ea_election.title

    class Meta:
        verbose_name = 'EaUser'
        verbose_name_plural = verbose_name
