from rest_framework import serializers

from .models import EaElection, EaVoteUser


class SetElectionRegistrationSerializer(serializers.ModelSerializer):
    class Meta:
        model = EaElection
        fields = ['title', 'registration_from', 'registration_to', 'voter_list']


class ElectionRegistrationListSerializer(serializers.ModelSerializer):
    class Meta:
        model = EaElection
        fields = ['title', 'registration_from', 'registration_to', 'id']


class EaVoteUserSerializer(serializers.ModelSerializer):
    class Meta:
        model = EaVoteUser
        fields = ['public_key', 'email', 'name']


class ElectionRegistrationDetailSerializer(serializers.ModelSerializer):
    eavoteusers = EaVoteUserSerializer(source='eavoteuser_set', many=True)

    class Meta:
        model = EaElection
        fields = ['title', 'registration_from', 'registration_to', 'eavoteusers']
