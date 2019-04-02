package com.start.envy.model;

import java.util.List;

public class ResponseVO {
	
	private Boolean success;
	private ResponseBody cabOrigin;
	private List<ResponseBody> flight;
	private Double startlatitude;
	private Double endLatitude;
	private Double startLongitude;
	private Double endLongitude;
	private ResponseBody cabDestination;
	private String searchId;
	private String message;
	private String date;
	private List<SearchDetails> sdList;
	
	
	public List<SearchDetails> getSdList() {
		return sdList;
	}
	public void setSdList(List<SearchDetails> sdList) {
		this.sdList = sdList;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getSearchId() {
		return searchId;
	}
	public void setSearchId(String searchId) {
		this.searchId = searchId;
	}
	public Boolean getSuccess() {
		return success;
	}
	public void setSuccess(Boolean success) {
		this.success = success;
	}
	public ResponseBody getCabOrigin() {
		return cabOrigin;
	}
	public void setCabOrigin(ResponseBody cabOrigin) {
		this.cabOrigin = cabOrigin;
	}
	public List<ResponseBody> getFlight() {
		return  flight;
	}
	public void setFlight(List<ResponseBody> flight) {
		this.flight = flight;
	}
	public Double getStartlatitude() {
		return startlatitude;
	}
	public void setStartlatitude(Double startlatitude) {
		this.startlatitude = startlatitude;
	}
	public Double getEndLatitude() {
		return endLatitude;
	}
	public void setEndLatitude(Double endLatitude) {
		this.endLatitude = endLatitude;
	}
	public Double getStartLongitude() {
		return startLongitude;
	}
	public void setStartLongitude(Double startLongitude) {
		this.startLongitude = startLongitude;
	}
	public Double getEndLongitude() {
		return endLongitude;
	}
	public void setEndLongitude(Double endLongitude) {
		this.endLongitude = endLongitude;
	}
	public ResponseBody getCabDestination() {
		return cabDestination;
	}
	public void setCabDestination(ResponseBody cabDestination) {
		this.cabDestination = cabDestination;
	}
	
	

}
