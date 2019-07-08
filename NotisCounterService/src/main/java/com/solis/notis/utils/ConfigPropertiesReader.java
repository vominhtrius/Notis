package com.solis.common.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Logger;

/**
 *
 * @author Võ Minh Trí
 */
public class ConfigPropertiesReader {

    private String filePath;

    private static final Logger LOG = Logger.getLogger(ConfigPropertiesReader.class.getName());
    private static ConfigPropertiesReader instance;
    Properties prop = new Properties();

    public static ConfigPropertiesReader getInstance() {
        if (instance == null) {
            synchronized (ConfigPropertiesReader.class) {
                if (instance == null) {
                    instance = new ConfigPropertiesReader();
                }
            }
        }

        return instance;
    }

    public void load(String path) throws FileNotFoundException, IOException {
        InputStream input = new FileInputStream("./config.properties");
        prop = new Properties();
        prop.load(input);
    }

    public String getProperty(String key) {
        return prop.getProperty(key);
    }

    public String getProperty(String key, String defaultValue) {
        return prop.getProperty(key, defaultValue);
    }

    public int getIntegerProperty(String key, int defaultValue) {
        try {
            return Integer.valueOf(getProperty(key));
        } catch (Exception e) {
            return defaultValue;
        }
    }

    public float getFloatProperty(String key, float defaultValue) {
        try {
            return Float.valueOf(getProperty(key));
        } catch (Exception e) {
            return defaultValue;
        }
    }

    private ConfigPropertiesReader() {

    }
}
