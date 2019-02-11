from __future__ import unicode_literals
from django.db import models
from django.db import models

# Create your models here.




class AirportIndex(models.Model):
	airport = models.TextField()
	latitude = models.FloatField()
	longitude = models.FloatField()
	iata = models.TextField()

	class Meta:
		managed=False
		db_table="airport_index"

class UserTravel(models.Model):
	#id =models.IntegerField(primary_key=True)
	email = models.TextField(primary_key=True)
	password = models.TextField()
	dob = models.DateField()

	class Meta:
		managed = False
		db_table="user_travel"

class SearchDetails(models.Model):
	id = models.IntegerField(primary_key=True)
	car_carrier = models.TextField()
	flight_carrier = models.TextField()
	total_price = models.FloatField()
	car_source = models.TextField()
	car_destination = models.TextField()
	flight_source = models.TextField()
	flight_destination = models.TextField()
	date = models.TextField()
	search_id = models.TextField()
	car_source_price = models.FloatField()
	car_destination_price = models.FloatField()

	class Meta:
		managed=False
		db_table = "search_details"