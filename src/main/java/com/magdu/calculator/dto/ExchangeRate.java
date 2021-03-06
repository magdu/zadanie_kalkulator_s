package com.magdu.calculator.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ExchangeRate {
  private String currencyCode;

  private String effectiveDate;

  private BigDecimal mid;

  @JsonCreator
  public ExchangeRate(@JsonProperty("currencyCode") String currencyCode, @JsonProperty("rates") Map<String, Object>[] rates) {
    this.currencyCode = currencyCode;
    this.effectiveDate = (String) rates[0].get("effectiveDate");
    this.mid = BigDecimal.valueOf((Double) rates[0].get("mid"));
  }

  public ExchangeRate(String currencyCode, String effectiveDate, BigDecimal mid) {
    this.currencyCode = currencyCode;
    this.effectiveDate = effectiveDate;
    this.mid = mid;
  }

  public String getCurrencyCode() {
    return currencyCode;
  }

  public String getEffectiveDate() {
    return effectiveDate;
  }


  public BigDecimal getMid() {
    return mid;
  }

}
