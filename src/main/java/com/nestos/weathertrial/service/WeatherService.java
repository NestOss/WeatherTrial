package com.nestos.weathertrial.service;

import com.nestos.weathertrial.dto.CurrentWeatherForOneLocation;
import com.nestos.weathertrial.dto.FiveDaysWeatherForecast;
import com.nestos.weathertrial.dto.LocalTimeForGeoLocation;

/**
 * Weather service interface.
 *
 * @author Roman Osipov
 */
public interface WeatherService {

    /**
     * Returns the current weather for the city.
     *
     * @param cityName the city name and country code divided by comma, use ISO 3166 country codes.
     * @param units temperature units format. Avaliable values: imperial (Fahrenheit),
     * metric(Celsius), kelvin.
     * @return the current weather for the city.
     */
    CurrentWeatherForOneLocation getCurrentWeatherForCity(String cityName, String units);

    /**
     * Returns the five days weather forecast for city. Forecast data every 3 hours.
     *
     * @param cityName the city name and country code divided by comma, use ISO 3166 country codes.
     * @param units temperature units format. Avaliable values: imperial (Fahrenheit),
     * metric(Celsius), kelvin.
     * @return the five days weather forecast for city.
     */
    FiveDaysWeatherForecast getFiveDaysWeatherForecastForCity(String cityName, String units);

    /**
     * Returns the forecast minimum temperature in the city for N days. N must be lesser or equals
     * then 5.
     *
     * @param cityName the city name and country code divided by comma, use ISO 3166 country codes.
     * @param units temperature units format. Avaliable values: imperial (Fahrenheit),
     * metric(Celsius), kelvin.
     * @param n forecast days count.
     * @param localTime local time for geo location.
     * @return the past 3 days minimum temperature in the city.
     */
    double getForecastMinTemperature(String cityName, String units, int n,
            LocalTimeForGeoLocation localTime);

}
