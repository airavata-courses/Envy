package com.start.envy.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
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
import com.start.envy.model.Carriers;
import com.start.envy.repo.AirportRepository;
import com.start.envy.repo.SearchRepository;
import com.start.envy.service.ClosestAirportService;
import com.start.envy.service.GetFlightsService;
import com.start.envy.service.LocationService;
import com.start.envy.service.NearestAirportService;
import com.start.envy.service.RentalCarService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.start.envy.model.ResponseBody;
import com.start.envy.model.ResponseVO;

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
    
	@Autowired
	private SearchRepository searchRepository;

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
	
	
	@CrossOrigin(allowedHeaders = "GET, POST, DELETE, PUT, OPTIONS, HEAD")
	@RequestMapping("/getairport")
	public ResponseVO getAirport(@RequestParam(value="from", defaultValue="World") String from,@RequestParam(value="to", defaultValue="World") String to) throws JsonParseException, JsonMappingException, IOException, ParseException {
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
			
			Map<String,String> Placejson = new HashMap<>();
			Map<String,String> PlaceCodeJson = new HashMap<>();
			Map<String,String> carriercompany = new HashMap<>() ;
			ResponseVO rvo = new ResponseVO();
			
			ResponseEntity<String> flights = GetFlights.findFlights("SFO","JFK");
			System.out.println(flights.getBody());
			ObjectMapper mapper = new ObjectMapper();
			Map<String,Object> map = mapper.readValue(flights.getBody(), Map.class);
			List results = (List<?>) map.get("Quotes");
			List CarriersList =(List<?>)  map.get("Carriers");
			List Places = (List<?>)map.get("Places");
			for(int i=0;i<Places.size();i++) {
				
				Map<?,?> tempPlace=(Map<?,?>) Places.get(i);
				Placejson.put(tempPlace.get("PlaceId").toString(), tempPlace.get("Name").toString());
				PlaceCodeJson.put(tempPlace.get("PlaceId").toString(), tempPlace.get("IataCode").toString());
			}
			System.out.println(Placejson);
			
			for(int i=0;i<CarriersList.size();i++) {
				
				Map<?, ?> c =  (Map<?, ?>)CarriersList.get(i);
				System.out.println(Integer.toString((int) c.get("CarrierId")));
				System.out.println();
				carriercompany.put(Integer.toString((int) c.get("CarrierId")),c.get("Name").toString());
				
			}
			System.out.println(carriercompany);
			List QuotesArray = new ArrayList<>();
			for(int k=0;k<results.size();k++) {
				ResponseBody rb = new ResponseBody();
				Map<?,?> Quote=(Map<?,?>) results.get(k);
				rb.setPrice((Double) Quote.get("MinPrice"));
				System.out.println((Double) Quote.get("MinPrice"));
				Map<?,?> OutboundLeg =(Map<?,?>)Quote.get("OutboundLeg");
				String endpoints = Placejson.get(OutboundLeg.get("OriginId").toString()) + "->"+Placejson.get(OutboundLeg.get("DestinationId").toString());
				rb.setEndpoints(endpoints);
				System.out.println(carriercompany);
				
				List carrier=(List) OutboundLeg.get("CarrierIds");
				System.out.println(carrier.size());
				rb.setCompany(carriercompany.get(Integer.toString((int) carrier.get(0))));
				System.out.println(carriercompany.get(Integer.toString((int) carrier.get(0))));
				Date tempdate = new SimpleDateFormat("yyyy-mm-dd'T'HH:mm:ss").parse((String) Quote.get("QuoteDateTime"));
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm");

				String dateString = format.format( tempdate   );
				rb.setTravelTimeStamp(dateString);
				System.out.println(dateString);
				QuotesArray.add(rb);
				String codes = PlaceCodeJson.get(OutboundLeg.get("OriginId").toString()) + "->"+PlaceCodeJson.get(OutboundLeg.get("DestinationId").toString());
				
				rb.setEndcodes(codes);
			}
			System.out.println(QuotesArray);
			rvo.setFlight(QuotesArray);
			rvo.setStartlatitude(start_lat);
			rvo.setEndLatitude(end_lat);
			rvo.setStartLongitude(start_lng);
			rvo.setEndLongitude(end_lng);
			return getCarRideRequests(rvo, "SFO","JFK");
			
			
			
		} catch (RestClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	
	public ResponseVO getCarRideRequests(ResponseVO res,String from, String to) {
		System.out.println("In here");
		
		double start_lat = res.getStartlatitude();
		double end_lat = res.getEndLatitude();
		double start_lng = res.getStartLongitude();
		double end_lng = res.getEndLongitude();
		
		List<ResponseBody> flights = res.getFlight();
		for(int i=0;i<flights.size();i++) {
			String[] split_endpoints = flights.get(i).getEndpoints().split("->");
			String[] split_endcodes = flights.get(i).getEndcodes().split("->");
			System.out.println(split_endcodes[0] + AirportRepo.findByLatitude(split_endcodes[0]));
			double latendpoint1 = AirportRepo.findByLatitude(split_endcodes[0]);
			double latendpoint2 = AirportRepo.findByLatitude(split_endcodes[1]);
			double lngendpoint1 = AirportRepo.findByLongitude(split_endcodes[0]);
			double lngendpoint2 = AirportRepo.findByLongitude(split_endcodes[1]);
			SearchDetails sd  = rentalCarService.getCarRideRequests(start_lat, start_lng, latendpoint1, lngendpoint1);
			SearchDetails sd2 = rentalCarService.getCarRideRequests(latendpoint2, lngendpoint2, end_lat, end_lng);
			sd.setCarsource(from);
			sd.setCardestination(to);
			System.out.println(split_endpoints[0]);
			sd.setFlightsource(split_endpoints[0]);
			sd.setFlightdestination(split_endpoints[1]);
			sd.setCarSourcePrice(sd.getTotalprice());
			sd.setCarDestinationPrice(sd2.getTotalprice());
			sd.setFlightPrice(flights.get(i).getPrice());
			sd.setTotalprice(sd.getTotalprice() + sd2.getTotalprice() + flights.get(i).getPrice());
			sd.setFlightcarrier(flights.get(i).getCompany());
			sd.setSearchId("2");
			searchRepository.save(sd);
		}
	
		res.setSuccess(true);
		res.setSearchId("2");
		
		return res;
				
	}
	
}
