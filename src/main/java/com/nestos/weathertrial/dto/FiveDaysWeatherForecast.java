package com.nestos.weathertrial.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

/**
 * Five days forecast data transfer class.
 *
 * @author Roman Osipov
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class FiveDaysWeatherForecast implements WeatherServiceResult {

    //----------------------Fields---------------------------------------------
    @JsonProperty("city")
    private City city;

    @JsonProperty("cod")
    private Integer cod;

    @JsonProperty("message")
    private String message;

    @JsonProperty("cnt")
    private Integer weatherForecastsListSize;

    @JsonProperty("list")
    private List<WeatherForecast> weatherForecasts;

    //----------------------Getters&Setters------------------------------------
    /**
     * Returns the city.
     *
     * @return the city.
     */
    public City getCity() {
        return city;
    }

    /**
     * Sets the city.
     *
     * @param city the city.
     */
    public void setCity(City city) {
        this.city = city;
    }

    /**
     * Returns the internal parameter 'code'.
     *
     * @return the internal parameter 'code'.
     */
    @Override
    public Integer getCod() {
        return cod;
    }

    /**
     * Sets the internal parameter 'cod'.
     *
     * @param cod the internal parameter 'cod'.
     */
    @Override
    public void setCod(Integer cod) {
        this.cod = cod;
    }

    /**
     * Returns the internal parameter 'message'.
     *
     * @return the internal parameter 'message'.
     */
    @Override
    public String getMessage() {
        return message;
    }

    /**
     * Sets the internal parameter 'message'.
     *
     * @param message the internal parameter 'message'.
     */
    @Override
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Returns the weather forecast list size.
     *
     * @return the forecast list size.
     */
    public Integer getWeatherForecastsListSize() {
        return weatherForecastsListSize;
    }

    /**
     * Sets the weather forecast list size.
     *
     * @param weatherForecastsListSize the forecast list size.
     */
    public void setWeatherForecastsListSize(Integer weatherForecastsListSize) {
        this.weatherForecastsListSize = weatherForecastsListSize;
    }

    /**
     * Returns the list of weather forecasts.
     *
     * @return the list of weather forecasts.
     */
    public List<WeatherForecast> getWeatherForecasts() {
        return weatherForecasts;
    }

    /**
     * Sets the list of weather forecasts.
     *
     * @param weatherForecasts the list of weather forecasts.
     */
    public void setWeatherForecasts(List<WeatherForecast> weatherForecasts) {
        this.weatherForecasts = weatherForecasts;
    }

    //----------------------Methods--------------------------------------------
    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }

}
