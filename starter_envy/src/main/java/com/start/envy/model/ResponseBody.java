package com.start.envy.model;

public class ResponseBody {
	private String company;
	private String travelTimeStamp;
	private String endpoints;
	private Double price;
	private String endcodes; 
public String getEndcodes() {
		return endcodes;
	}
	public void setEndcodes(String endcodes) {
		this.endcodes = endcodes;
	}
	//	private Double startlatitude;
//	private Double endLatitude;
//	private Double startLongitude;
//	private Double endLongitude;
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getTravelTimeStamp() {
		return travelTimeStamp;
	}
	public void setTravelTimeStamp(String travelTimeStamp) {
		this.travelTimeStamp = travelTimeStamp;
	}
	public String getEndpoints() {
		return endpoints;
	}
	public void setEndpoints(String endpoints) {
		this.endpoints = endpoints;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}

}
