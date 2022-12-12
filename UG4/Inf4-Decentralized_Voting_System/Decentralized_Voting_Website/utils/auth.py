from django.contrib.auth.models import Group


def get_role(user):
    return user.groups.first().name if user.groups.exists() else None
