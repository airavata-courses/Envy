package com.start.envy.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.start.envy.service.ClosestAirportService;
import com.start.envy.service.GetFlightsService;
import com.start.envy.service.LocationService;
import com.start.envy.service.NearestAirportService;

@RestController
public class StarterController {
	@Autowired 
    private  LocationService locationService;
	
	@Autowired
	private NearestAirportService NearestairportService;
	
	@Autowired
	private ClosestAirportService ClosestAirport;
	
	@Autowired
	private GetFlightsService GetFlights;
	
	@RequestMapping("/getRideRequests")
    public void getCoordinates(@RequestParam(value="from", defaultValue="World") String from,@RequestParam(value="to", defaultValue="World") String to) {
		try {
			double[] location = locationService.findLocation("NewYork");
			
//			String arguments = Double.toString(location[0])+","+Double.toString(location[1]);
//			System.out.println(arguments);
//			String result = NearestairportService.findnearestairport(arguments);
			
		} catch (RestClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	@RequestMapping("/getairport")
	public void getAirport(@RequestParam(value="from", defaultValue="World") String from,@RequestParam(value="to", defaultValue="World") String to) throws JsonParseException, JsonMappingException, IOException {
		try {
			double[] location = locationService.findLocation("NewYork");
			double lat = location[0];
			double lng = location[1];
			String arguments = Double.toString(lng)+","+Double.toString(lat);
			System.out.println(arguments);
			String result = ClosestAirport.findClosestAirport(lat, lng);
			String flights = GetFlights.findFlights();
			
		} catch (RestClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	
	}
}
