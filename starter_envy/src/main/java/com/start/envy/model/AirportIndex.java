package com.start.envy.model;

import java.io.Serializable;


public class AirportIndex implements Serializable{
	
	private String airport;
	
	
	
	private double latitude;
	
	private double longitude;
	private String iata;
	

	public AirportIndex() {
		super();
		this.airport = airport;
		this.latitude = latitude;
		this.longitude = longitude;
		this.iata = iata;
	}

	public String getAirport() {
		return airport;
	}

	public void setAirport(String airport) {
		this.airport = airport;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public String getIata() {
		return iata;
	}

	public void setIata(String iata) {
		this.iata = iata;
	}

}
