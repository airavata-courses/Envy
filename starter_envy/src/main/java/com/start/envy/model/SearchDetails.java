package com.start.envy.model;

import java.io.Serializable;

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
	@Override
	public String toString() {
		return "SearchDetails [carcarrier=" + carcarrier + ", flightcarrier=" + flightcarrier + ", totalprice="
				+ totalprice + ", carsource=" + carsource + ", cardestination=" + cardestination + ", flightsource="
				+ flightsource + ", flightdestination=" + flightdestination + ", date=" + date + ", searchId="
				+ searchId + ", carSourcePrice=" + carSourcePrice + ", carDestinationPrice=" + carDestinationPrice
				+ ", flightPrice=" + flightPrice + "]";
	}
	
	private String carcarrier;
	private String flightcarrier;
	private Double totalprice;
	private String carsource;
	private String cardestination;
	private String flightsource;
	private String flightdestination;
	private String date;
	private String searchId;
	private Double carSourcePrice;
	private Double carDestinationPrice;
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
