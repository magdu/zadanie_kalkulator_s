package com.magdu.calculator.services;

import com.magdu.calculator.enums.Country;
import com.magdu.calculator.dto.ExchangeRate;
import com.magdu.calculator.dto.MonthlyRate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class RateCalculationService {

  private final static BigDecimal DAY_IN_MONTH = new BigDecimal(22);

  @Autowired
  private ExchangeRateApiService exchangeRateApiService;

  public MonthlyRate getMonthlyRate(Country country, BigDecimal dailyRate) {
    MonthlyRate monthlyRate = null;
    BigDecimal exchangeRateAmount;
    ExchangeRate exchangeRate = exchangeRateApiService.getRate(country);
    if (exchangeRate != null) {
      exchangeRateAmount = exchangeRate.getMid();
      BigDecimal bruttoMonthlyRate = dailyRate.multiply(DAY_IN_MONTH).multiply(exchangeRateAmount);
      BigDecimal taxes = bruttoMonthlyRate.multiply(BigDecimal.valueOf(country.getTaxes())).divide(new BigDecimal(100));
      BigDecimal fixedCosts = new BigDecimal(country.getFixedCosts()).multiply(exchangeRateAmount);
      BigDecimal finalMonthlyRate = bruttoMonthlyRate.subtract(taxes).subtract(fixedCosts);
      monthlyRate = new MonthlyRate(finalMonthlyRate, country, exchangeRate);
    }
    return monthlyRate;
  }

}
