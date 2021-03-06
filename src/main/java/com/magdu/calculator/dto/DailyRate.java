package com.magdu.calculator.dto;

import com.magdu.calculator.enums.Country;

public class DailyRate {

    private Country country;
    private int dailyRate;

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public int getDailyRate() {
        return dailyRate;
    }

    public void setDailyRate(int dailyRate) {
        this.dailyRate = dailyRate;
    }
}
