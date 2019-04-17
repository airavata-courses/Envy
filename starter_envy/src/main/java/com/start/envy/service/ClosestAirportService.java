package com.start.envy.service; 

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.start.envy.model.AirportIndex;
import com.start.envy.model.Location;
 
@Service("ClosestAirportService")
public class ClosestAirportService {

	public double distance(double lat1, double lon1, double lat2, double lon2) {


        double theta = lon1 - lon2;
        double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515;
        dist = dist * 0.8684;
          
        return (dist);
      }
	public double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
      }
	public double rad2deg(double rad) {
        return (rad * 180.0 / Math.PI);
      }

	
	public List<AirportIndex> findClosestAirport(Location location) throws JsonParseException, JsonMappingException, IOException {
		
		System.out.println("Finding Closest Airports.....");
		String url = "/nodebackend/findAirports?latitude="+location.getLatitude()+"&longitude="+location.getLongitutde()+"&state="+location.getState();
		System.out.println(url);
		RestTemplate restTemplate = new RestTemplate();
	    HttpHeaders headers = new HttpHeaders();
	    headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	    HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
	    ResponseEntity<String> result = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
	    String results = result.getBody();
	    String finalResult ="",closestairport= "";
	    ObjectMapper objectMapper = new ObjectMapper();
	    Map<String,String> ret = null;
	    System.out.println("results");
	    System.out.println(results);
	    
	    Map<?,?> output = objectMapper.readValue(results, Map.class);
	    Map<?,?> status = (Map<?, ?>) output.get("status");
	    
	    List<Map<?,?>> data  = (List<Map<?, ?>>) status.get("data");
	    List<AirportIndex> aiList = new ArrayList<>();
	   
	    for(Map<?, ?> a: data) {
	    	AirportIndex ai = new AirportIndex();
	    	ai.setAirport((String) a.get("airport"));
	    	ai.setLatitude((Double) a.get("latitude"));
	    	ai.setLongitude((Double) a.get("longitude"));
	    	ai.setIata((String) a.get("iata"));
	    	aiList.add(ai);
	    }
	    //test
//	    for(int i=0;i<output.size();i++) {
//	    	Map<?,?> temp = (Map<?,?>) output.get(i);
//	    	String name = (String) temp.get("code");
//	    	 Map<?, ?> location = (Map<?, ?>) temp.get("location"); 
//	    	Double latitude =  (Double) location.get("latitude");
//	    	Double longitude =  (Double) location.get("longitude");
//	    	double tempdistance = distance(lat, lng, latitude, longitude);
//	    	finalResult += name+",";
//	    }
	    
        return aiList;
}
}
