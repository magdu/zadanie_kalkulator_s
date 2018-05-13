package com.magdu.kalkulator.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ExchangeRate {
    private String currencyCode;

    private String effectiveDate;

    private BigDecimal mid;

    @JsonProperty("rates")
    private void unpackNested(Map<String, Object>[] rates) {
        this.effectiveDate = (String) rates[0].get("effectiveDate");
        this.mid = BigDecimal.valueOf((Double) rates[0].get("mid"));
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(String effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    public BigDecimal getMid() {
        return mid;
    }

}
