package com.nestos.weathertrial.exception;

import org.springframework.http.HttpStatus;

/**
 * Exception for abnormal REST service invocation result.
 *
 * @author Roman Osipov
 */
public class RestServiceException extends RuntimeException {

    //----------------------Fields---------------------------------------------
    private final HttpStatus httpStatus;

    //----------------------Constructors---------------------------------------
    /**
     * Constructs REST service exception.
     *
     * @param httpStatus the HTTP status.
     * @param message the exception message.
     */
    public RestServiceException(HttpStatus httpStatus, String message) {
        super(message);
        this.httpStatus = httpStatus;
    }

    //----------------------Getters&Setters------------------------------------
    /**
     * Returns the HTTP status.
     *
     * @return the HTTP status.
     */
    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

}
