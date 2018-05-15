package com.magdu.calculator.dto;

import com.magdu.calculator.enums.Country;

import java.math.BigDecimal;

public class MonthlyRate {
  private BigDecimal monthlyRate;

  private Country country;

  private ExchangeRate exchangeRate;

  public MonthlyRate(BigDecimal monthlyRate, Country country, ExchangeRate exchangeRate) {
    this.monthlyRate = monthlyRate;
    this.country = country;
    this.exchangeRate = exchangeRate;
  }

  public BigDecimal getMonthlyRate() {
    return monthlyRate;
  }

  public Country getCountry() {
    return country;
  }

  public ExchangeRate getExchangeRate() {
    return this.exchangeRate;
  }

}
