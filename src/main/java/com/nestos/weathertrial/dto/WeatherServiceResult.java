package com.nestos.weathertrial.dto;

/**
 * Interface to access weather result data.
 *
 * @author Roman Osipov
 */
public interface WeatherServiceResult {

    /**
     * Returns the internal parameter 'cod'.
     *
     * @return the internal parameter 'cod'.
     */
    Integer getCod();

    /**
     * Sets the internal parameter 'cod'.
     *
     * @param cod the internal parameter 'cod'.
     */
    void setCod(Integer cod);

    /**
     * Returns the error message.
     *
     * @return the error message.
     */
    public String getMessage();

    /**
     * Sets the error message.
     *
     * @param message the error message.
     */
    public void setMessage(String message);
}
