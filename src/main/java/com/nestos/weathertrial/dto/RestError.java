package com.nestos.weathertrial.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Error data transfer class.
 *
 * @author Roman Osipov
 */
public class RestError {

    //----------------------Fields---------------------------------------------
    @JsonProperty("cod")
    private final int code;

    @JsonProperty("message")
    private final String message;

    //----------------------Constructors---------------------------------------
    /**
     * Constructs error object.
     *
     * @param code the error code.
     * @param message the error message.
     */
    public RestError(int code, String message) {
        this.code = code;
        this.message = message;
    }

    //----------------------Getters&Setters------------------------------------
    /**
     * Returns the error code.
     *
     * @return the error code.
     */
    public int getCode() {
        return code;
    }

    /**
     * Returns the error message.
     *
     * @return the error message.
     */
    public String getMessage() {
        return message;
    }
}
