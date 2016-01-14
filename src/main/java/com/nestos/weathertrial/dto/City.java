package com.nestos.weathertrial.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

/**
 * City data transfer class.
 *
 * @author Roman Osipov
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class City {

    //----------------------Fields---------------------------------------------
    @JsonProperty("id")
    private Long id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("coord")
    private GeoLocation geoLocation; // coord

    @JsonProperty("country")
    private String country;

    @JsonProperty("population")
    private Integer population;

    //----------------------Getters&Setters------------------------------------
    /**
     * Returns the city id.
     *
     * @return the city id.
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the city id.
     *
     * @param id the city id.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Returns the city name.
     *
     * @return the city name.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the city name.
     *
     * @param name the city name.
     */
    public void setName(String name) {
        this.name = name;
    }

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
     * @param geoLocation the city geo location.
     */
    public void setGeoLocation(GeoLocation geoLocation) {
        this.geoLocation = geoLocation;
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
     * Sets the country code (GB, JP etc.)
     *
     * @param country the country code.
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * Returns the city population.
     *
     * @return the city population.
     */
    public Integer getPopulation() {
        return population;
    }

    /**
     * Sets the city population.
     *
     * @param population the city population.
     */
    public void setPopulation(Integer population) {
        this.population = population;
    }

    //----------------------Methods--------------------------------------------
    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }

}
