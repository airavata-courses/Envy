# -*- coding: utf-8 -*-
from __future__ import unicode_literals

from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ('PythonService', '0003_auto_20190206_0154'),
    ]

    operations = [
        migrations.AlterField(
            model_name='user_travel',
            name='email',
            field=models.CharField(max_length=255, serialize=False, primary_key=True),
        ),
        migrations.AlterField(
            model_name='user_travel',
            name='password',
            field=models.CharField(max_length=255),
        ),
    ]
