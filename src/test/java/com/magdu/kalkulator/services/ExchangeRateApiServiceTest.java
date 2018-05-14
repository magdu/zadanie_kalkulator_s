package com.magdu.kalkulator.services;

import com.magdu.kalkulator.enums.Country;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.MockRestServiceServer;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withServerError;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@RunWith(SpringRunner.class)
@RestClientTest(ExchangeRateApiService.class)
public class ExchangeRateApiServiceTest {

  @Autowired
  private MockRestServiceServer mockRestServiceServer;

  @Autowired
  private ExchangeRateApiService exchangeRateApiService;

  @Test
  public void whenContryUK_thenCorrectExchangeRate() throws IOException {
    String repositoryDataString = new String(
      Files.readAllBytes(Paths.get("./src/test/resources/gbp.json")));
    this.mockRestServiceServer.expect(requestTo("http://api.nbp.pl/api/exchangerates/rates/a/gbp/"))
      .andRespond(withSuccess(repositoryDataString, MediaType.APPLICATION_JSON));
    assertEquals(BigDecimal.valueOf(4.8314).compareTo(exchangeRateApiService.getRate(Country.UK).getMid()), 0);

  }

  @Test
  public void whenContryPoland_thenExpectNotFound() {
    assertEquals(BigDecimal.ONE.compareTo(exchangeRateApiService.getRate(Country.PL).getMid()), 0);
  }

  @Test
  public void whenApiDoesntRespond_thenNullRate() {
    this.mockRestServiceServer.expect(requestTo("http://api.nbp.pl/api/exchangerates/rates/a/gbp/"))
      .andRespond(withServerError());

    assertNull(exchangeRateApiService.getRate(Country.UK));
  }
}
