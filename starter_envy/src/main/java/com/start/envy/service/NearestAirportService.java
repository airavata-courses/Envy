package com.start.envy.service;

import static java.net.URLEncoder.encode;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Service("NearestAiportService")
public class NearestAirportService {
	
	public static final String STATUS_OK = "OK"; 
    public static final String STATUS_ZERO_RESULTS = "ZERO_RESULTS"; 
    public static final String STATUS_OVER_QUERY_LIMIT = "OVER_QUERY_LIMIT"; 
    public static final String STATUS_REQUEST_DENIED = "REQUEST_DENIED"; 
    public static final String STATUS_INVALID_REQUEST = "INVALID_REQUEST"; 
 
	
	public static final String GOOGLE_MAPS_API_ENDPOINT = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?location= {location}&rankby=distance&type=airport&key=AIzaSyBagWk-nugrXCoELtAZHg0CR0BcnlqWrU0"; 
    public static final String endpoint = "http://api.geonames.org/findNearby?lat= {lat}&lng= {lng}&fcode=AIRP&radius=25&maxRows=100&username=thumblas";
    //private RestTemplate restTemplate; 	
//    
//    @Autowired 
//    public LocationService(RestTemplate restTemplate) { 
//        //this.restTemplate = restTemplate; 
//    } 
 
    private String buildMessage(String status) { 
        if (status == STATUS_ZERO_RESULTS) 
            return "No result is found"; 
        else if (status == STATUS_OVER_QUERY_LIMIT) 
            return "You are over your quota"; 
        else if (status == STATUS_REQUEST_DENIED) 
            return "Your request was denied"; 
        else if (status == STATUS_INVALID_REQUEST) 
            return "The query is missing";
 
        return ""; 
   } 
    
    
    public String findnearestairport(String location) throws RestClientException, UnsupportedEncodingException { 
    	RestTemplate restTemplate = new RestTemplate();
    	
    	System.out.println("In nearest airprt !!");
        Map<?, ?> obj = restTemplate.getForObject(GOOGLE_MAPS_API_ENDPOINT, Map.class,location); 
        System.out.println(obj);
 
        // check the response status 
        String status = (String) obj.get("status"); 
        if (!status.equals(STATUS_OK)) { 
            throw new RuntimeException(buildMessage(status)); 
        } 
 
        List<?> results = (List<?>) obj.get("results");
        int i;
        String res ="";
        for(i=0;i<results.size();i++) {
        Map<?, ?> result = (Map<?, ?>) results.get(i); 
//        Map<?, ?> geometry = (Map<?, ?>) result.get("geometry"); 
//        Map<?, ?> location = (Map<?, ?>) geometry.get("location"); 
        String name =  (String) result.get("name");
        //String name = result.get("name");
        //System.out.println(location.get("lat"));
        //System.out.println(location.get("lng"));
        System.out.println(name);
        res+= name+"\n";
        }
        return res;
   
    } 

}