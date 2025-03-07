package com.rnr.app.controller;

import com.rnr.app.ResourceHelper;
import com.rnr.app.data.WeatherData;
import com.rnr.app.service.WeatherService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIf;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(WeatherController.class)
@ExtendWith(MockitoExtension.class)
public class WeatherControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @InjectMocks
    private WeatherController weatherController;

    @MockitoBean
    private WeatherService weatherService;


    @Captor
    private ArgumentCaptor<String> argCaptor;

    @Test
    @EnabledIf("inWorkingHours")
    void testWeatherAllDataByCity() throws Exception{
        final String seville = "Seville";
        WeatherData weatherData = ResourceHelper.entityFromResource("weather.json", WeatherData.class);

        when(weatherService.getWeatherData(argCaptor.capture())).thenReturn(weatherData);
        MvcResult mvcResult = mockMvc.perform(
                MockMvcRequestBuilders
                    .get("/weatherApi/all/" + seville)
                    .contentType(MediaType.APPLICATION_JSON_VALUE))
                    .andExpect(status().isOk())
                .andReturn();
        WeatherData result = ResourceHelper.entityFromContent(
                mvcResult.getResponse().getContentAsString(), WeatherData.class);

        assertEquals(result, weatherData, () -> "Expected city different than expected");
        assertEquals(seville, argCaptor.getValue());
        verify(weatherService).getWeatherData(seville);
    }

    private boolean inWorkingHours() {
        LocalDateTime begin = LocalDateTime.now().withHour(9).withMinute(0).withSecond(0);
        LocalDateTime end = LocalDateTime.now().withHour(18).withMinute(0).withSecond(0);
        return !LocalDateTime.now().isBefore(begin) || !LocalDateTime.now().isAfter(end);
    }
}
