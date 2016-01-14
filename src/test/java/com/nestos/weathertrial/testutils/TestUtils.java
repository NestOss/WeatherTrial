package com.nestos.weathertrial.testutils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Test utils.
 *
 * @author Roman Osipov
 */
public class TestUtils {

    //-------------------Methods--------------------------------------------------
    public static String getResourceAsString(String resourceName) {
        String result = null;
        try {
            result = new String(Files.readAllBytes(Paths.get(
                    TestUtils.class.getResource(resourceName).toURI())), StandardCharsets.UTF_8);
        } catch (IOException | URISyntaxException ex) {
            ex.printStackTrace();
        }
        return result;
    }
}
