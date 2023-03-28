package com.calculator.tax.congestion.service;

import com.calculator.tax.congestion.model.CongestionTax;
import com.calculator.tax.congestion.model.RunningVehicleDetails;
import com.calculator.tax.congestion.model.TollFreeVehicle;
import com.calculator.tax.congestion.repository.CongestionTaxRepository;
import com.calculator.tax.congestion.repository.RunningVehicleDetailsRepository;
import com.calculator.tax.congestion.repository.TollFreeVehicleRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.Calendar;
import org.springframework.stereotype.Service;


@Service
public class CongestionTaxService {
	
	// holidays of Gothenburg during 2013
	
	@Value("${congestion.tax.holidays.required.Gothenburg}")
	private boolean holidayRequired;
	
	@Value("${congestion.tax.Nextholidays.required.Gothenburg}")
	private boolean holidayNextDayRequired;
	
	@Value("${congestion.tax.julyholiday.required.Gothenburg}")
	private boolean holidayJulyRequired;
	
	@Value("${congestion.tax.satholiday.required.Gothenburg}")
	private boolean holidaySatRequired;
	
	@Value("${congestion.tax.sunholiday.required.Gothenburg}")
	private boolean holidaySunRequired;
	
	
	
	
	private final Set<LocalDate> holidays = Set.of(
            LocalDate.of(2013, 1, 1),
            LocalDate.of(2013, 3, 29),
            LocalDate.of(2013, 4, 1),
            LocalDate.of(2013, 4, 30),
            LocalDate.of(2013, 5, 1),
            LocalDate.of(2013, 5, 9),
            LocalDate.of(2013, 5, 19),
            LocalDate.of(2013, 6, 6),
            LocalDate.of(2013, 6, 22),
            LocalDate.of(2013, 11, 2),
            LocalDate.of(2013, 12, 24),
            LocalDate.of(2013, 12, 25),
            LocalDate.of(2013, 12, 26),
            LocalDate.of(2013, 12, 31)
);
   
    @Autowired
    private CongestionTaxRepository congestionTaxRepository;
    
    @Autowired
    private RunningVehicleDetailsRepository runningVehicleDetailsRepository;
    
    @Autowired
    private TollFreeVehicleRepository tollFreeVehicleRepository;
    

    public List<CongestionTax> getAllCongestionTaxes() {
    	System.out.println("holidayRequired : " + holidayRequired);
        return congestionTaxRepository.findAll(); 
    }   
    
    public List<RunningVehicleDetails> getMyEntitiesByVehicleNumber(String vehicleNumber) {
        return runningVehicleDetailsRepository.findByVehicleNumber(vehicleNumber);
    }
    
    
    public CongestionTax saveCongestionTax(CongestionTax congestionTax) {
        return congestionTaxRepository.save(congestionTax);
    }
    
    public RunningVehicleDetails saveRunningVehicleDetails(RunningVehicleDetails runningVehicleDetails) {
    	
    	Date date = new Date();
    	LocalDateTime localDateTime = LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
    
    	

    	// Get the current date and time
    	Calendar cal = Calendar.getInstance();

    	// Subtract one hour from the current date
    	cal.add(Calendar.HOUR_OF_DAY, -1);

    	// Get the new date and time
    	Date oneHourAgo = cal.getTime();
    	
    	
    	int currentAmount = this.getCurrentAmountByCityAndTimeRange(runningVehicleDetails.getCity(), runningVehicleDetails.getVehicleType(), runningVehicleDetails.getVehicleNumber());
    	int prevTotalAmount = runningVehicleDetailsRepository.findAmountByVehicleNumberAndDate(runningVehicleDetails.getVehicleNumber());
    	
    	int prevAmountWithin60Minutes = runningVehicleDetailsRepository.getAmountByVehicleNumberAndCreatedAtTodayAndWithinOneHour(runningVehicleDetails.getVehicleNumber(), oneHourAgo, date);
    	
    	
    	int totalAmount = currentAmount + prevTotalAmount;
    	
    	if(totalAmount>=60) {    		
    		currentAmount = 60 - prevTotalAmount;
    	}
    	  
    	if(prevAmountWithin60Minutes>=currentAmount)
    		currentAmount = 0;
    	else
    		currentAmount = currentAmount - prevAmountWithin60Minutes;
       	
    	
    	runningVehicleDetails.setAmount(currentAmount);    		
        return runningVehicleDetailsRepository.save(runningVehicleDetails);
    }
    
    
    public int getCurrentAmountByCityAndTimeRange(String city, String vehicleType, String vehicleNumber) {
    	Date date = new Date();
    	LocalDateTime localDateTime = LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
    	LocalTime localTime = localDateTime.toLocalTime();
    	int amount = 0;
    	
    	// Logics to calculate the Tax amount for the passsing vehicle
    	
    	TollFreeVehicle tollFreeVehicle =  tollFreeVehicleRepository.findByVehicleType(vehicleType);    	
    	if (tollFreeVehicle != null) 
    		amount = 0;
    	else if(isTollFreeDate(date))     		
    	  amount = 0;
    	else
    		amount = congestionTaxRepository.findAmountByCityAndTime(city, localTime);
    	    	
        return amount;
       
    } 
    
     
        
    public boolean isTollFreeDate(Date date) {
        LocalDateTime localDateTime = LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
        LocalDate localDate = localDateTime.toLocalDate();
        LocalDate localNextDate = localDateTime.toLocalDate().plusDays(1);
        LocalTime localTime = localDateTime.toLocalTime();

        // Check if it's a holiday and also next date is holiday
        if ((holidays.contains(localDate) && holidayRequired) || (holidays.contains(localNextDate) && holidayNextDayRequired)) {
            return true;
        }

        // Check if it's a weekend day
        if ((localDate.getDayOfWeek() == DayOfWeek.SATURDAY && holidaySatRequired) || (localDate.getDayOfWeek() == DayOfWeek.SUNDAY && holidaySunRequired)) {
            return true;
        }

        // Check if it's during the month of July
        if (localDate.getMonth() == Month.JULY && holidayJulyRequired) {
            return true;
        }

        // Check if it's within tolling hours
        if (localTime.isAfter(LocalTime.of(18, 30)) && localTime.isBefore(LocalTime.of(05, 59))) {
            return true;
        } 
        
        return false;
    }
    
    
}
