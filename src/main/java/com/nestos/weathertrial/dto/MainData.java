package com.nestos.weathertrial.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

/**
 * Main data transfer class.
 *
 * @author Roman Osipov
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class MainData {

    //----------------------Fields---------------------------------------------
    @JsonProperty("temp")
    private Double temperature;

    @JsonProperty("pressure")
    private Double pressure;

    @JsonProperty("humidity")
    private Double humidity;

    @JsonProperty("temp_min")
    private Double minTemperature;

    @JsonProperty("temp_max")
    private Double maxTemperature;

    @JsonProperty("sea_level")
    private Double seaLevelPressure;

    @JsonProperty("grnd_level")
    private Double groundLevelPressure;

    @JsonProperty("temp_kf")
    private Double tempKf;

    //----------------------Getters&Setters------------------------------------
    /**
     * Returns the current temperature.
     *
     * @return the current temperature.
     */
    public Double getTemperature() {
        return temperature;
    }

    /**
     * Sets the current temperature.
     *
     * @param temperature the current temperature.
     */
    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    /**
     * Returns the atmospheric pressure (on the sea level, if there is no sea_level or grnd_level
     * data), hPa.
     *
     * @return the atmospheric pressure.
     */
    public Double getPressure() {
        return pressure;
    }

    /**
     * Sets the atmospheric pressure (on the sea level, if there is no sea_level or grnd_level
     * data), hPa.
     *
     * @param pressure the atmospheric pressure.
     */
    public void setPressure(Double pressure) {
        this.pressure = pressure;
    }

    /**
     * Returns the humidity, %.
     *
     * @return the humidity.
     */
    public Double getHumidity() {
        return humidity;
    }

    /**
     * Sets the humidity, %.
     *
     * @param humidity the humidity.
     */
    public void setHumidity(Double humidity) {
        this.humidity = humidity;
    }

    /**
     * Returns the minimum temperature at the moment. This is deviation from current temp that is //
     * possible for large cities and megalopolises geographically expanded (use these // parameter
     * optionally).
     *
     * @return the minimum temperature at the moment.
     */
    public Double getMinTemperature() {
        return minTemperature;
    }

    /**
     * Sets the minimum temperature at the moment. This is deviation from current temp that is //
     * possible for large cities and megalopolises geographically expanded (use these // parameter
     * optionally).
     *
     * @param minTemperature the minimum temperature at the moment.
     */
    public void setMinTemperature(Double minTemperature) {
        this.minTemperature = minTemperature;
    }

    /**
     * Returns the maximum temperature at the moment. This is deviation from current temp that is
     * possible for large cities and megalopolises geographically expanded (use these parameter
     * optionally).
     *
     * @return the maximum temperature at the moment.
     */
    public Double getMaxTemperature() {
        return maxTemperature;
    }

    /**
     * Sets the maximum temperature at the moment. This is deviation from current temp that is
     * possible for large cities and megalopolises geographically expanded (use these parameter
     * optionally).
     *
     * @param maxTemperature the maximum temperature at the moment.
     */
    public void setMaxTemperature(Double maxTemperature) {
        this.maxTemperature = maxTemperature;
    }

    /**
     * Returns the atmospheric pressure on the sea level, hPa.
     *
     * @return the atmospheric pressure on the sea level.
     */
    public Double getSeaLevelPressure() {
        return seaLevelPressure;
    }

    /**
     * Sets the atmospheric pressure on the sea level, hPa.
     *
     * @param seaLevelPressure the atmospheric pressure on the sea level.
     */
    public void setSeaLevelPressure(Double seaLevelPressure) {
        this.seaLevelPressure = seaLevelPressure;
    }

    /**
     * Returns the atmospheric pressure on the ground level, hPa.
     *
     * @return the atmospheric pressure on the ground level.
     */
    public Double getGroundLevelPressure() {
        return groundLevelPressure;
    }

    /**
     * Sets the atmospheric pressure on the ground level, hPa.
     *
     * @param groundLevelPressure the atmospheric pressure on the ground level.
     */
    public void setGroundLevelPressure(Double groundLevelPressure) {
        this.groundLevelPressure = groundLevelPressure;
    }

    /**
     * Returns the internal parameter 'temp_kf'.
     *
     * @return the internal parameter 'temp_kf'.
     */
    public Double getTempKf() {
        return tempKf;
    }

    /**
     * Sets the internal parameter 'temp_kf'.
     *
     * @param tempKf the internal parameter 'temp_kf'.
     */
    public void setTempKf(Double tempKf) {
        this.tempKf = tempKf;
    }

    //----------------------Methods--------------------------------------------
    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
