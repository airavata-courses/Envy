# -*- coding: utf-8 -*-
from __future__ import unicode_literals

from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ('PythonService', '0001_initial'),
    ]

    operations = [
        migrations.CreateModel(
            name='User_Travel',
            fields=[
                ('id', models.IntegerField(serialize=False, primary_key=True)),
                ('email', models.TextField()),
                ('password', models.TextField()),
                ('dob', models.DateField()),
            ],
        ),
    ]
