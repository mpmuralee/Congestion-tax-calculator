package com.calculator.tax.congestion.controller;

import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.calculator.tax.congestion.model.CongestionTax;
import com.calculator.tax.congestion.model.RequestDto;
import com.calculator.tax.congestion.model.RunningVehicleDetails;
import com.calculator.tax.congestion.service.CongestionTaxService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;


@RestController
@RequestMapping("/congestion-tax")
@Api(value = "Congestion Tax API", tags = {"Congestion Tax"})
public class CongestionTaxController {

    @Autowired
    private CongestionTaxService congestionTaxService;

    @ApiOperation(value = "Get all congestion taxes configuration", response = CongestionTax.class, responseContainer = "List")
    @GetMapping("/congestion-tax")
    public List<CongestionTax> getAllCongestionTaxes() {
        return congestionTaxService.getAllCongestionTaxes();
    }

    @ApiOperation(value = "Save a congestion tax for any city which will be a rule for tax calculation", response = CongestionTax.class)
    @PostMapping("/congestion-tax")
    public ResponseEntity<CongestionTax> saveCongestionTax(@RequestBody CongestionTax congestionTax) {
        CongestionTax savedCongestionTax = congestionTaxService.saveCongestionTax(congestionTax);
        return ResponseEntity.ok(savedCongestionTax);
    }

    @ApiOperation(value = "Get running vehicle details by vehicle number", response = RunningVehicleDetails.class, responseContainer = "List")
    @GetMapping("/running-vehicle-details")
    public List<RunningVehicleDetails> getMyEntitiesByVehicleNumber(@ApiParam(value = "Vehicle number") @RequestParam String vehicleNumber) {
        return congestionTaxService.getMyEntitiesByVehicleNumber(vehicleNumber);
    }

    @ApiOperation(value = "Save running vehicle details applying all the charge and tax rules", response = RunningVehicleDetails.class)
    @PostMapping("/running-vehicle-details")
    public ResponseEntity<RunningVehicleDetails> saveRunningVehicleDetails(@RequestBody RunningVehicleDetails runningVehicleDetails) {
        RunningVehicleDetails savedRunningVehicleDetails = congestionTaxService.saveRunningVehicleDetails(runningVehicleDetails);
        return ResponseEntity.ok(savedRunningVehicleDetails);
    }
}
