package com.start.envy.model;

public class Location {
	private Double  latitude;
	private Double longitutde;
	private String state;
	
	public Location(Double latitude, Double longitutde, String state) {
		super();
		this.latitude = latitude;
		this.longitutde = longitutde;
		this.state = state;
	}
	
	public Location() {
		// TODO Auto-generated constructor stub
	}

	public Double getLatitude() {
		return latitude;
	}
	
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	public Double getLongitutde() {
		return longitutde;
	}
	public void setLongitutde(Double longitutde) {
		this.longitutde = longitutde;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
	



}
