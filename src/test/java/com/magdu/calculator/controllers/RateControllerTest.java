package com.magdu.calculator.controllers;

import com.magdu.calculator.CalculatorSApplication;
import com.magdu.calculator.dto.MonthlyRate;
import com.magdu.calculator.enums.Country;
import com.magdu.calculator.services.RateCalculationService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CalculatorSApplication.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@AutoConfigureMockMvc
public class RateControllerTest {

  @Autowired
  private MockMvc mvc;

  @MockBean
  private RateCalculationService rateCalculationService;

  @Test
  public void whenCorrectData_expectCorrectResponse() throws Exception {
    Mockito.when(rateCalculationService.getMonthlyRate(Country.PL, BigDecimal.valueOf(200)))
      .thenReturn(new MonthlyRate(BigDecimal.valueOf(2364), Country.PL));
    mvc.perform(get("/rate?dailyRate=200&country=PL"))
      .andExpect(status().isOk())
      .andExpect(jsonPath("$.monthlyRate", is(2364)));
  }

  @Test
  public void whenRequiredParameterNotPresent_expectBadResponse() throws Exception {
    mvc.perform(get("/rate?dailyRate=200"))
      .andExpect(status().isBadRequest());
  }

  @Test
  public void whenIncorrectParameter_expectBadResponse() throws Exception {
    mvc.perform(get("/rate?dailyRate=200&country=US"))
      .andExpect(status().isBadRequest());
  }

  @Test
  public void whenSomethingWentWrong_expectInternalServerError() throws Exception {
    Mockito.when(rateCalculationService.getMonthlyRate(Country.PL, BigDecimal.valueOf(200)))
      .thenReturn(null);
    mvc.perform(get("/rate?dailyRate=200&country=PL"))
      .andExpect(status().isInternalServerError());
  }
}
