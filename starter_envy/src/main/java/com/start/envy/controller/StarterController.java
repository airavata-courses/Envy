package com.start.envy.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.start.envy.model.AirportIndex;
import com.start.envy.model.Location;
import com.start.envy.model.ResponseBody;
import com.start.envy.model.ResponseVO;
import com.start.envy.model.SearchDetails;
import com.start.envy.service.ClosestAirportService;
import com.start.envy.service.DBNodeService;
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
	private DBNodeService dbNodeService;
	
	

	@RequestMapping("/getAirport")
	@CrossOrigin(origins = "http://149.165.170.230:30002")
	public ResponseVO getAirport(@RequestParam(value="origin", defaultValue="") String origin,@RequestParam(value="destination", defaultValue="World") String destination,
			@RequestParam(value="date", defaultValue="") String date, @RequestParam(value="search_id", defaultValue="") String searchId) throws JsonParseException, JsonMappingException, IOException, ParseException {
		ResponseVO responseVO = new ResponseVO();
		ArrayList<SearchDetails> sdList = new ArrayList<>();
		try {

			
			System.out.println("Parameters received : "+ origin + " " + destination + " " + date + " " + searchId);
			if(origin.equals("") || destination.equals("") || date.equals("")) {
				responseVO.setSuccess(false);
				responseVO.setMessage("Invalid Field Values");
				return responseVO;
			}

			Location locOrigin = locationService.findLocation(origin);
			Location locDest =locationService.findLocation(destination);

			Double[] start_location = new Double[2];
			Double[] end_location = new Double[2];
			
			start_location[0] = locOrigin.getLatitude();
			start_location[1] = locOrigin.getLatitude();
			
			end_location[0] = locDest.getLatitude();
			end_location[1] = locDest.getLatitude();
			
			if(start_location == null || end_location == null) {
				responseVO.setSuccess(false);
				responseVO.setMessage("Invalid Location");
				return responseVO;

			}
		
			

			double start_lat = start_location[0];
			double start_lng = start_location[1];

			double end_lat = end_location[0];
			double end_lng = end_location[1];

			List<AirportIndex> resultairport_org = ClosestAirport.findClosestAirport(locOrigin);
			List<AirportIndex> resultairport_dest = ClosestAirport.findClosestAirport(locDest);
			//System.out.println(source_airports);
			//System.out.println(split_source_airports);
			List<String> sources = new ArrayList<>();
			List<String> destinations = new ArrayList<>();
			
			for(AirportIndex source: resultairport_org) {
				sources.add(source.getIata());
			}
			for(AirportIndex dest: resultairport_dest) {
				destinations.add(dest.getIata());
			}
			

			System.out.println("Sources Found-- "+sources);
			System.out.println("Destination Found-- "+destinations);

			if(sources.isEmpty() || destinations.isEmpty()) {
				responseVO.setSuccess(false);
				responseVO.setMessage("No airports found within 100 miles of given location. Please change location.");
				return responseVO;
			}
			
			Map<String,String> Placejson = new HashMap<>();
			Map<String,String> PlaceCodeJson = new HashMap<>();
			Map<String,String> carriercompany = new HashMap<>() ;
			ResponseVO rvo = new ResponseVO();
			System.out.println("Finding Flights....");
			for(AirportIndex airfrom: resultairport_org) {
				for(AirportIndex airto: resultairport_dest) {
					String from  = airfrom.getIata();
					String to = airto.getIata();
					
					System.out.println("Source : " + from + " : " + "Destination" + to);
					ResponseEntity<String> flights = null;
					List results = new ArrayList<>();
					List QuotesArray = new ArrayList<>();
					Map<String,Object> map = null;
					int counter = 0;
					while(counter < 3) {
						try {
							flights = GetFlights.findFlights(from, to, date);
							break;
						}catch(Exception e) {
							counter++;
							e.printStackTrace();
						}
					}
					ResponseBody rb = new ResponseBody();
					ObjectMapper mapper = new ObjectMapper();
					if(from.equals(to)) {
						responseVO.setSuccess(false);
						responseVO.setMessage("Please change location.");
						return responseVO;
					}
					if(flights !=null) {
						map = mapper.readValue(flights.getBody(), Map.class);
						results = (List<?>) map.get("Quotes");
					}
					System.out.println("Flights : " + results);
					if(results.size() == 0) {
						Random random = new Random();
						String airlines[] = {"American Airlines", "Alaska Airlines", "SouthWest Airlines", "Delta Airlines", "Virgin Atlantic"};
						rb.setCompany(airlines[random.nextInt(airlines.length)]);
						DecimalFormat df2 = new DecimalFormat(".##");
						rb.setPrice(Double.valueOf(df2.format(Math.random() * 300 + 200)));
						rb.setEndpoints(airfrom.getAirport()+ "-"
								+ ">" +airto.getAirport());
						rb.setTravelTimeStamp(new SimpleDateFormat("yyyy-MM-dd hh:mm").format(Calendar.getInstance().getTime()));
						rb.setEndcodes(from + "->" + to);
						QuotesArray.add(rb);
					}else {
						
						List CarriersList =(List<?>)  map.get("Carriers");
						List Places = (List<?>)map.get("Places");
						for(int i=0;i<Places.size();i++) {

							Map<?,?> tempPlace=(Map<?,?>) Places.get(i);
							Placejson.put(tempPlace.get("PlaceId").toString(), tempPlace.get("Name").toString());
							PlaceCodeJson.put(tempPlace.get("PlaceId").toString(), tempPlace.get("IataCode").toString());
						}
						
						for(int i=0;i<CarriersList.size();i++) {
							Map<?, ?> c =  (Map<?, ?>)CarriersList.get(i);
							carriercompany.put(Integer.toString((Integer) c.get("CarrierId")),c.get("Name").toString());
						}

						for(int k=0;k<results.size();k++) {
							Map<?,?> Quote=(Map<?,?>) results.get(k);
							rb.setPrice((Double) Quote.get("MinPrice"));
							Map<?,?> OutboundLeg =(Map<?,?>)Quote.get("OutboundLeg");
							String endpoints = Placejson.get(OutboundLeg.get("OriginId").toString()) + "->"+Placejson.get(OutboundLeg.get("DestinationId").toString());
							rb.setEndpoints(endpoints);
							List carrier=(List) OutboundLeg.get("CarrierIds");
							rb.setCompany(carriercompany.get(Integer.toString((int) carrier.get(0))));
							Date tempdate = new SimpleDateFormat("yyyy-mm-dd'T'HH:mm:ss").parse((String) Quote.get("QuoteDateTime"));
							SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm");
							String dateString = format.format( tempdate   );
							rb.setTravelTimeStamp(dateString);
							QuotesArray.add(rb);
							String codes = PlaceCodeJson.get(OutboundLeg.get("OriginId").toString()) + "->"+PlaceCodeJson.get(OutboundLeg.get("DestinationId").toString());
							rb.setEndcodes(codes);
						} 
					}
					
					rvo.setFlight(QuotesArray);
					rvo.setStartlatitude(start_lat);
					rvo.setEndLatitude(end_lat);
					rvo.setStartLongitude(start_lng);
					rvo.setEndLongitude(end_lng);
					getCarRideRequests(rvo, origin, destination, searchId, sdList,airfrom, airto);

				}
			}
			
			
			System.out.println("Flights found...");
			System.out.println(sdList);
			dbNodeService.SendDataToDb(sdList);
			responseVO = new ResponseVO();
			responseVO.setSearchId(searchId);
			responseVO.setSuccess(true);
			responseVO.setMessage("Success");
			responseVO.setDate(date);
			//responseVO.setSdList(sdList);

			return responseVO;

		} catch (RestClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}



	public ResponseVO getCarRideRequests(ResponseVO res,String from, String to, String searchId, ArrayList<SearchDetails> sdList, AirportIndex airFrom, AirportIndex airTo) {
		System.out.println("Getting rental Prices....");

		double start_lat = res.getStartlatitude();
		double end_lat = res.getEndLatitude();
		double start_lng = res.getStartLongitude();
		double end_lng = res.getEndLongitude();

		List<ResponseBody> flights = res.getFlight();
		String[] split_endpoints = flights.get(0).getEndpoints().split("->");
		String[] split_endcodes = flights.get(0).getEndcodes().split("->");

		double latendpoint1 = airFrom.getLatitude();
		double latendpoint2 = airTo.getLatitude();
		double lngendpoint1 = airFrom.getLongitude();
		double lngendpoint2 = airTo.getLongitude();

		SearchDetails sd  = rentalCarService.getCarRideRequests(start_lat, start_lng, latendpoint1, lngendpoint1);
		SearchDetails sd2 = rentalCarService.getCarRideRequests(latendpoint2, lngendpoint2, end_lat, end_lng);
		System.out.println("Adding prices to DB!");
		for(int i=0;i<flights.size();i++) {
			SearchDetails sd1 = new SearchDetails();
			sd1.setCarsource(from);
			sd1.setCardestination(to);
			sd1.setFlightsource(split_endpoints[0]);
			sd1.setFlightdestination(split_endpoints[1]);
			sd1.setCarSourcePrice(sd.getTotalprice());
			sd1.setCarDestinationPrice(sd2.getTotalprice());
			sd1.setFlightPrice(flights.get(i).getPrice());
			sd1.setTotalprice(sd.getTotalprice() + sd2.getTotalprice() + flights.get(i).getPrice());
			sd1.setFlightcarrier(flights.get(i).getCompany());
			sd1.setSearchId(searchId);
			sd1.setCarcarrier(sd.getCarcarrier());
			sd1.setDate(sd.getDate());
			sdList.add(sd1);
			
		}
		System.out.println("Added prices to DB!");

		res.setSuccess(true);
		res.setSearchId(searchId);

		return res;

	}

}
