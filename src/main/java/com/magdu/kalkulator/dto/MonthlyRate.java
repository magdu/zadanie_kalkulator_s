package com.magdu.kalkulator.dto;

import com.magdu.kalkulator.Country;

import java.math.BigDecimal;

public class MonthlyRate {
    private BigDecimal monthlyRate;

    private Country country;

    public BigDecimal getMonthlyRate() {
        return monthlyRate;
    }

    public void setMonthlyRate(BigDecimal monthlyRate) {
        this.monthlyRate = monthlyRate;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }
}
