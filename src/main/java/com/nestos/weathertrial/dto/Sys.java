package com.nestos.weathertrial.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.nestos.weathertrial.json.UnixTimestampDeserializer;
import java.util.Date;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

/**
 * Sys data transfer class.
 *
 * @author Roman Osipov
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Sys {

    //----------------------Fields---------------------------------------------
    @JsonProperty("type")
    private Long type;

    @JsonProperty("id")
    private Long id;

    @JsonProperty("message")
    private Double message;

    @JsonProperty("country")
    private String country;

    @JsonDeserialize(using = UnixTimestampDeserializer.class)
    @JsonProperty("sunrise")
    private Date sunriseTime;

    @JsonDeserialize(using = UnixTimestampDeserializer.class)
    @JsonProperty("sunset")
    private Date sunsetTime;

    //----------------------Getters&Setters------------------------------------
    /**
     * Returns the internal parameter 'type'.
     *
     * @return the internal parameter 'type'.
     */
    public Long getType() {
        return type;
    }

    /**
     * Sets the internal parameter 'type'.
     *
     * @param type the internal parameter 'type'.
     */
    public void setType(Long type) {
        this.type = type;
    }

    /**
     * Returns the internal parameter 'id'.
     *
     * @return the internal parameter 'id'.
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the internal parameter 'id'.
     *
     * @param id the internal parameter 'id'.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Returns the internal parameter 'message'.
     *
     * @return the internal parameter 'message'.
     */
    public Double getMessage() {
        return message;
    }

    /**
     * Sets the internal parameter 'message'.
     *
     * @param message internal parameter 'message'.
     */
    public void setMessage(Double message) {
        this.message = message;
    }

    /**
     * Returns the country code (GB, JP etc.).
     *
     * @return the country code.
     */
    public String getCountry() {
        return country;
    }

    /**
     * Sets the country code (GB, JP etc.).
     *
     * @param country the country code.
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * Returns the sunrise time, unix, UTC.
     *
     * @return the sunrise time.
     */
    public Date getSunriseTime() {
        return sunriseTime;
    }

    /**
     * Sets the sunrise time, unix, UTC.
     *
     * @param sunriseTime the sunrise time.
     */
    public void setSunriseTime(Date sunriseTime) {
        this.sunriseTime = sunriseTime;
    }

    /**
     * Returns the sunset time, unix, UTC.
     *
     * @return the sunset time.
     */
    public Date getSunsetTime() {
        return sunsetTime;
    }

    /**
     * Sets the sunset time, unix, UTC.
     *
     * @param sunsetTime the sunset time.
     */
    public void setSunsetTime(Date sunsetTime) {
        this.sunsetTime = sunsetTime;
    }

    //----------------------Methods--------------------------------------------
    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
