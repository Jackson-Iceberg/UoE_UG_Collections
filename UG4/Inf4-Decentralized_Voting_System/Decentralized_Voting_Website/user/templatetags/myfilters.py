from django.template import Library

from utils.auth import get_role


register = Library()


@register.filter
def role(user):
    return get_role(user)
