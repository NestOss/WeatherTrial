package com.nestos.weathertrial.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

/**
 * Rain data transfer class.
 *
 * @author Roman Osipov
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Rain {

    //----------------------Fields---------------------------------------------
    @JsonProperty("3h")
    public Double volume3h;

    //----------------------Getters&Setters------------------------------------
    /**
     * Returns the rain volume for the last 3 hours.
     *
     * @return the rain volume for the last 3 hours.
     */
    public Double getVolume3h() {
        return volume3h;
    }

    /**
     * Sets the rain volume for the last 3 hours.
     *
     * @param volume3h the rain volume for the last 3 hours.
     */
    public void setVolume3h(Double volume3h) {
        this.volume3h = volume3h;
    }

    //----------------------Methods--------------------------------------------
    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
