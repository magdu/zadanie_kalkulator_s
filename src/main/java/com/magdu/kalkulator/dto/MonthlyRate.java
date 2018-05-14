package com.magdu.kalkulator.dto;

import com.magdu.kalkulator.enums.Country;

import java.math.BigDecimal;

public class MonthlyRate {
  private BigDecimal monthlyRate;

  private Country country;

  public MonthlyRate(BigDecimal monthlyRate, Country country) {
    this.monthlyRate = monthlyRate;
    this.country = country;
  }

  public BigDecimal getMonthlyRate() {
    return monthlyRate;
  }

  public Country getCountry() {
    return country;
  }

}
