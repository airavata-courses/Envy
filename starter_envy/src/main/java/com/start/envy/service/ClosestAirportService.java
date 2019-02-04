package com.start.envy.service; 

import java.util.List;
import java.util.Map;
import java.io.IOException;
import java.util.Arrays;

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

@Service("ClosestAirportService")
public class ClosestAirportService {

	
	public String findClosestAirport(double lat,double lng) throws JsonParseException, JsonMappingException, IOException {
		
		
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
	    String finalResult ="";
	    ObjectMapper objectMapper = new ObjectMapper();
	    List output = objectMapper.readValue(results, List.class);
	    for(i=0;i<output.size();i++) {
	    	Map<?,?> temp = (Map<?,?>) output.get(i);
	    	String name = (String) temp.get("name");
	    	finalResult += name + "\n";
	    }
//	    System.out.println(output);
//	    Map<?,?> output = objectMapper.readValue(results, Map.class);
	    System.out.println(result.getBody());
	    System.out.println(finalResult);
	    //System.out.println(output);
	    
        return finalResult;
}
}