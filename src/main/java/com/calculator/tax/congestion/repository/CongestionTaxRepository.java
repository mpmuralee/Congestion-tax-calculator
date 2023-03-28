package com.calculator.tax.congestion.repository;

import java.time.LocalDateTime;
import java.time.LocalTime;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.calculator.tax.congestion.model.CongestionTax;

@Repository
public interface CongestionTaxRepository extends JpaRepository<CongestionTax, Long> {
//	 int getTotalAmountByCityAndTimeRange(String city, LocalTime fromTime, LocalTime toTime);	
	// int getTotalAmountByCityAndFromTimeAndToTime(String city, LocalTime localTime);
	 
	 @Query("SELECT COALESCE(SUM(e.amount), 0) FROM CongestionTax e WHERE e.city = :city AND e.fromTime <= :currentTime AND e.toTime >= :currentTime")
	    int findAmountByCityAndTime(@Param("city") String city, @Param("currentTime") LocalTime currentTime);
}
