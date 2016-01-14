package com.nestos.weathertrial.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.nestos.weathertrial.json.UnixTimestampDeserializer;
import java.util.Date;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

/**
 * Forecast data transfer class.
 *
 * @author Roman Osipov
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherForecast extends Weather {

    //----------------------Fields---------------------------------------------
    @JsonDeserialize(using = UnixTimestampDeserializer.class)
    @JsonProperty("dt")
    private Date time;

    @JsonProperty("dt_txt")
    private String calculationTimeText;

    //----------------------Getters&Setters------------------------------------
    /**
     * Returns the time of data forecasted, unix, UTC.
     *
     * @return time of data forecasted.
     */
    public Date getTime() {
        return time;
    }

    /**
     * Sets the time of data forecasted, unix, UTC.
     *
     * @param time time of data forecasted.
     */
    public void setTime(Date time) {
        this.time = time;
    }

    /**
     * Returns the text of data/time of calculation, UTC.
     *
     * @return the text of data/time of calculation, UTC.
     */
    public String getCalculationTimeText() {
        return calculationTimeText;
    }

    /**
     * Sets the text of data/time of calculation, UTC.
     *
     * @param calculationTimeText the text of data/time of calculation, UTC.
     */
    public void setCalculationTimeText(String calculationTimeText) {
        this.calculationTimeText = calculationTimeText;
    }

    //----------------------Methods--------------------------------------------
    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, null, false, false, Weather.class);
    }

}
