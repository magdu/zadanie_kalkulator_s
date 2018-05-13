package com.magdu.kalkulator.dto;

import com.magdu.kalkulator.Country;

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
