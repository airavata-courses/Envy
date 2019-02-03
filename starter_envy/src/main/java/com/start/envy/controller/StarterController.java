package com.start.envy.controller;

import java.io.UnsupportedEncodingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;

import com.start.envy.service.LocationService;

@RestController
public class StarterController {
	@Autowired 
    private  LocationService locationService;
	
	@RequestMapping("/getRideRequests")
    public void getCoordinates(@RequestParam(value="from", defaultValue="World") String from,@RequestParam(value="to", defaultValue="World") String to) {
		try {
			double[] location = locationService.findLocation("Indiana");
		} catch (RestClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	
}
