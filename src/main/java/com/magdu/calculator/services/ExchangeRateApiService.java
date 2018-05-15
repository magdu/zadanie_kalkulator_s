package com.magdu.calculator.services;


import com.magdu.calculator.enums.Country;
import com.magdu.calculator.dto.ExchangeRate;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

@Service
public class ExchangeRateApiService {
  private static final Log LOG = LogFactory.getLog(ExchangeRateApiService.class);

  private static final String CURRENCY_TABLE = "a";

  private static final String URL_TEMPLATE = "http://api.nbp.pl/api/exchangerates/rates/%s/%s/";

  private RestTemplate restTemplate;

  @Autowired
  public ExchangeRateApiService(RestTemplateBuilder restTemplateBuilder) {
    restTemplate = restTemplateBuilder.build();
  }

  public ExchangeRate getRate(Country country) {
    ExchangeRate data = null;
    if (Country.PL.equals(country)) {
      data = new ExchangeRate("pln", LocalDateTime.now()
        .format(DateTimeFormatter.ofPattern("yyyy-MM-dd")), BigDecimal.ONE);
    } else {
      String url = String.format(URL_TEMPLATE, CURRENCY_TABLE, country.getCurrencyCode());
      try {
        HashMap<String, Object> headers = new HashMap<>();
        headers.put("Accept", "application/json");
        ResponseEntity<ExchangeRate> response = restTemplate.getForEntity(url, ExchangeRate.class, headers);
        if (response.getStatusCode().equals(HttpStatus.OK)) {
          data = response.getBody();
        }
      } catch (RestClientException ex) {
        LOG.error("Error when requesting: " + url, ex);
      }
    }
    return data;
  }

}
