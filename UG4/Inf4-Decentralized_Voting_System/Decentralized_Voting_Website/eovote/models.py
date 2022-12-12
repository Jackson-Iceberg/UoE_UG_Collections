import uuid

from django.contrib.sites.shortcuts import get_current_site
from django.urls import reverse_lazy
from django.db import models
from django.contrib.auth.views import get_user_model

# Create your models here.


class EoElection(models.Model):
    title = models.CharField(max_length=255)
    credential_period_from = models.DateTimeField()
    credential_period_to = models.DateTimeField()
    casting_period_from = models.DateTimeField()
    casting_period_to = models.DateTimeField()
    tallying_period = models.DateTimeField()
    voter_list = models.FileField()
    candidates_list = models.FileField()
    pub_user = models.ForeignKey(get_user_model(), on_delete=models.CASCADE)

    def __str__(self):
        return self.title


class EoVoterUser(models.Model):
    name = models.CharField(max_length=255)
    email = models.EmailField()
    public_key = models.CharField(max_length=255)
    userid = models.UUIDField(primary_key=True, auto_created=True, default=uuid.uuid4,
                              editable=False)

    eo_election = models.ForeignKey(EoElection, on_delete=models.CASCADE)

    def __str__(self):
        return self.name

    def get_callback_data(self):
        url = get_current_site(None)
        print(self.eo_election.eocredentialuser_set.all())
        candidates = [user.name for user in self.eo_election.eocredentialuser_set.all()]
        return {
            'url': '{}://{}{}?userid={}&type={}'.format('http', url.domain, reverse_lazy('vote_callback'), self.userid, 2),
            'credential_period_from': self.eo_election.credential_period_from.strftime('%Y-%m-%d %H:%M:%S'),
            'credential_period_to': self.eo_election.credential_period_to.strftime('%Y-%m-%d %H:%M:%S'),
            'title': self.eo_election.title,
            'casting_period_from': self.eo_election.casting_period_from.strftime('%Y-%m-%d %H:%M:%S'),
            'casting_period_to': self.eo_election.casting_period_to.strftime('%Y-%m-%d %H:%M:%S'),
            'tallying_period': self.eo_election.tallying_period.strftime('%Y-%m-%d %H:%M:%S'),
            'candidate': candidates
        }

    def get_title(self):
        return self.eo_election.title


class EoCredentialUser(models.Model):
    name = models.CharField(max_length=255)
    userid = models.UUIDField(primary_key=True, auto_created=True, default=uuid.uuid4,
                              editable=False)

    eo_election = models.ForeignKey(EoElection, on_delete=models.CASCADE)

    def __str__(self):
        return self.name

    def get_callback_data(self):
        url = get_current_site(None)
        candidates = [user.name for user in self.eo_election.eocredentialuser_set.all()]
        return {
            'url': '{}://{}{}?userid={}&type={}'.format('http', url.domain, reverse_lazy('vote_callback'), self.userid,
                                                        2),
            'credential_period_from': self.eo_election.credential_period_from.strftime('%Y-%m-%d %H:%M:%S'),
            'credential_period_to': self.eo_election.credential_period_to.strftime('%Y-%m-%d %H:%M:%S'),
            'title': self.eo_election.title,
            'casting_period_from': self.eo_election.casting_period_from.strftime('%Y-%m-%d %H:%M:%S'),
            'casting_period_to': self.eo_election.casting_period_to.strftime('%Y-%m-%d %H:%M:%S'),
            'tallying_period': self.eo_election.tallying_period.strftime('%Y-%m-%d %H:%M:%S'),
            'candidate': candidates
        }

    def get_title(self):
        return self.eo_election.title
