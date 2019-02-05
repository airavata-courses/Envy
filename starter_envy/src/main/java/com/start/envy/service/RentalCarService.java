package com.start.envy.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.start.envy.model.ResponseBody;
import com.start.envy.model.ResponseVO;
import com.start.envy.model.SearchDetails;




@Service("RentalCarService")
public class RentalCarService {
	
	
	
	public ResponseVO getCarRideRequests(String start_latitude,
			String start_longitude,
			String end_latitude,
			String end_longitude) {
		System.out.println("In here");
		final String uri = "https://api.uber.com/v1.2/estimates/price";
//				+ "start_latitude=37.7752315&start_longitude=-122.418075&end_latitude=37.7752415&end_longitude=-122.518075";
		
		List<?> results = new ArrayList<>();
		SearchDetails searchDetails = new SearchDetails();
		ResponseVO rvo = new ResponseVO();
		try { 
		
			RestTemplate restTemplate = new RestTemplate();
			HttpHeaders headers = new HttpHeaders();
			headers.set("Authorization", "Token QJjMDPc2Pue2O8KpgM0dUYaCP4ZRRuf1zagNMcrM");
			headers.set("Accept-Language", "en_US");
			headers.set("Content-Type", "application/json");
			headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		
			UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(uri)
			        .queryParam("start_latitude", 37.7752315)
			        .queryParam("start_longitude", -122.418075)
			        .queryParam("end_latitude", 37.7752415)
			        .queryParam("end_longitude", -122.518075);
			        
			
			HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
			ResponseEntity<String> result = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity, String.class);
			ObjectMapper mapper = new ObjectMapper();
			Map<String,Object> map = mapper.readValue(result.getBody(), Map.class);
			results = (List<?>) map.get("prices");
			
			System.out.println(results.get(0));
			Map<?, ?> obj = (Map<?, ?>) results.get(0); 
			Double estimate = (Double) obj.get("low_estimate");
			
			searchDetails.setSearchId("s1");
			searchDetails.setPrice(estimate);
			searchDetails.setCarrier("Uber");
			searchDetails.setType("Rental");
			searchDetails.setSource("NYC");
			searchDetails.setDestination("ORD");
			ResponseBody cabOrigin = new ResponseBody();
			cabOrigin.setCompany("Uber");
			cabOrigin.setEndpoints("NYC");
			cabOrigin.setPrice(estimate);
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm");

			String dateString = format.format( new Date()   );
			cabOrigin.setTravelTimeStamp(dateString);
			ResponseBody cabDestination = new ResponseBody();
			cabDestination.setCompany("Uber");
			cabDestination.setEndpoints("NYC");
			cabDestination.setPrice(estimate);
			cabDestination.setTravelTimeStamp(new Date().toString());
			rvo.setSuccess(true);
			rvo.setCabOrigin(cabOrigin);
			rvo.setCabDestination(cabDestination);
		
			
			
			
		}catch(Exception e) {
			e.printStackTrace();
			
		}

		
		return rvo;
	}


}
