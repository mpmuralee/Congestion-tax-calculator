package com.calculator.tax.congestion.repository;


import com.calculator.tax.congestion.model.TollFreeVehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TollFreeVehicleRepository extends JpaRepository<TollFreeVehicle, Long> {

    TollFreeVehicle findByVehicleType(String vehicleType);

}