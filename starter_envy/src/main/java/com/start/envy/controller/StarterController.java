package com.start.envy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.start.envy.service.LocationService;
@Controller
public class StarterController {
	@Autowired 
    private static LocationService locationService;
	public static void Main(String[] args) {
	double[] location = null;
	
    try { 
        location = locationService.findLocation("Indiana"); 
    } catch (Exception e) { 
        e.printStackTrace(); 
    } 
    
  System.out.println(location);

	}	
}
