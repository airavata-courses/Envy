from django.shortcuts import render


from django.shortcuts import render
from django.http import Http404
from rest_framework.views import APIView
from rest_framework.decorators import api_view
from rest_framework.response import Response
from django.http import HttpResponse
from rest_framework import status
from django.http import JsonResponse
from django.core import serializers
from django.conf import settings
import json
from .models import *

from .serializers import UserTravelSerializer
from django.core.serializers import serialize
# Create your views here.

def pretty_json(partjson,totaljson):
	remaniningresponse = [ x for x in totaljson if x!=partjson[0]]
	response = {}
	initial = {}
	remaining_results = []
	
	for i in remaniningresponse:
		print(i)
		temp = i['fields']
		tempresult = {}
		tempresult['cab_origin_endpoint'] = temp['flight_source']
		tempresult['cab_destination_startpoint'] = temp['flight_destination']
		tempresult['cab_origin']=temp['car_carrier']
		tempresult['cab_origin_fare']=temp['car_source_price']
		tempresult['cab_origin_time']=temp['date']
		tempresult['cab_destination']=temp['car_carrier']
		tempresult['cab_destination_fare'] = temp['car_destination_price']
		tempresult['cab_destination_time'] = temp['date']

		tempresult['flight'] = temp['flight_carrier']
		tempresult['flight_time']= temp['date']
		tempresult['total_price'] = temp['total_price']
		initial['origin']=temp['car_source']
		initial['destination']=temp['car_destination']
		remaining_results.append(tempresult)

	for i in partjson:

		temp = i["fields"]
		initial['type']='success'
		initial['searchid']=temp['search_id']
		initial['origin']=temp['car_source']
		initial['destination']=temp['car_destination']
		tempresult = {}
		tempresult['cab_origin_endpoint'] = temp['flight_source']
		tempresult['cab_destination_startpoint'] = temp['flight_destination']
		tempresult['cab_origin']=temp['car_carrier']
		tempresult['cab_origin_fare']=temp['car_source_price']
		tempresult['cab_origin_time']=temp['date']
		tempresult['cab_destination']=temp['car_carrier']
		tempresult['cab_destination_fare'] = temp['car_destination_price']
		tempresult['cab_destination_time'] = temp['date']

		tempresult['flight'] = temp['flight_carrier']
		tempresult['flight_time']= temp['date']
		tempresult['total_price'] = temp['total_price']
		initial['cheapest'] = tempresult
		initial['remaining_results']= remaining_results

	response['status']=initial
	response['error']='false'
	

	return response

# @api_view(["GET"])

def json_load_byteified(file_handle):
    return _byteify(
        json.load(file_handle, object_hook=_byteify),
        ignore_dicts=True
    )

def json_loads_byteified(json_text):
    return _byteify(
        json.loads(json_text, object_hook=_byteify),
        ignore_dicts=True
    )

def _byteify(data, ignore_dicts = False):
    # if this is a unicode string, return its string representation
    if isinstance(data, unicode):
        return data.encode('utf-8')
    # if this is a list of values, return list of byteified values
    if isinstance(data, list):
        return [ _byteify(item, ignore_dicts=True) for item in data ]
    # if this is a dictionary, return dictionary of byteified keys and values
    # but only if we haven't already byteified it
    if isinstance(data, dict) and not ignore_dicts:
        return {
            _byteify(key, ignore_dicts=True): _byteify(value, ignore_dicts=True)
            for key, value in data.iteritems()
        }
    # if it's anything else, return it in its original form
    return data

def Itenary(request):
    try:
    	if request.method == "GET":
	        #search_id= json.loads(searchid.body)
	        #search_id =request.data
	        temp = request.GET['search_id']
	        
	        #temp = request.query_params.get('search_id')
	        print(temp)
	        #search_id = '2'
	        queryset = SearchDetails.objects.filter(search_id=str(temp)).all()
	        queryset2 = SearchDetails.objects.raw("select * from search_details where search_id= %s",[str(temp)])
	        #serializer_class = UserTravelSerializer
	        # ret = []
	        # for o in result:
	        # 	ret.append(o)
	        # 	print(o)
	        #height=json.loads(heightdata.body)
	        #weight=str(height*10)
	        #result = list(Airport_index.objects.values())
	        #output = Airport_index.objects.raw("SELECT * FROM airport_index")
	        #output = User_Travel.objects.raw("SELECT email FROM user_travel")
	        #result = User_TravelSerializer(User_Travel.objects.all())
	        
	        prettyprint = serializers.serialize('json',queryset2)
	        allprint = serializers.serialize('json',queryset)
	        prettyprint = json_loads_byteified(prettyprint)
	        print(prettyprint)
	        print(type(prettyprint))
	        #printoutput =list(prettyprint)
	        print(type(allprint))

	        output = pretty_json(prettyprint,json_loads_byteified(allprint))
	        print(output)
	        #return JsonResponse(output,safe=False)
	        #return search_id
	        #return JsonResponse("Ideal weight should be:"+weight+" kg",safe=False)
	        return HttpResponse(json.dumps(output), content_type="application/json")
    except ValueError as e:
        return Response(e.args[0],status.HTTP_400_BAD_REQUEST)
