from rest_framework import serializers

from .models import EoElection


class EoElectionSerializer(serializers.ModelSerializer):
    class Meta:
        model = EoElection
        fields = ['title', 'credential_period_from', 'credential_period_to',
                  'casting_period_from', 'casting_period_to', 'tallying_period',
                  'voter_list', 'candidates_list']


class EoElectionListSerializer(serializers.ModelSerializer):
    class Meta:
        model = EoElection
        fields = ['title', 'credential_period_from', 'credential_period_to', 'id']
