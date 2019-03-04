# -*- coding: utf-8 -*-
from __future__ import unicode_literals

from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ('PythonService', '0005_auto_20190206_0209'),
    ]

    operations = [
        migrations.RenameModel(
            old_name='Airport_index',
            new_name='AirportIndex',
        ),
        migrations.RenameModel(
            old_name='User_Travel',
            new_name='UserTravel',
        ),
        migrations.AlterModelOptions(
            name='airportindex',
            options={'managed': True},
        ),
        migrations.AlterModelOptions(
            name='usertravel',
            options={'managed': True},
        ),
        migrations.AlterModelTable(
            name='airportindex',
            table='airport_index',
        ),
        migrations.AlterModelTable(
            name='usertravel',
            table='user_travel',
        ),
    ]
