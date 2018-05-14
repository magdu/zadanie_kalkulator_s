package com.magdu.kalkulator.services;

import com.magdu.kalkulator.dto.ExchangeRate;
import com.magdu.kalkulator.dto.MonthlyRate;
import com.magdu.kalkulator.enums.Country;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
public class RateCalculationServiceTest {

  @TestConfiguration
  static class RateCalculationServiceTestContextConfiguration {

    @Bean
    public RateCalculationService rateCalculationService() {
      return new RateCalculationService();
    }
  }

  @Autowired
  private RateCalculationService rateCalculationService;

  @MockBean
  private ExchangeRateApiService exchangeRateApiService;

  @Test
  public void whenContryPoland_thenCorrectMonthlyRate() {
    Mockito.when(exchangeRateApiService.getRate(Country.PL))
      .thenReturn(new ExchangeRate("pln", "some date", BigDecimal.ONE));

    MonthlyRate monthlyRate = rateCalculationService.getMonthlyRate(Country.PL, BigDecimal.valueOf(100));

    assertEquals(BigDecimal.valueOf(582).compareTo(monthlyRate.getMonthlyRate()), 0);
  }

  @Test
  public void whenContryGermany_thenCorrectMonthlyRate() {
    Mockito.when(exchangeRateApiService.getRate(Country.DE))
      .thenReturn(new ExchangeRate("eur", "some date", BigDecimal.valueOf(4.2615)));

    MonthlyRate monthlyRate = rateCalculationService.getMonthlyRate(Country.DE, BigDecimal.valueOf(100));

    assertEquals(BigDecimal.valueOf(4091.04).compareTo(monthlyRate.getMonthlyRate()), 0);
  }

  @Test
  public void whenContryUK_thenCorrectMonthlyRate() {
    Mockito.when(exchangeRateApiService.getRate(Country.UK))
      .thenReturn(new ExchangeRate("gbp", "some date", BigDecimal.valueOf(4.8314)));

    MonthlyRate monthlyRate = rateCalculationService.getMonthlyRate(Country.UK, BigDecimal.valueOf(100));

    assertEquals(BigDecimal.valueOf(5072.97).compareTo(monthlyRate.getMonthlyRate()), 0);
  }
}
