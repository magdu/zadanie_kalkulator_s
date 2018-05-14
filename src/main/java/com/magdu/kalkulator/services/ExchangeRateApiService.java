package com.magdu.kalkulator.services;


import com.magdu.kalkulator.enums.Country;
import com.magdu.kalkulator.dto.ExchangeRate;
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

  public static final String CURRENCY_TABLE = "a";

  private final String URL_TEMPLATE = "http://api.nbp.pl/api/exchangerates/rates/%s/%s/";

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
        ResponseEntity<ExchangeRate> response = restTemplate.getForEntity(url, ExchangeRate.class);
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
