package com.nestos.weathertrial.service;

import com.nestos.weathertrial.dto.GeoLocation;
import com.nestos.weathertrial.dto.LocalTimeForGeoLocation;

/**
 * Local time service.
 *
 * @author Roman Osipov
 */
public interface LocalTimeService {

    /**
     * Returns the local time for geo location.
     *
     * @param geoLocation the geo location.
     * @return the local time for geo location.
     */
    LocalTimeForGeoLocation getLocalTimeForGeoLocation(GeoLocation geoLocation);
}
