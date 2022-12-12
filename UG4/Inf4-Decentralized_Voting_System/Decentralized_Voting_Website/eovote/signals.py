from django.conf import settings
from django.dispatch import receiver
from django.db.models.signals import post_save
from django.core.mail import send_mail
from django.contrib.sites.shortcuts import get_current_site
import qrcode

from .models import EoVoterUser, EoCredentialUser
from vote.models import EaVoteUser


def gen_img(instance):
    print(instance.get_callback_data())
    img = qrcode.make(instance.get_callback_data())

    filename = str(instance.userid) + '.png'
    path = settings.MEDIA_ROOT / filename
    with open(path, 'wb') as f:
        img.save(f)
    return filename


@receiver(post_save, sender=EoVoterUser)
@receiver(post_save, sender=EaVoteUser)
def send_email(instance, created,  **kwargs):
    if created:
        filename = gen_img(instance)
        site = get_current_site(None)
        url = 'http://{}/media/{}'.format(site.domain, filename)
        content = '<img src="{}" alt="" style="width: 500px;height: 500px">'.format(url)
        try:
            send_mail(instance.get_title(), message=content, html_message=content, recipient_list=[instance.email],
                      from_email=settings.EMAIL_HOST_USER)
        except Exception as e:
            print(e)
