package com.nestos.weathertrial.service;

import com.nestos.weathertrial.dto.GeoLocation;
import com.nestos.weathertrial.dto.LocalTimeForGeoLocation;
import com.nestos.weathertrial.exception.RestServiceException;
import java.net.URI;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * Local time service implementation.
 *
 * @author Roman Osipov
 */
@Service
public class LocalTimeServiceImpl implements LocalTimeService {

    //----------------------Fields---------------------------------------------
    public static final String PROVIDER_HOST = "api.geonames.org";
    public static final String TIME_ZONE_SERVICE_PATH = "/timezoneJSON";
    public static final String LONGITUDE_PARAM_NAME = "lng";
    public static final String LATITUDE_PARAM_NAME = "lat";
    public static final String USER_PARAM_NAME = "username";
    public static final String USER_NAME = "myapp";
    public static final String ERROR_MESSAGE_FIELD_NAME = "message";
    private RestTemplate restTemplate;

    //----------------------Constructors---------------------------------------
    //----------------------Methods--------------------------------------------

    @Autowired(required = false) // false for test suit
    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    
    /**
     * Returns the local time for geo location.
     *
     * @param geoLocation the geo location.
     * @return the local time for geo location.
     * @throw RestServiceException
     * @throw RestClientException
     *
     */
    @Override
    public LocalTimeForGeoLocation getLocalTimeForGeoLocation(GeoLocation geoLocation) {
        URI uri = UriComponentsBuilder.newInstance()
                .scheme("http").host(PROVIDER_HOST).path(TIME_ZONE_SERVICE_PATH)
                .queryParam(LONGITUDE_PARAM_NAME, geoLocation.getLongitude())
                .queryParam(LATITUDE_PARAM_NAME, geoLocation.getLatitude())
                .queryParam(USER_PARAM_NAME, USER_NAME)
                .build().encode().toUri();
        ResponseEntity<LocalTimeForGeoLocation> responseEntity
                = restTemplate.getForEntity(uri, LocalTimeForGeoLocation.class);
        LocalTimeForGeoLocation localTimeForGeoLocation = responseEntity.getBody();
        Map<String, String> errorStatusMap = localTimeForGeoLocation.getErrorStatus();
        if (errorStatusMap != null) {
            throw new RestServiceException(HttpStatus.BAD_REQUEST, uri.toString() + " -> "
                    + errorStatusMap.get(ERROR_MESSAGE_FIELD_NAME));
        }
        return localTimeForGeoLocation;
    }

}
