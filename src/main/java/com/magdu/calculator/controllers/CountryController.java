package com.magdu.calculator.controllers;

import com.magdu.calculator.enums.Country;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/countries", produces = "application/json")
public class CountryController {

  @RequestMapping(method = RequestMethod.GET)
  public ResponseEntity<Country[]> getCountries() {
    return new ResponseEntity<>(Country.values(), HttpStatus.OK);
  }
}
