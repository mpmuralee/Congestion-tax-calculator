package com.calculator.tax.congestion.model;


import javax.persistence.*;

import org.hibernate.annotations.CreationTimestamp;
import java.util.Date;

@Entity
@Table(name = "running_vehicle_details")
public class RunningVehicleDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
     
    @Column(name = "amount")
    private int amount = 0;
    
    @Column(name = "city")
    private String city;
    
    @Column(name = "vehicle_number")
    private String vehicleNumber;
    
    @Column(name = "vehicle_type")
    private String vehicleType;
       
    @CreationTimestamp
    @Temporal(TemporalType.DATE)
    @Column(name = "created_at")
    private Date createdAt;
    
    
    @CreationTimestamp
    @Temporal(TemporalType.TIME)
    @Column(name = "created_time_at")
    private Date createdTimeAt;

    public RunningVehicleDetails() {}



	public RunningVehicleDetails(Long id, int amount, String city, String vehicleNumber, String vehicleType) {
		super();
		this.id = id;
		this.amount = amount;
		this.city = city;
		this.vehicleNumber = vehicleNumber;
		this.vehicleType = vehicleType;
	}



	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public int getAmount() {
		return amount;
	}



	public void setAmount(int amount) {
		this.amount = amount;
	}



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


}
