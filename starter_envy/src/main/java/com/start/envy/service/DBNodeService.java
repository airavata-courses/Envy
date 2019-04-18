package com.start.envy.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.start.envy.model.AirportIndex;
import com.start.envy.model.SearchDetails;

@Service("DBNodeService")
public class DBNodeService {
	
	public void SendDataToDb(ArrayList<SearchDetails> sdList) {
		String url = "http://149.165.170.230:30045/addToDatabase";
		System.out.println(url);
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.set("Content-Type", "application/json");
		JSONObject json = new JSONObject();
		JSONArray jsonArray = new JSONArray(sdList);
		try {
			json.put("sdList", jsonArray);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(jsonArray);
		HttpEntity <String> httpEntity = new HttpEntity <String> (json.toString(), httpHeaders);
	    
	    String response = restTemplate.postForObject(url, httpEntity, String.class);
	    System.out.println("response"+ response);

	}

}
