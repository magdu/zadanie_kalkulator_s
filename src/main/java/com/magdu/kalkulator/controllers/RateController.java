package com.magdu.kalkulator.controllers;

import com.magdu.kalkulator.Country;
import com.magdu.kalkulator.dto.MonthlyRate;
import com.magdu.kalkulator.services.RateCalculationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping(value = "/rate", produces = "application/json")
public class RateController {

    @Autowired
    private RateCalculationService rateCalculationService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<MonthlyRate> computeRate(
            @RequestParam(value = "dailyRate") BigDecimal dailyRate, @RequestParam(value = "country") Country country) {
        MonthlyRate monthlyRate = rateCalculationService.getMonthlyRate(country, dailyRate);
        return new ResponseEntity<>(monthlyRate, HttpStatus.OK);
    }
}
