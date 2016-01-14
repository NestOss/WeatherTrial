package com.nestos.weathertrial.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

/**
 * Clouds data transfer class.
 *
 * @author Roman Osipov
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Clouds {

    //----------------------Fields---------------------------------------------
    @JsonProperty("all")
    private Double cloudiness;

    //----------------------Getters&Setters------------------------------------
    /**
     * Returns the cloudiness, %.
     *
     * @return the cloudiness.
     */
    public Double getCloudiness() {
        return cloudiness;
    }

    /**
     * Sets the cloudiness, %.
     *
     * @param cloudiness the cloudiness.
     */
    public void setCloudiness(Double cloudiness) {
        this.cloudiness = cloudiness;
    }

    //----------------------Methods--------------------------------------------
    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }

}
