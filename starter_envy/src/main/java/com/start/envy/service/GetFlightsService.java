package com.start.envy.service;

import java.io.IOException;
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
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service("GetFlightsService")
public class GetFlightsService {

	
public ResponseEntity<String> findFlights(String originPlace,String destinationPlace, String date) throws JsonParseException, JsonMappingException, IOException {
		
		String country ="US";
		String currency = "USD";
		String locale = "en-US";
		String inboundDate = date;
		String buildUrl = country.concat("/").concat(currency).concat("/").concat(locale).concat("/").concat(originPlace).concat("/").concat(destinationPlace).concat("/").concat(inboundDate);
		String url = "https://skyscanner-skyscanner-flight-search-v1.p.rapidapi.com/apiservices/browseroutes/v1.0/"+buildUrl;
		
		RestTemplate restTemplate = new RestTemplate();
	    HttpHeaders headers = new HttpHeaders();
	    headers.set("X-RapidAPI-Key", "84f658fc33msh8dc84ca11f27689p175a8fjsnf4214dbe7a20");
	    headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	    HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
	    
	    ResponseEntity<String> result = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
	    String results = result.getBody();
	    
        return result;
}
}
