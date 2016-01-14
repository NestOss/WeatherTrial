package com.nestos.weathertrial.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

/**
 * Weather data transfer class.
 *
 * @author Roman Osipov
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherCondition {

    //----------------------Fields---------------------------------------------
    @JsonProperty("id")
    private Long id;

    @JsonProperty("main")
    private String weatherParams;

    @JsonProperty("description")
    private String weatherParamsDescription;

    @JsonProperty("icon")
    private String iconId;

    //----------------------Getters&Setters------------------------------------
    /**
     * Returns the weather condition id.
     *
     * @return the weather condition id.
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the weather condition id.
     *
     * @param id the weather condition id.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Returns the group of weather parameters (Rain, Snow, Extreme etc.).
     *
     * @return the group of weather parameters.
     */
    public String getWeatherParams() {
        return weatherParams;
    }

    /**
     * Sets the group of weather parameters (Rain, Snow, Extreme etc.).
     *
     * @param weatherParams the group of weather parameters.
     */
    public void setWeatherParams(String weatherParams) {
        this.weatherParams = weatherParams;
    }

    /**
     * Returns the weather condition within the group.
     *
     * @return the weather condition within the group.
     */
    public String getWeatherParamsDescription() {
        return weatherParamsDescription;
    }

    /**
     * Sets the weather condition within the group.
     *
     * @param weatherParamsDescription the weather condition within the group.
     */
    public void setWeatherParamsDescription(String weatherParamsDescription) {
        this.weatherParamsDescription = weatherParamsDescription;
    }

    /**
     * Returns the weather icon id.
     *
     * @return the weather icon id.
     */
    public String getIconId() {
        return iconId;
    }

    /**
     * Sets the weather icon id.
     *
     * @param iconId the weather icon id.
     */
    public void setIconId(String iconId) {
        this.iconId = iconId;
    }

    //----------------------Methods--------------------------------------------
    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
