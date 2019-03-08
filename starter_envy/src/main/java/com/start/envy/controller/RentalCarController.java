package com.start.envy.controller;



import static java.net.URLEncoder.encode;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.start.envy.service.LocationService;

@RestController
public class RentalCarController {
	
	
	@Autowired 
    private  LocationService locationService;
	
//testing jenkins
	@RequestMapping("/getRideRequestes")
	public void getCarRideRequests(@RequestParam(value="from", defaultValue="World") String from,@RequestParam(value="to", defaultValue="World") String to) throws RestClientException, UnsupportedEncodingException {
		System.out.println("In here");
		
		Double[] start_location = locationService.findLocation("Indiana");
		Double[] end_location =locationService.findLocation("NewYork");
		double start_lat = start_location[0];
		double start_lng = start_location[1];
		
		double end_lat = end_location[0];
		double end_lng = end_location[1];
		final String uri = "https://api.uber.com/v1.2/estimates/price?start_latitude=37.7752315&start_longitude=-122.418075&end_latitude=37.7752415&end_longitude=-122.518075";
		try { 
		
			RestTemplate restTemplate = new RestTemplate();
			HttpHeaders headers = new HttpHeaders();
			headers.set("Authorization", "Token QJjMDPc2Pue2O8KpgM0dUYaCP4ZRRuf1zagNMcrM");
			headers.set("Accept-Language", "en_US");
			headers.set("Content-Type", "application/json");
			headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
			HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
			ResponseEntity<String> result = restTemplate.exchange(uri, HttpMethod.GET, entity, String.class);
			ObjectMapper mapper = new ObjectMapper();
			Map<String,Object> map = mapper.readValue(result.getBody(), Map.class);
			
			
		
		}catch(Exception e) {
			e.printStackTrace();
			
		}

	}

}

