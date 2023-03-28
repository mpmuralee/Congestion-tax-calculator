package com.calculator.tax.congestion.repository;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.calculator.tax.congestion.model.CongestionTax;
import com.calculator.tax.congestion.model.RunningVehicleDetails;

@Repository
public interface RunningVehicleDetailsRepository extends JpaRepository<RunningVehicleDetails, Long> {
	 
	@Query("SELECT COALESCE(SUM(e.amount), 0) FROM RunningVehicleDetails e WHERE e.vehicleNumber = :vehicleNumber AND e.createdAt = CURRENT_DATE")
	int findAmountByVehicleNumberAndDate(@Param("vehicleNumber") String vehicleNumber);
	
	  @Query("SELECT COALESCE(SUM(r.amount), 0) FROM RunningVehicleDetails r WHERE r.vehicleNumber = :vehicleNumber AND r.createdAt = CURRENT_DATE AND r.createdTimeAt BETWEEN :oneHourAgo AND :now")
	    Integer getAmountByVehicleNumberAndCreatedAtTodayAndWithinOneHour(@Param("vehicleNumber") String vehicleNumber, @Param("oneHourAgo") Date oneHourAgo, @Param("now") Date now);
	  	  
	  List<RunningVehicleDetails> findByVehicleNumber(String vehicleNumber);
}


