package com.start.envy.service; 

import java.util.List;
import java.util.Map;
import java.io.IOException;
import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.start.envy.repo.AirportRepository;

 
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

	
	public String findClosestAirport(double lat,double lng) throws JsonParseException, JsonMappingException, IOException {
		
		System.out.println("inside closest");
		String url = "https://cometari-airportsfinder-v1.p.rapidapi.com/api/airports/by-radius?X-RapidAPI-Key=84f658fc33msh8dc84ca11f27689p175a8fjsnf4214dbe7a20&radius=50&lng="+lng+"&lat="+lat;
		//String url = "https://cometari-airportsfinder-v1.p.rapidapi.com/api/airports/by-radius?X-RapidAPI-Key=84f658fc33msh8dc84ca11f27689p175a8fjsnf4214dbe7a20";
		RestTemplate restTemplate = new RestTemplate();
	    HttpHeaders headers = new HttpHeaders();
	    headers.set("X-RapidAPI-Key", "84f658fc33msh8dc84ca11f27689p175a8fjsnf4214dbe7a20");

	    headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	    HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
	    System.out.println(url);
	    ResponseEntity<String> result = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
	    String results = result.getBody();
	    int i;
	    String finalResult ="",closestairport= "";
	    double min = 999999.999;
	    ObjectMapper objectMapper = new ObjectMapper();
	    Map<String,String> ret = null;
	    List output = objectMapper.readValue(results, List.class);
	   // List all = null ;
	    for(i=0;i<output.size();i++) {
	    	Map<?,?> temp = (Map<?,?>) output.get(i);
	    	String name = (String) temp.get("code");
	    	 Map<?, ?> location = (Map<?, ?>) temp.get("location");
	    	 System.out.println(location);
//	    	String latlng =  location.get("latitude")+","+location.get("longitude");
	    	Double latitude =  (Double) location.get("latitude");
	    	Double longitude =  (Double) location.get("longitude");
	    	Double tempdistance = distance(lat, lng, latitude, longitude);
	    	//System.out.println(tempdistance);
	    	if (tempdistance<min) {
	    		min = tempdistance;
	    		closestairport = name;
	    	}
	    	//ret.put(name, latlng);
	    	finalResult += name+"\n";
	    	//all.add(name);
	    }
	     
	   // List alliata  = temp.findAll();
	  // System.out.println(closestairport);
//	    Map<?,?> output = objectMapper.readValue(results, Map.class);
	    //System.out.println(result.getBody());
	    System.out.println(finalResult);
	    //System.out.println(output);
	    
        return finalResult;
}
}