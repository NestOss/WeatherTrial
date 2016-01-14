package com.nestos.weathertrial.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nestos.weathertrial.dto.GeoLocation;
import com.nestos.weathertrial.dto.LocalTimeForGeoLocation;
import com.nestos.weathertrial.exception.RestServiceException;
import static com.nestos.weathertrial.service.LocalTimeServiceImpl.LATITUDE_PARAM_NAME;
import static com.nestos.weathertrial.service.LocalTimeServiceImpl.LONGITUDE_PARAM_NAME;
import static com.nestos.weathertrial.service.LocalTimeServiceImpl.PROVIDER_HOST;
import static com.nestos.weathertrial.service.LocalTimeServiceImpl.TIME_ZONE_SERVICE_PATH;
import static com.nestos.weathertrial.service.LocalTimeServiceImpl.USER_NAME;
import static com.nestos.weathertrial.service.LocalTimeServiceImpl.USER_PARAM_NAME;
import com.nestos.weathertrial.service.LocalTimeServiceImplTest.LocalTimeServiceImplTestConfig;
import static com.nestos.weathertrial.testutils.TestUtils.getResourceAsString;
import java.io.IOException;
import java.net.URI;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.ConfigFileApplicationContextInitializer;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.client.MockRestServiceServer;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.unitils.reflectionassert.ReflectionAssert;

/**
 * LocalTimeServiceImpl tests.
 *
 * @author Roman Osipov
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(LocalTimeServiceImplTestConfig.class)
@ActiveProfiles("test")
public class LocalTimeServiceImplTest {
    //-------------------Inner classes--------------------------------------------

    @Configuration
    @EnableAutoConfiguration
    public static class LocalTimeServiceImplTestConfig {

        @Bean
        public RestTemplate restTemplate() {
            return new RestTemplate();
        }

        @Bean
        public LocalTimeService localTimeService(RestTemplate restTemplate) {
            LocalTimeServiceImpl localTimeServiceImpl = new LocalTimeServiceImpl();
            localTimeServiceImpl.setRestTemplate(restTemplate);
            return localTimeServiceImpl;
        }
    }

    //-------------------Fields---------------------------------------------------
    MockRestServiceServer mockServer;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private LocalTimeService localTimeService;

    private static final String OK_RESPONSE_JSON_RESOURCE
            = "/localTimeServiceImplData/okResponse.json";

    private static final String ERROR_RESPONSE_JSON_RESOURCE
            = "/localTimeServiceImplData/errorResponse.json";

    private static final String VALID_LONGITUDE_VALUE = "12.5";
    private static final String VALID_LATITUDE_VALUE = "17.1";

    //-------------------Methods-------------------------------------------------
    private LocalTimeForGeoLocation getOkLocalTime() throws IOException, ParseException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(
                getResourceAsString(OK_RESPONSE_JSON_RESOURCE));
        LocalTimeForGeoLocation localTime = new LocalTimeForGeoLocation();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.ENGLISH);
        format.setTimeZone(TimeZone.getTimeZone("GMT"));
        localTime.setTime(format.parse(root.findValue("time").asText()));
        localTime.setCountryName(root.findValue("countryName").asText());
        localTime.setSunset(format.parse(root.findValue("sunset").asText()));
        localTime.setSunrise(format.parse(root.findValue("sunrise").asText()));
        localTime.setLongitude(root.findValue("lng").asDouble());
        localTime.setLatitude(root.findValue("lat").asDouble());
        localTime.setRawOffset(root.findValue("rawOffset").asInt());
        localTime.setDstOffset(root.findValue("dstOffset").asInt());
        localTime.setGmtOffset(root.findValue("gmtOffset").asInt());
        localTime.setCountryCode(root.findValue("countryCode").asText());
        localTime.setTimezoneId(root.findValue("timezoneId").asText());
        return localTime;
    }

    @Before
    public void setup() {
        mockServer = MockRestServiceServer.createServer(restTemplate);
    }

    @Test // Happy path.
    public void shouldReturnCorrectLocalTime() throws IOException, ParseException {
        // arrange
        String jsonResponseString
                = getResourceAsString(OK_RESPONSE_JSON_RESOURCE);
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(jsonResponseString);
        URI uri = UriComponentsBuilder.newInstance()
                .scheme("http").host(PROVIDER_HOST)
                .path(TIME_ZONE_SERVICE_PATH)
                .queryParam(LONGITUDE_PARAM_NAME, root.findValue("lng").asText())
                .queryParam(LATITUDE_PARAM_NAME, root.findValue("lat").asText())
                .queryParam(USER_PARAM_NAME, USER_NAME)
                .build().encode().toUri();
        mockServer.expect(requestTo(uri.toString()))
                .andExpect(method(HttpMethod.GET))
                .andRespond(
                        withSuccess(jsonResponseString, MediaType.APPLICATION_JSON));
        // act
        GeoLocation geoLocation = new GeoLocation();
        geoLocation.setLongitude(root.findValue("lng").asDouble());
        geoLocation.setLatitude(root.findValue("lat").asDouble());
        LocalTimeForGeoLocation localTime
                = localTimeService.getLocalTimeForGeoLocation(geoLocation);
        // assert
        ReflectionAssert.assertReflectionEquals(localTime, getOkLocalTime());
    }

    @Test
    public void shouldThrowRestServiceExceptionForErrorResponceLocalTime()
            throws IOException, ParseException {
        // arrange
        String jsonResponseString
                = getResourceAsString(ERROR_RESPONSE_JSON_RESOURCE);
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(jsonResponseString);
        URI uri = UriComponentsBuilder.newInstance()
                .scheme("http").host(PROVIDER_HOST)
                .path(TIME_ZONE_SERVICE_PATH)
                .queryParam(LONGITUDE_PARAM_NAME, VALID_LONGITUDE_VALUE)
                .queryParam(LATITUDE_PARAM_NAME, VALID_LATITUDE_VALUE)
                .queryParam(USER_PARAM_NAME, USER_NAME)
                .build().encode().toUri();
        mockServer.expect(requestTo(uri.toString()))
                .andExpect(method(HttpMethod.GET))
                .andRespond(
                        withSuccess(jsonResponseString, MediaType.APPLICATION_JSON));
        // act
        GeoLocation geoLocation = new GeoLocation();
        geoLocation.setLongitude(Double.valueOf(VALID_LONGITUDE_VALUE));
        geoLocation.setLatitude(Double.valueOf(VALID_LATITUDE_VALUE));
        try {
            LocalTimeForGeoLocation localTime
                    = localTimeService.getLocalTimeForGeoLocation(geoLocation);
            fail("shouldThrowRestServiceExceptionForErrorLocalTime");
        } catch (RestServiceException e) {
            // assert
            assertEquals(uri.toString() + " -> "
                    + root.findValue("status").findValue("message").asText(),
                    e.getMessage());
            assertEquals(HttpStatus.BAD_REQUEST, e.getHttpStatus());
        }
    }

}
