package com.nestos.weathertrial.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.nestos.weathertrial.json.UnixTimestampDeserializer;
import java.util.Date;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

/**
 * Current weather for one location data transfer class.
 *
 * @author Roman Osipov
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CurrentWeatherForOneLocation extends Weather implements WeatherServiceResult {

    //----------------------Fields---------------------------------------------
    @JsonProperty("coord")
    private GeoLocation geoLocation;

    @JsonProperty("base")
    private String base;

    @JsonProperty("visibility")
    private Double visibility;

    @JsonDeserialize(using = UnixTimestampDeserializer.class)
    @JsonProperty("dt")
    private Date calculateTime;

    @JsonProperty("sys")
    private Sys sys;

    @JsonProperty("id")
    private Long cityId;

    @JsonProperty("name")
    private String cityName;

    @JsonProperty("cod")
    private Integer cod;

    @JsonProperty("message")
    private String message;

    //----------------------Getters&Setters------------------------------------
    /**
     * Returns the city geo location.
     *
     * @return the city geo location.
     */
    public GeoLocation getGeoLocation() {
        return geoLocation;
    }

    /**
     * Sets the city geo location.
     *
     * @param geoLocation geo location.
     */
    public void setGeoLocation(GeoLocation geoLocation) {
        this.geoLocation = geoLocation;
    }

    /**
     * Returns the internal parameter 'base'.
     *
     * @return the internal parameter 'base'
     */
    public String getBase() {
        return base;
    }

    /**
     * Sets the internal parameter 'base'.
     *
     * @param base internal parameter 'base'.
     */
    public void setBase(String base) {
        this.base = base;
    }

    /**
     * Returns the visibility, meter.
     *
     * @return the visibility.
     */
    public Double getVisibility() {
        return visibility;
    }

    /**
     * Set the visibility, meter.
     *
     * @param visibility the visibility.
     */
    public void setVisibility(Double visibility) {
        this.visibility = visibility;
    }

    /**
     * Returns the time of data calculation, unix, UTC.
     *
     * @return the time of data calculation.
     */
    public Date getCalculateTime() {
        return calculateTime;
    }

    /**
     * Sets the time of data calculation, unix, UTC.
     *
     * @param calculateTime the time of data calculation.
     */
    public void setCalculateTime(Date calculateTime) {
        this.calculateTime = calculateTime;
    }

    /**
     * Returns the extra weather data.
     *
     * @return the extra weather data.
     */
    public Sys getSys() {
        return sys;
    }

    /**
     * Sets the extra weather data.
     *
     * @param sys the extra weather data.
     */
    public void setSys(Sys sys) {
        this.sys = sys;
    }

    /**
     * Returns the city ID.
     *
     * @return the city ID.
     */
    public Long getCityId() {
        return cityId;
    }

    /**
     * Sets the city ID.
     *
     * @param cityId the city ID.
     */
    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

    /**
     * Returns the city name.
     *
     * @return the city name.
     */
    public String getCityName() {
        return cityName;
    }

    /**
     * Sets the city name.
     *
     * @param cityName the city name.
     */
    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    /**
     * Returns the internal parameter 'cod'.
     *
     * @return the internal parameter 'cod'.
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
     * Returns the error message.
     *
     * @return the error message.
     */
    @Override
    public String getMessage() {
        return message;
    }

    /**
     * Sets the error message.
     *
     * @param message the error message.
     */
    @Override
    public void setMessage(String message) {
        this.message = message;
    }

    //----------------------Methods--------------------------------------------
    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, null, false, false, Weather.class);
    }

}
