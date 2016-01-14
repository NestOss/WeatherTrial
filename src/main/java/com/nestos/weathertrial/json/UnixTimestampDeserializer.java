package com.nestos.weathertrial.json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import java.io.IOException;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Unix timestamp deserializer for JSON.
 *
 * @author Roman Osipov
 */
public class UnixTimestampDeserializer extends JsonDeserializer<Date> {

    //------------------Logger-------------------------------------------------
    private final Logger logger = LoggerFactory.getLogger(UnixTimestampDeserializer.class);

    //------------------Methods------------------------------------------------
    @Override
    public Date deserialize(JsonParser jp, DeserializationContext dc) throws IOException,
            JsonProcessingException {
        String timestamp = jp.getText().trim();
        try {
            return new Date(Long.valueOf(timestamp + "000"));
        } catch (NumberFormatException e) {
            logger.warn("Unable to deserialize timestamp: " + timestamp, e);
            return null;
        }
    }

}
