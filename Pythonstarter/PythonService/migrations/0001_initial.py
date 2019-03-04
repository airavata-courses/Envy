# -*- coding: utf-8 -*-
from __future__ import unicode_literals

from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
    ]

    operations = [
        migrations.CreateModel(
            name='Airport_index',
            fields=[
                ('id', models.AutoField(verbose_name='ID', serialize=False, auto_created=True, primary_key=True)),
                ('airport', models.TextField()),
                ('latitude', models.FloatField()),
                ('longitude', models.FloatField()),
                ('iata', models.TextField()),
            ],
        ),
    ]
