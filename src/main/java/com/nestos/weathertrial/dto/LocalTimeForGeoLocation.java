package com.nestos.weathertrial.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;
import java.util.Map;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

/**
 * Local time for geo location - data transfer class.
 *
 * @author Roman Osipov
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class LocalTimeForGeoLocation {

    //----------------------Fields---------------------------------------------
    private final static String TIME_FORMAT_PATTERN = "yyyy-MM-dd HH:mm";

    @JsonProperty("time")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = TIME_FORMAT_PATTERN, timezone = "GMT")
    private Date time;

    @JsonProperty("timezoneId")
    private String timezoneId;

    @JsonProperty("rawOffset")
    private Integer rawOffset;

    @JsonProperty("lng")
    private Double longitude;

    @JsonProperty("lat")
    private Double latitude;

    @JsonProperty("sunrise")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = TIME_FORMAT_PATTERN, timezone = "GMT")
    private Date sunrise;

    @JsonProperty("sunset")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = TIME_FORMAT_PATTERN, timezone = "GMT")
    private Date sunset;

    @JsonProperty("gmtOffset")
    private Integer gmtOffset;

    @JsonProperty("dstOffset")
    private Integer dstOffset;

    @JsonProperty("countryCode")
    private String countryCode;

    @JsonProperty("countryName")
    private String countryName;

    @JsonProperty("status")
    private Map<String, String> errorStatus;

    //----------------------Getters&Setters------------------------------------
    /**
     * Returns the local current time.
     *
     * @return the local current time.
     */
    public Date getTime() {
        return time;
    }

    /**
     * Sets the local current time.
     *
     * @param time the local current time.
     *
     */
    public void setTime(Date time) {
        this.time = time;
    }

    /**
     * Returns the name of the timezone (according to TimeZone format).
     *
     * @return the name of the timezone.
     */
    public String getTimezoneId() {
        return timezoneId;
    }

    /**
     * Sets the name of the timezone (according to TimeZone format).
     *
     * @param timezoneId the name of the timezone.
     */
    public void setTimezoneId(String timezoneId) {
        this.timezoneId = timezoneId;
    }

    /**
     * Returns the amount of time in hours to add to UTC to get standard time in this time zone.
     * Because this value is not affected by daylight saving time, it is called raw offset.
     *
     * @return the amount of time in hours to add to UTC to get standard time in this time zone.
     */
    public Integer getRawOffset() {
        return rawOffset;
    }

    /**
     * Sets the amount of time in hours to add to UTC to get standard time in this time zone.
     * Because this value is not affected by daylight saving time, it is called raw offset.
     *
     * @param rawOffset the amount of time in hours to add to UTC to get standard time in this time
     * zone.
     */
    public void setRawOffset(Integer rawOffset) {
        this.rawOffset = rawOffset;
    }

    /**
     * Returns the geo location longitude, degrees.
     *
     * @return the geo location longitude.
     */
    public Double getLongitude() {
        return longitude;
    }

    /**
     * Sets the geo location longitude, degrees.
     *
     * @param longitude the geo location longitude.
     */
    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    /**
     * Returns the geo location latitude, degrees.
     *
     * @return the geo location latitude.
     */
    public Double getLatitude() {
        return latitude;
    }

    /**
     * Sets the the geo location latitude, degrees.
     *
     * @param latitude the geo location latitude.
     */
    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    /**
     * Returns the sunrise local time.
     *
     * @return the sunrise local time.
     */
    public Date getSunrise() {
        return sunrise;
    }

    /**
     * Sets the sunrise local time.
     *
     * @param sunrise the sunrise local time.
     */
    public void setSunrise(Date sunrise) {
        this.sunrise = sunrise;
    }

    /**
     * Returns the sunset local time.
     *
     * @return the sunset local time.
     */
    public Date getSunset() {
        return sunset;
    }

    /**
     * Sets the sunset local time.
     *
     * @param sunset the sunset local time.
     */
    public void setSunset(Date sunset) {
        this.sunset = sunset;
    }

    /**
     * Returns the offset to GMT at 1 January.
     *
     * @return the offset to GMT at 1 January.
     * @deprecated
     */
    public Integer getGmtOffset() {
        return gmtOffset;
    }

    /**
     * Sets the the offset to GMT at 1 January.
     *
     * @param gmtOffset the offset to GMT at 1 January.
     * @deprecated
     */
    public void setGmtOffset(Integer gmtOffset) {
        this.gmtOffset = gmtOffset;
    }

    /**
     * Returns the offset to GMT at 1 July.
     *
     * @return the offset to GMT at 1 July.
     * @deprecated
     */
    public Integer getDstOffset() {
        return dstOffset;
    }

    /**
     * Sets the offset to GMT at 1 July.
     *
     * @param dstOffset the offset to GMT at 1 July.
     * @deprecated
     */
    public void setDstOffset(Integer dstOffset) {
        this.dstOffset = dstOffset;
    }

    /**
     * Returns the ISO country code.
     *
     * @return the ISO country code.
     */
    public String getCountryCode() {
        return countryCode;
    }

    /**
     * Sets the ISO country code.
     *
     * @param countryCode the ISO country code.
     */
    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    /**
     * Returns the country name.
     *
     * @return the country name.
     */
    public String getCountryName() {
        return countryName;
    }

    /**
     * Sets the country name.
     *
     * @param countryName country name.
     */
    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    /**
     * Returns the error status map. Returns null, if request processed without errors. Map contains
     * keys: "message" - the error reason phrase, "value" - the error code.
     *
     * @return the error status map.
     */
    public Map<String, String> getErrorStatus() {
        return errorStatus;
    }

    /**
     * Sets the error status map. May be sets to null.
     *
     * @param errorStatus the error status map.
     */
    public void setErrorStatus(Map<String, String> errorStatus) {
        this.errorStatus = errorStatus;
    }

    //----------------------Methods--------------------------------------------
    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
