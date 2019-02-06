package com.start.envy.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.CrossOrigin;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import com.start.envy.model.ResponseVO;
import com.start.envy.model.SearchDetails;

import com.start.envy.model.AirportIndex;
import com.start.envy.repo.AirportRepository;

import com.start.envy.service.ClosestAirportService;
import com.start.envy.service.GetFlightsService;
import com.start.envy.service.LocationService;
import com.start.envy.service.NearestAirportService;
import com.start.envy.service.RentalCarService;

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

	
	@Autowired
	private RentalCarService rentalCarService;
	
	

	@Autowired
	private AirportRepository AirportRepo ;


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
			double[] start_location = locationService.findLocation("Califoria");
			double[] end_location =locationService.findLocation("NewYork");
			double start_lat = start_location[0];
			double start_lng = start_location[1];

			double end_lat = end_location[0];
			double end_lng = end_location[1];

			String source_airports = ClosestAirport.findClosestAirport(start_lat, start_lng);
			String destination_airports = ClosestAirport.findClosestAirport(end_lat, end_lng);
			//System.out.println(source_airports);
			String[] split_source_airports = source_airports.split("\n");
			String[] split_destination_airports = destination_airports.split("\n");
			//System.out.println(split_source_airports);
			List<String> sources = new ArrayList<>();
			List<String> destination = new ArrayList<>();
			
			List<AirportIndex> resultairport = AirportRepo.findByIata(split_source_airports[0]);
			System.out.println(resultairport);
			for(String temp:split_source_airports) {
				if(temp!=null) {
					Boolean Flag =resultairport.contains(temp);
					if(Flag)
						sources.add(temp);

				}
			}
			for(String temp:split_destination_airports) {
				if(temp!=null) {
					Boolean Flag =resultairport.contains(temp);
					if(Flag)
						destination.add(temp);

				}
			}
			//			for(String temp:split_destination_airports) {
			//				Boolean Flag =resultairport.contains(temp);
			//				if(Flag)
			//					destination.add(temp);
			//				
			//				}
			System.out.println(sources);
			System.out.println(destination);
//			for(int i=0;i<sources.size();i++) {
//				for(int j=0;j<destination.size();j++) {
//			ResponseEntity<String> flights = GetFlights.findFlights(sources.get(i),destination.get(j));
//			if(flights.getStatusCode()==HttpStatus.ACCEPTED) {
//				
//				System.out.println(flights.getBody());
//			}
//			else
//				continue;
//				}
//			}
			ResponseEntity<String> flights = GetFlights.findFlights("FAT","JFK");
			System.out.println(flights.getBody());
			
		} catch (RestClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	

	}
	
	@CrossOrigin(allowedHeaders = "GET, POST, DELETE, PUT, OPTIONS, HEAD")
	@RequestMapping("/getCarRideRequests")
	public ResponseVO getCarRideRequests(@RequestParam(value="start_latitude",defaultValue = "") String start_latitude,
			@RequestParam(value="start_longitude",defaultValue = "") String start_longitude,
			@RequestParam(value="end_latitude",defaultValue = "") String end_latitude,
			@RequestParam(value="end_longitude",defaultValue = "") String end_longitude) {
		System.out.println("In here");
		
		return rentalCarService.getCarRideRequests(start_latitude, start_longitude, end_latitude, end_longitude);
		
	}

}
