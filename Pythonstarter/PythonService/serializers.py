from rest_framework import serializers

from .models import UserTravel


class UserTravelSerializer(serializers.ModelSerializer):
    class Meta:
        model = UserTravel
        fields = '__all__'
        #fields = ('email', 'password', 'dob', )


