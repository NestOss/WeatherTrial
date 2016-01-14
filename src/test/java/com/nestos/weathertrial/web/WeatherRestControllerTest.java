package com.nestos.weathertrial.web;

import com.nestos.weathertrial.dto.CurrentWeatherForOneLocation;
import com.nestos.weathertrial.dto.GeoLocation;
import com.nestos.weathertrial.dto.LocalTimeForGeoLocation;
import com.nestos.weathertrial.dto.MainData;
import com.nestos.weathertrial.exception.RestServiceException;
import com.nestos.weathertrial.service.LocalTimeService;
import com.nestos.weathertrial.service.WeatherService;
import com.nestos.weathertrial.web.WeatherRestControllerTest.WeatherRestControllerTestConfig;
import static org.hamcrest.Matchers.is;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.springframework.web.client.RestClientException;
import org.springframework.web.context.WebApplicationContext;

/**
 * Weather rest controller tests.
 *
 * @author Roman Osipov
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@SpringApplicationConfiguration(classes = WeatherRestControllerTestConfig.class)
@ActiveProfiles("test")
public class WeatherRestControllerTest {
    //-------------------Inner classes--------------------------------------------

    @Configuration
    @EnableAutoConfiguration
    public static class WeatherRestControllerTestConfig {

        @Bean
        public WeatherRestController weatherRestController() {
            return new WeatherRestController();
        }
    }

    //-------------------Constants------------------------------------------------
    private static final HttpStatus VALID_HTTP_ERROR_STATUS = HttpStatus.BAD_REQUEST;
    private static final String VALID_ERROR_MESSAGE = "This is error message.";
    private static final double VALID_TEMPERATURE = 24.5;
    private static final double VALID_MIN_TEMPERATURE = 21.3;
    private static final String VALID_CITY_NAME = "DEFAULT_CITY";
    private static final String VALID_UNITS_NAME = "metric";

    //-------------------Fields---------------------------------------------------
    @Autowired
    private WebApplicationContext webApplicationContext;

    @Mock
    private WeatherService weatherService;

    @Mock
    private LocalTimeService localTimeService;

    @Autowired
    @InjectMocks
    WeatherRestController weatherRestController;

    private MockMvc mockMvc;

    //-------------------Methods--------------------------------------------------
    private CurrentWeatherForOneLocation getValidCurrentWeatherForOneLocation() {
        CurrentWeatherForOneLocation currentWeather = new CurrentWeatherForOneLocation();
        MainData mainData = new MainData();
        mainData.setTemperature(VALID_TEMPERATURE);
        currentWeather.setMainData(mainData);
        GeoLocation geoLocation = new GeoLocation();
        currentWeather.setGeoLocation(geoLocation);
        return currentWeather;
    }

    private LocalTimeForGeoLocation getValidLocalTimeForGeoLocation() {
        return new LocalTimeForGeoLocation();
    }

    @Before
    public void setup() throws Exception {
        MockitoAnnotations.initMocks(this);
        mockMvc = webAppContextSetup(webApplicationContext).build();
    }
    
    @Test // Happy path.
    public void shouldReturnResponceBodyForWeatherRequest()
            throws Exception {
        // arrange
        CurrentWeatherForOneLocation currentWeather = getValidCurrentWeatherForOneLocation();
        LocalTimeForGeoLocation localTime = getValidLocalTimeForGeoLocation();
        when(weatherService.getCurrentWeatherForCity(VALID_CITY_NAME, VALID_UNITS_NAME))
                .thenReturn(currentWeather);
        when(localTimeService.getLocalTimeForGeoLocation(currentWeather.getGeoLocation()))
                .thenReturn(localTime);
        when(weatherService.getForecastMinTemperature(VALID_CITY_NAME, VALID_UNITS_NAME,
                WeatherRestController.FORECAST_DAYS_COUNT, localTime))
                .thenReturn(VALID_MIN_TEMPERATURE);
        // act & assert
        mockMvc.perform(get(WeatherRestController.WEATHER_SERVICE_PATH)
                .param("q", VALID_CITY_NAME).param("units", VALID_UNITS_NAME))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$." + WeatherRestController.CITY_FIELD_NAME,
                                is(VALID_CITY_NAME)))
                .andExpect(jsonPath("$." + WeatherRestController.CURRENT_TEMPERATURE_FIELD_NAME,
                                is(VALID_TEMPERATURE)))
                .andExpect(jsonPath("$." + WeatherRestController.FORECAST_3DAYS_MIN_TEMPERATURE_FIELD_NAME,
                                is(VALID_MIN_TEMPERATURE)));
    }
    
    @Test
    public void shouldReturnErrorBodyForWeatherServiceGetCurrentWeatherForCityRestClientException()
            throws Exception {
        // arrange 
        when(weatherService.getCurrentWeatherForCity(anyString(), anyString()))
                .thenThrow(new RestClientException("Exception"));
        // act & assert
        mockMvc.perform(get(WeatherRestController.WEATHER_SERVICE_PATH))
                .andExpect(status().is5xxServerError())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.cod",
                                is(HttpStatus.SERVICE_UNAVAILABLE.value())))
                .andExpect(jsonPath("$.message",
                                is(HttpStatus.SERVICE_UNAVAILABLE.getReasonPhrase())));
    }

    @Test
    public void shouldReturnErrorBodyForWeatherServiceGetCurrentWeatherForCityRestServiceException()
            throws Exception {
        // arrange 
        when(weatherService.getCurrentWeatherForCity(anyString(), anyString()))
                .thenThrow(new RestServiceException(VALID_HTTP_ERROR_STATUS, VALID_ERROR_MESSAGE));
        // act & assert
        mockMvc.perform(get(WeatherRestController.WEATHER_SERVICE_PATH))
                .andExpect(status().is(VALID_HTTP_ERROR_STATUS.value()))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.cod",
                                is(VALID_HTTP_ERROR_STATUS.value())))
                .andExpect(jsonPath("$.message",
                                is(VALID_ERROR_MESSAGE)));
    }

    @Test
    public void shouldReturnErrorBodyForLocalTimeServiceGetLocalTimeForGeoLocationRestClientException()
            throws Exception {
        // arrange 
        when(weatherService.getCurrentWeatherForCity(anyString(), anyString()))
                .thenReturn(getValidCurrentWeatherForOneLocation());
        when(localTimeService.getLocalTimeForGeoLocation(anyObject()))
                .thenThrow(new RestClientException("Exception"));
        // act & assert
        mockMvc.perform(get(WeatherRestController.WEATHER_SERVICE_PATH))
                .andExpect(status().is5xxServerError())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.cod",
                                is(HttpStatus.SERVICE_UNAVAILABLE.value())))
                .andExpect(jsonPath("$.message",
                                is(HttpStatus.SERVICE_UNAVAILABLE.getReasonPhrase())));
    }

    @Test
    public void shouldReturnErrorBodyForLocalTimeServiceGetLocalTimeForGeoLocationRestServiceException()
            throws Exception {
        // arrange 
        when(weatherService.getCurrentWeatherForCity(anyString(), anyString()))
                .thenReturn(getValidCurrentWeatherForOneLocation());
        when(localTimeService.getLocalTimeForGeoLocation(anyObject()))
                .thenThrow(new RestServiceException(VALID_HTTP_ERROR_STATUS, VALID_ERROR_MESSAGE));
        // act & assert
        mockMvc.perform(get(WeatherRestController.WEATHER_SERVICE_PATH))
                .andExpect(status().is(VALID_HTTP_ERROR_STATUS.value()))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.cod",
                                is(VALID_HTTP_ERROR_STATUS.value())))
                .andExpect(jsonPath("$.message",
                                is(VALID_ERROR_MESSAGE)));
    }

    @Test
    public void shouldReturnErrorBodyForWeatherServiceGetForecastMinTemperatureRestClientException()
            throws Exception {
        // arrange 
        when(weatherService.getCurrentWeatherForCity(anyString(), anyString()))
                .thenReturn(getValidCurrentWeatherForOneLocation());
        when(localTimeService.getLocalTimeForGeoLocation(anyObject()))
                .thenReturn(getValidLocalTimeForGeoLocation());
        when(weatherService.getForecastMinTemperature(anyString(), anyString(),
                anyInt(), anyObject())).thenThrow(new RestClientException("Exception"));
        // act & assert
        mockMvc.perform(get(WeatherRestController.WEATHER_SERVICE_PATH))
                .andExpect(status().is5xxServerError())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.cod",
                                is(HttpStatus.SERVICE_UNAVAILABLE.value())))
                .andExpect(jsonPath("$.message",
                                is(HttpStatus.SERVICE_UNAVAILABLE.getReasonPhrase())));
    }

    @Test
    public void shouldReturnErrorBodyForWeatherServiceGetForecastMinTemperatureRestServiceException()
            throws Exception {
        // arrange 
        when(weatherService.getCurrentWeatherForCity(anyString(), anyString()))
                .thenReturn(getValidCurrentWeatherForOneLocation());
        when(localTimeService.getLocalTimeForGeoLocation(anyObject()))
                .thenReturn(getValidLocalTimeForGeoLocation());
        when(weatherService.getForecastMinTemperature(anyString(), anyString(),
                anyInt(), anyObject())).thenThrow(
                        new RestServiceException(VALID_HTTP_ERROR_STATUS, VALID_ERROR_MESSAGE));
        // act & assert
        mockMvc.perform(get(WeatherRestController.WEATHER_SERVICE_PATH))
                .andExpect(status().is(VALID_HTTP_ERROR_STATUS.value()))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.cod",
                                is(VALID_HTTP_ERROR_STATUS.value())))
                .andExpect(jsonPath("$.message",
                                is(VALID_ERROR_MESSAGE)));
    }

}
