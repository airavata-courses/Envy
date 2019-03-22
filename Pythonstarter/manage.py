#!/usr/bin/env python
import os
import sys
#import consul
#import requests

if __name__ == "__main__":
    os.environ.setdefault("DJANGO_SETTINGS_MODULE", "Pythonstarter.settings")

    from django.core.management import execute_from_command_line

   # c = consul.Consul()
   # f = requests.request('GET', 'http://myip.dnsomatic.com')
   # res = c.agent.service.register('pythonservice',address=f.content,port=8000)
    ''',check={
                "DeregisterCriticalServiceAfter": "3m",
                "http": "http://{}:{}/v1/health".format(f.content, 8500),
                "interval": "5s",
                "timeout": "1s"
                })'''

    execute_from_command_line(sys.argv)


