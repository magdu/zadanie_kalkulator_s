package com.magdu.kalkulator;

public enum Country {
    PL("pln", 19, 1200),
    DE("eur", 20, 800),
    UK("gbp", 25, 600);

    private String currencyCode;
    private int taxes;
    private int fixedCosts;

    private Country(String currencyCode, int taxes, int fixedCosts) {
        this.currencyCode = currencyCode;
        this.taxes = taxes;
        this.fixedCosts = fixedCosts;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public int getTaxes() {
        return taxes;
    }

    public int getFixedCosts() {
        return fixedCosts;
    }
}
