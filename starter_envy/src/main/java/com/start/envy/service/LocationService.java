package com.start.envy.service;

import static java.net.URLEncoder.encode;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Service("LocationService")
public class LocationService {
	public static final String STATUS_OK = "OK"; 
    public static final String STATUS_ZERO_RESULTS = "ZERO_RESULTS"; 
    public static final String STATUS_OVER_QUERY_LIMIT = "OVER_QUERY_LIMIT"; 
    public static final String STATUS_REQUEST_DENIED = "REQUEST_DENIED"; 
    public static final String STATUS_INVALID_REQUEST = "INVALID_REQUEST"; 
 
	
	public static final String GOOGLE_MAPS_API_ENDPOINT = "https://maps.googleapis.com/maps/api/geocode/json?address={address}&sensor=false&key= AIzaSyBagWk-nugrXCoELtAZHg0CR0BcnlqWrU0"; 
	 
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
    
    
    public Double[] findLocation(String address) throws RestClientException, UnsupportedEncodingException { 
    	RestTemplate restTemplate = new RestTemplate();
    	Map<?, ?> obj = restTemplate.getForObject(GOOGLE_MAPS_API_ENDPOINT, Map.class,encode(address, "UTF-8")); 
               // check the response status 
        String status = (String) obj.get("status"); 
        if (!status.equals(STATUS_OK)) { 
            throw new RuntimeException(buildMessage(status)); 
        } 
 
        List<?> results = (List<?>) obj.get("results"); 
        Map<?, ?> result = (Map<?, ?>) results.get(0); 
        Map<?, ?> geometry = (Map<?, ?>) result.get("geometry"); 
        Map<?, ?> location = (Map<?, ?>) geometry.get("location"); 
        
        if(location.get("lat") == null || location.get("lng") == null) {
        	return null;
        }
        return new Double[]{ 
                (Double) location.get("lat"), 
                (Double) location.get("lng") 
                
        }; 
   
    } 

}
