package com.magdu.calculator.enums;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum Country {
  PL("Poland", "pln", 19, 1200),
  DE("Germany", "eur", 20, 800),
  UK("United Kingdom", "gbp", 25, 600);

  private String name;
  private String currencyCode;
  private int taxes;
  private int fixedCosts;

  private Country(String name, String currencyCode, int taxes, int fixedCosts) {
    this.name = name;
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

  public String getSymbol() {
    return this.name();
  }

  public String getName() {
    return name;
  }
}
