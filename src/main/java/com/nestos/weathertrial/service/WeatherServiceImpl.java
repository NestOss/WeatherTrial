package com.nestos.weathertrial.service;

import com.nestos.weathertrial.domain.TimeInterval;
import com.nestos.weathertrial.dto.CurrentWeatherForOneLocation;
import com.nestos.weathertrial.dto.FiveDaysWeatherForecast;
import com.nestos.weathertrial.dto.LocalTimeForGeoLocation;
import com.nestos.weathertrial.dto.WeatherServiceResult;
import com.nestos.weathertrial.exception.RestServiceException;
import java.net.URI;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.NoSuchElementException;
import java.util.TimeZone;
import static org.apache.commons.lang3.Validate.inclusiveBetween;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * Weather service implementation.
 *
 * @author Roman Osipov
 */
@Service
public class WeatherServiceImpl implements WeatherService {

    //----------------------Fields---------------------------------------------
    private static final int LAST_HOUR_IN_DAY = 23;
    private static final int LAST_MINUTE_IN_HOUR = 59;
    private static final int FIVE_DAYS_LIMIT = 5;
    public final String PROVIDER_HOST = "openweathermap.org";
    public final String CURRENT_WEATHER_SERVICE_PATH = "/data/2.5/weather";
    public final String FIVE_DAYS_FORECAST_WEATHER_SERVICE_PATH = "/data/2.5/forecast";
    public final String CITY_QUALIFIER_PARAM_NAME = "q";
    public final String APPLICATION_ID_PARAM_NAME = "APPID";
    public final String UNITS_PARAM_NAME = "units";

    @Value("${openweathermap.appid}")
    private String APP_ID;

    private RestTemplate restTemplate;

    //----------------------Constructors---------------------------------------
    @Autowired(required = false) // false for test suit 
    public WeatherServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    //----------------------Methods--------------------------------------------
    /**
     * Returns the current weather for the city.
     *
     * @param cityName the city name and country code divided by comma, use ISO 3166 country codes.
     * @param units temperature units format. Avaliable values: imperial (Fahrenheit),
     * metric(Celsius), kelvin.
     * @return the current weather for the city.
     * @throw RestServiceException
     * @throw RestClientException
     */
    @Override
    public CurrentWeatherForOneLocation getCurrentWeatherForCity(String cityName, String units) {
        URI uri = UriComponentsBuilder.newInstance()
                .scheme("http").host(PROVIDER_HOST).path(CURRENT_WEATHER_SERVICE_PATH)
                .queryParam(CITY_QUALIFIER_PARAM_NAME, cityName)
                .queryParam(APPLICATION_ID_PARAM_NAME, APP_ID)
                .queryParam(UNITS_PARAM_NAME, units)
                .build().encode().toUri();
        ResponseEntity<CurrentWeatherForOneLocation> responseEntity
                = restTemplate.getForEntity(uri, CurrentWeatherForOneLocation.class);
        CurrentWeatherForOneLocation currentWeather = responseEntity.getBody();
        validateWeatherServiceResult(currentWeather, responseEntity, uri.toString());
        return currentWeather;
    }

    /**
     * Returns the five days weather forecast for city. Forecast data every 3 hours.
     *
     * @param cityName the city name and country code divided by comma, use ISO 3166 country codes.
     * @param units temperature units format. Avaliable values: imperial (Fahrenheit),
     * metric(Celsius), kelvin.
     * @return the five days weather forecast for city.
     * @throw RestServiceException
     * @throw RestClientException
     */
    @Override
    public FiveDaysWeatherForecast getFiveDaysWeatherForecastForCity(String cityName,
            String units) {
        URI uri = UriComponentsBuilder.newInstance()
                .scheme("http").host(PROVIDER_HOST).path(FIVE_DAYS_FORECAST_WEATHER_SERVICE_PATH)
                .queryParam(CITY_QUALIFIER_PARAM_NAME, cityName)
                .queryParam(APPLICATION_ID_PARAM_NAME, APP_ID)
                .queryParam(UNITS_PARAM_NAME, units)
                .build().encode().toUri();
        ResponseEntity<FiveDaysWeatherForecast> responseEntity
                = restTemplate.getForEntity(uri, FiveDaysWeatherForecast.class);
        FiveDaysWeatherForecast fiveDaysWeatherForecast = responseEntity.getBody();
        validateWeatherServiceResult(fiveDaysWeatherForecast, responseEntity, uri.toString());
        return fiveDaysWeatherForecast;
    }

