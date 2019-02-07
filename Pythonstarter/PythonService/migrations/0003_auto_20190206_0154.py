# -*- coding: utf-8 -*-
from __future__ import unicode_literals

from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ('PythonService', '0002_user_travel'),
    ]

    operations = [
        migrations.RemoveField(
            model_name='user_travel',
            name='id',
        ),
        migrations.AlterField(
            model_name='user_travel',
            name='email',
            field=models.TextField(serialize=False, primary_key=True),
        ),
    ]
