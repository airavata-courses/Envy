package com.start.envy.model;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "search_details")
public class SearchDetails implements Serializable{

	
	
	public SearchDetails(String carcarrier, String flightcarrier, Double totalprice, String carsource,
			String cardestination, String flightsource, String flightdestination, String date, String searchId) {
		super();
		this.carcarrier = carcarrier;
		this.flightcarrier = flightcarrier;
		this.totalprice = totalprice;
		this.carsource = carsource;
		this.cardestination = cardestination;
		this.flightsource = flightsource;
		this.flightdestination = flightdestination;
		this.date = date;
		this.searchId = searchId;
	}
	@Column(name="car_carrier")
	private String carcarrier;
	@Column(name="flight_carrier")
	private String flightcarrier;
	@Id
	@Column(name="total_price")
	private Double totalprice;
	@Column(name="car_source")
	private String carsource;
	@Column(name="car_destination")
	private String cardestination;
	@Column(name="flight_source")
	
	private String flightsource;
	@Column(name="flight_destination")
	private String flightdestination;
	@Column(name="date")
	private String date;
	@Column(name="search_Id")
	private String searchId;
	@Column(name="car_source_price")
	
	private Double carSourcePrice;
	@Column(name="car_destination_price")
	private Double carDestinationPrice;
	@Column(name="flight_Price")
	private Double flightPrice;
	
	public Double getCarSourcePrice() {
		return carSourcePrice;
	}

	public void setCarSourcePrice(Double carSourcePrice) {
		this.carSourcePrice = carSourcePrice;
	}

	public Double getCarDestinationPrice() {
		return carDestinationPrice;
	}

	public void setCarDestinationPrice(Double carDestinationPrice) {
		this.carDestinationPrice = carDestinationPrice;
	}

	public Double getFlightPrice() {
		return flightPrice;
	}

	public void setFlightPrice(Double flightPrice) {
		this.flightPrice = flightPrice;
	}

	public SearchDetails() {}
	
	public String getCarcarrier() {
		return carcarrier;
	}
	public void setCarcarrier(String carcarrier) {
		this.carcarrier = carcarrier;
	}
	public String getFlightcarrier() {
		return flightcarrier;
	}
	public void setFlightcarrier(String flightcarrier) {
		this.flightcarrier = flightcarrier;
	}
	public Double getTotalprice() {
		return totalprice;
	}
	public void setTotalprice(Double totalprice) {
		this.totalprice = totalprice;
	}
	public String getCarsource() {
		return carsource;
	}
	public void setCarsource(String carsource) {
		this.carsource = carsource;
	}
	public String getCardestination() {
		return cardestination;
	}
	public void setCardestination(String cardestination) {
		this.cardestination = cardestination;
	}
	public String getFlightsource() {
		return flightsource;
	}
	public void setFlightsource(String flightsource) {
		this.flightsource = flightsource;
	}
	public String getFlightdestination() {
		return flightdestination;
	}
	public void setFlightdestination(String flightdestination) {
		this.flightdestination = flightdestination;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getSearchId() {
		return searchId;
	}
	public void setSearchId(String searchId) {
		this.searchId = searchId;
	}
	
	
	
	
	
	
}