    /**
     * Returns the forecast minimum temperature in the city for N days. N must be lesser or equals
     * then 5 and bigger then 0.
     *
     * @param cityName the city name and country code divided by comma, use ISO 3166 country codes.
     * @param units temperature units format. Avaliable values: imperial (Fahrenheit),
     * metric(Celsius), kelvin.
     * @param n forecast days count.
     * @param localTime local time for geo location.
     * @return the past N days minimum temperature in the city.
     * @throws IllegalArgumentException if param n is not valid.
     */
    @Override
    public double getForecastMinTemperature(String cityName, String units, int n,
            LocalTimeForGeoLocation localTime) {
        inclusiveBetween(1, FIVE_DAYS_LIMIT, n);
        TimeInterval forecastTimeInterval = getForecastTimeInterval(localTime.getTime(),
                localTime.getRawOffset(), n); // omitted daylight saving time
        FiveDaysWeatherForecast fiveDaysWeatherForecast
                = getFiveDaysWeatherForecastForCity(cityName, units);
        Double minTemperature = null;
        try {
            minTemperature = fiveDaysWeatherForecast.getWeatherForecasts().stream()
                    .filter(forecast
                            -> forecast.getTime().after(forecastTimeInterval.getStartDate())
                            && forecast.getTime().before(forecastTimeInterval.getEndDate()))
                    .mapToDouble(forecast -> forecast.getMainData().getMinTemperature()).min()
                    .getAsDouble();
        } catch (NoSuchElementException ex) { // null is acceptable value for minTemperature
        }
        return minTemperature;
    }

    /**
     * Validate WeatherServiceResult.
     *
     * @param result the WeatherServiceResult.
     * @param responseEntity the response entity.
     * @param serviceUriString the service uri string.
     * @throw RestServiceException
     * @throw RestClientException
     */
    private void validateWeatherServiceResult(WeatherServiceResult result,
            ResponseEntity<?> responseEntity, String serviceUriString) {
        if (result.getCod() == null) {
            result.setCod(responseEntity.getStatusCode().value());
        }
        if (result.getCod() != HttpStatus.OK.value()) {
            if (result.getMessage() == null) {
                result.setMessage(responseEntity.getStatusCode().getReasonPhrase());
            }
            result.setMessage(serviceUriString + " -> " + result.getMessage());
            throw new RestServiceException(HttpStatus.valueOf(result.getCod()),
                    result.getMessage());
        }
    }

    /**
     * Calculate N days forecast time interval, GMT. Time interval from 23:59 current day local time
     * to 23:59 (current day + N days) local time.
     *
     * @param currentTime the current time as GMT.
     * @param timeZone the timezone.
     * @return the N days forecast time interval, GMT.
     */
    private TimeInterval getForecastTimeInterval(Date currentTime, int timeZoneOffset,
            int forecastDaysCount) {
        TimeZone gmtTimeZone = TimeZone.getTimeZone("GMT");
        Calendar startTime = new GregorianCalendar(gmtTimeZone);
        startTime.setTime(currentTime);
        startTime.set(Calendar.HOUR_OF_DAY, LAST_HOUR_IN_DAY);
        startTime.set(Calendar.MINUTE, LAST_MINUTE_IN_HOUR);
        Calendar endTime = new GregorianCalendar(gmtTimeZone);
        endTime.setTime(startTime.getTime());
        endTime.add(Calendar.DATE, forecastDaysCount);
        // shift time back to GMT
        startTime.add(Calendar.HOUR_OF_DAY, -timeZoneOffset);
        endTime.add(Calendar.HOUR_OF_DAY, -timeZoneOffset);
        return new TimeInterval(startTime.getTime(), endTime.getTime());
    }
}
