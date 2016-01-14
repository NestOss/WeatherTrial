package com.nestos.weathertrial.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

/**
 * Weather data transfer class.
 *
 * @author Roman Osipov
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Weather {

    //----------------------Fields---------------------------------------------
    @JsonProperty("weather")
    private List<WeatherCondition> weatherConditions;

    @JsonProperty("main")
    private MainData mainData;

    @JsonProperty("wind")
    private Wind wind;

    @JsonProperty("clouds")
    private Clouds clouds;

    @JsonProperty("rain")
    private Rain rain;

    @JsonProperty("snow")
    private Snow snow;

    //----------------------Getters&Setters------------------------------------
    /**
     * Returns the list of weather conditions (more info Weather condition codes).
     *
     * @return the weather conditions list.
     */
    public List<WeatherCondition> getWeatherConditions() {
        return weatherConditions;
    }

    /**
     * Sets the the list of weather conditions (more info Weather condition codes).
     *
     * @param weatherConditions the weather conditions list.
     */
    public void setWeatherConditions(List<WeatherCondition> weatherConditions) {
        this.weatherConditions = weatherConditions;
    }

    /**
     * Returns the main weather data.
     *
     * @return the main weather data.
     */
    public MainData getMainData() {
        return mainData;
    }

    /**
     * Sets the main weather data.
     *
     * @param mainData the main weather data.
     */
    public void setMainData(MainData mainData) {
        this.mainData = mainData;
    }

    /**
     * Returns the wind data.
     *
     * @return the wind data.
     */
    public Wind getWind() {
        return wind;
    }

    /**
     * Sets the wind data.
     *
     * @param wind the wind data.
     */
    public void setWind(Wind wind) {
        this.wind = wind;
    }

    /**
     * Returns the clouds data.
     *
     * @return the clouds data.
     */
    public Clouds getClouds() {
        return clouds;
    }

    /**
     * Sets the the clouds data.
     *
     * @param clouds the clouds data.
     */
    public void setClouds(Clouds clouds) {
        this.clouds = clouds;
    }

    /**
     * Returns the rain data.
     *
     * @return the rain data.
     */
    public Rain getRain() {
        return rain;
    }

    /**
     * Sets the rain data.
     *
     * @param rain the rain data.
     */
    public void setRain(Rain rain) {
        this.rain = rain;
    }

    /**
     * Returns the snow data.
     *
     * @return the snow data.
     */
    public Snow getSnow() {
        return snow;
    }

    /**
     * Sets the snow data.
     *
     * @param snow the snow data.
     */
    public void setSnow(Snow snow) {
        this.snow = snow;
    }

    //----------------------Methods--------------------------------------------
    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
