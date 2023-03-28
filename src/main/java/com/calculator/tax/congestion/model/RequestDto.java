package com.calculator.tax.congestion.model;

import java.time.LocalTime;

import javax.persistence.Column;

public class RequestDto {
    private String city;
    private String vehicleNumber;
    private String vehicleType;
    
    
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getVehicleNumber() {
		return vehicleNumber;
	}
	public void setVehicleNumber(String vehicleNumber) {
		this.vehicleNumber = vehicleNumber;
	}
	public String getVehicleType() {
		return vehicleType;
	}
	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}
  
      
	
    // Getters and setters
    
    
}