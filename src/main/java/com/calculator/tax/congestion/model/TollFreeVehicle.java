package com.calculator.tax.congestion.model;


import javax.persistence.*;
import java.time.LocalTime;

@Entity
@Table(name = "toll_free_vehicle")
public class TollFreeVehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "vehicle_type")
    private String vehicleType;
    
    
    

    public TollFreeVehicle() {}

	public TollFreeVehicle(Long id, String vehicleType) {
		super();
		this.id = id;
		this.vehicleType = vehicleType;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getVehicleType() {
		return vehicleType;
	}

	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}

   
    
    
    
}


