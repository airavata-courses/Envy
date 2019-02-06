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

	
public ResponseEntity<String> findFlights(String originPlace,String destinationPlace) throws JsonParseException, JsonMappingException, IOException {
		
		String country ="US";
		String currency = "USD";
		String locale = "en-US";
//		originPlace ="SFO-sky";
//		destinationPlace="ORD-sky";
		String inboundDate = "2019-02-10";
		//String outboundDate="2019-02-12";
		String buildUrl = country.concat("/").concat(currency).concat("/").concat(locale).concat("/").concat(originPlace).concat("/").concat(destinationPlace).concat("/").concat(inboundDate);
		String url = "https://skyscanner-skyscanner-flight-search-v1.p.rapidapi.com/apiservices/browseroutes/v1.0/"+buildUrl;
		//String url = "https://skyscanner-skyscanner-flight-search-v1.p.rapidapi.com/apiservices/browseroutes/v1.0/US/USD/en-US/SFO-sky/ORD-sky/2019-02-05?inboundpartialdate=2019-01-01";
		//String url = "https://cometari-airportsfinder-v1.p.rapidapi.com/api/airports/by-radius?X-RapidAPI-Key=84f658fc33msh8dc84ca11f27689p175a8fjsnf4214dbe7a20";
		RestTemplate restTemplate = new RestTemplate();
	    HttpHeaders headers = new HttpHeaders();
	    headers.set("X-RapidAPI-Key", "84f658fc33msh8dc84ca11f27689p175a8fjsnf4214dbe7a20");
//	    
//	    HttpResponse<String> response = Unirest.get("https://skyscanner-skyscanner-flight-search-v1.p.rapidapi.com/apiservices/browseroutes/v1.0/US/USD/en-US/SFO-sky/ORD-sky/2019-02-05?inboundpartialdate=2019-01-01")
//	    		.header("X-RapidAPI-Key", "fe8e115cafmsh948e409de59748ap1de812jsn7c85d9df01ec")
//	    		.asJson();
	    
//	    UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url)
//		        .queryParam("country", "US")
//		        .queryParam("currency", "USD")
//		        .queryParam("locale", "en-US")
//		        .queryParam("originPlace", "SFO-sky")
//		        .queryParam("destinationPlace", "ORD-sky")
//		        .queryParam("inboundDate", "2019-02-10")
//		        .queryParam("outboundDate", "2019-02-10");
//		 
	    
	    //System.out.println(builder.toUriString());
	    headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	    HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
	    System.out.println(url);
	    ResponseEntity<String> result = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
	    String results = result.getBody();
//	    int i;
//	    String finalResult ="";
//	    ObjectMapper objectMapper = new ObjectMapper();
//	    List output = objectMapper.readValue(results, List.class);
//	    for(i=0;i<output.size();i++) {
//	    	Map<?,?> temp = (Map<?,?>) output.get(i);
//	    	String name = (String) temp.get("name");
//	    	finalResult += name + "\n";
//	    }
//	    System.out.println(output);
//	    Map<?,?> output = objectMapper.readValue(results, Map.class);
	    System.out.println(result.getBody());
	    //System.out.println(finalResult);
	    //System.out.println(output);
	    
        return result;
}
}
