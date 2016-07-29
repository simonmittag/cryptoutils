/*
 * PropertyHelper
 */
package com.simonmittag.cryptoutils;

/**
 * Fetches values from System environment variables or properties
 * @author simonmittag
 */
public class PropertyHelper {

    /**
     * Return system environment variable or property
     * @param key The property to lookup
     * @return The return value
     */
    public static String getEnvOrProperty(String key) {
        String value = System.getenv(key);
        if(value==null) {
            value = System.getProperty(key);
        }
        return value;
    }
}
