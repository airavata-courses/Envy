from .models import *
from .serializers import *

from rest_framework import viewsets

class UserTravelViewSet(viewsets.ModelViewSet):
	queryset = UserTravel.objects.all()
	serializer_class = UserTravelSerializer