package com.nestos.weathertrial.web;

import com.nestos.weathertrial.dto.CurrentWeatherForOneLocation;
import com.nestos.weathertrial.dto.LocalTimeForGeoLocation;
import com.nestos.weathertrial.dto.RestError;
import com.nestos.weathertrial.exception.RestServiceException;
import com.nestos.weathertrial.service.LocalTimeService;
import com.nestos.weathertrial.service.WeatherService;
import java.util.LinkedHashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;

/**
 * Weather REST controller.
 *
 * @author Roman Osipov
 */
@RestController
public class WeatherRestController {

    //------------------Logger-------------------------------------------------
    private final Logger logger = LoggerFactory.getLogger(WeatherRestController.class);

    //----------------------Fields---------------------------------------------
    public static final int FORECAST_DAYS_COUNT = 3;
    public static final String CITY_FIELD_NAME = "city";
    public static final String CURRENT_TEMPERATURE_FIELD_NAME = "currentTemperature";
    public static final String FORECAST_3DAYS_MIN_TEMPERATURE_FIELD_NAME
            = "forecast3DaysMinTemperature";
    public static final String WEATHER_SERVICE_PATH = "/weather";

    private WeatherService weatherService;

    private LocalTimeService localTimeService;

    //----------------------Getters&Setters------------------------------------
    /**
     * Sets the weather service.
     *
     * @param weatherService the weather service.
     */
    @Autowired(required = false) // false for test suite
    public void setWeatherService(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    /**
     * Sets the local time service.
     *
     * @param localTimeService the local time service.
     */
    @Autowired(required = false)
    public void setLocalTimeService(LocalTimeService localTimeService) {
        this.localTimeService = localTimeService;
    }

    //----------------------Methods--------------------------------------------
    @ExceptionHandler(RestServiceException.class)
    public ResponseEntity<RestError> restServiceExceptionHandler(RestServiceException e) {
        RestError restError
                = new RestError(e.getHttpStatus().value(), e.getMessage());
        return new ResponseEntity<>(restError, e.getHttpStatus());
    }

    @ExceptionHandler(RestClientException.class)
    public ResponseEntity<RestError> restClientExceptionHandler(RestClientException e) {
        RestError restError = new RestError(HttpStatus.SERVICE_UNAVAILABLE.value(),
                HttpStatus.SERVICE_UNAVAILABLE.getReasonPhrase());
        logger.debug(e.toString());
        return new ResponseEntity<>(restError, HttpStatus.SERVICE_UNAVAILABLE);
    }

    /**
     * Find and returns current temperature and forecast 3 days minimum temperature for the city.
     *
     * @param city the city name and country code divided by comma, use ISO 3166 country codes. Maps
     * to request parameter 'q'.
     * @param units temperature units format. Standard, metric, and imperial units are available.
     * Maps to request parameter 'units'. Avaliable values: imperial (Fahrenheit), metric(Celsius,
     * default), kelvin.
     * @return the map with city name, current temperature, forecast 3 days minimum temperature.
     * Response example:
     * {"city":"Moscow,RU","currentTemperature":28.1,"forecast3DaysMinTemperature":14.7}
     */
    @RequestMapping(
            path = WEATHER_SERVICE_PATH,
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> request(
            @RequestParam(value = "q", defaultValue = "") String city,
            @RequestParam(value = "units", defaultValue = "metric") String units) {
        CurrentWeatherForOneLocation currentWeather
                = weatherService.getCurrentWeatherForCity(city, units);
        LocalTimeForGeoLocation localTime
                = localTimeService.getLocalTimeForGeoLocation(currentWeather.getGeoLocation());
        Double minTemperature = weatherService
                .getForecastMinTemperature(city, units, FORECAST_DAYS_COUNT, localTime);
        Map<String, Object> map = new LinkedHashMap<>();
        map.put(CITY_FIELD_NAME, city);
        map.put(CURRENT_TEMPERATURE_FIELD_NAME, currentWeather.getMainData().getTemperature());
        map.put(FORECAST_3DAYS_MIN_TEMPERATURE_FIELD_NAME, minTemperature);
        return map;
    }

}
