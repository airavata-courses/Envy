package com.start.envy.model;

public class ResponseVO {
	
	private Boolean success;
	private ResponseBody cabOrigin;
	private ResponseBody flight;
	private ResponseBody cabDestination;
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
	public ResponseBody getFlight() {
		return flight;
	}
	public void setFlight(ResponseBody flight) {
		this.flight = flight;
	}
	public ResponseBody getCabDestination() {
		return cabDestination;
	}
	public void setCabDestination(ResponseBody cabDestination) {
		this.cabDestination = cabDestination;
	}
	
	

}
