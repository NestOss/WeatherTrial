package com.nestos.weathertrial.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

/**
 * Wind data transfer class.
 *
 * @author Roman Osipov
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Wind {

    //----------------------Fields---------------------------------------------
    @JsonProperty("speed")
    private Double speed;

    @JsonProperty("deg")
    private Double degrees;

    //----------------------Getters&Setters------------------------------------
    /**
     * Returns the wind speed.
     *
     * @return the wind speed.
     */
    public Double getSpeed() {
        return speed;
    }

    /**
     * Sets the wind speed.
     *
     * @param speed the wind speed.
     */
    public void setSpeed(Double speed) {
        this.speed = speed;
    }

    /**
     * Returns the wind direction, degrees (meteorological).
     *
     * @return the wind direction.
     */
    public Double getDegrees() {
        return degrees;
    }

    /**
     * Sets the wind direction, degrees (meteorological).
     *
     * @param degrees the wind direction.
     */
    public void setDegrees(Double degrees) {
        this.degrees = degrees;
    }

    //----------------------Methods--------------------------------------------
    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
