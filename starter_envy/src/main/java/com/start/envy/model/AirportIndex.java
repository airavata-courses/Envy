package com.start.envy.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "airport_index")
public class AirportIndex implements Serializable{
	
	@Column(name="airport")
	private String airport;
	
	
	
	@Column(name="latitude")
	private double latitude;
	

	@Column(name="longitude")
	private double longitude;
	
	@Id
	@Column(name="iata")
	private String iata;
	

	public AirportIndex(String airport, double latitude, double longitude, String iata) {
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
