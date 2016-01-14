package com.nestos.weathertrial.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

/**
 * Geo location data transfer class.
 *
 * @author Roman Osipov
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class GeoLocation {

    //----------------------Fields---------------------------------------------
    @JsonProperty("lon")
    private Double longitude;

    @JsonProperty("lat")
    private Double latitude;

    //----------------------Getters&Setters------------------------------------
    /**
     * Returns the geo location longitude.
     *
     * @return the geo location longitude.
     */
    public Double getLongitude() {
        return longitude;
    }

    /**
     * Sets the geo location longitude.
     *
     * @param longitude the geo location longitude.
     */
    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    /**
     * Returns the geo location latitude.
     *
     * @return the geo location latitude.
     */
    public Double getLatitude() {
        return latitude;
    }

    /**
     * Sets the geo location latitude.
     *
     * @param latitude the geo location latitude.
     */
    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    //----------------------Methods--------------------------------------------
    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
