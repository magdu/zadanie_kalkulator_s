package com.magdu.kalkulator.controllers;

import com.magdu.kalkulator.enums.Country;
import com.magdu.kalkulator.dto.MonthlyRate;
import com.magdu.kalkulator.services.RateCalculationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Min;
import java.math.BigDecimal;

@RestController
@Validated
@RequestMapping(value = "/rate", produces = "application/json")
public class RateController {

  @Autowired
  private RateCalculationService rateCalculationService;

  @RequestMapping(method = RequestMethod.GET)
  public ResponseEntity<MonthlyRate> computeRate(@Min(1)
                                                 @RequestParam(value = "dailyRate") BigDecimal dailyRate, @RequestParam(value = "country") Country country) {
    MonthlyRate monthlyRate = rateCalculationService.getMonthlyRate(country, dailyRate);
    HttpStatus responseStatus = HttpStatus.OK;
    if (monthlyRate == null) {
      responseStatus = HttpStatus.INTERNAL_SERVER_ERROR;
    }
    return new ResponseEntity<>(monthlyRate, responseStatus);
  }
}
