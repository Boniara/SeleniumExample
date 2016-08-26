package com.boniara.seleniumexample.utils;

import com.boniara.seleniumexample.exceptions.UrlNoValidException;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigUtil {

    private static final Logger LOG = Logger.getLogger(ConfigUtil.class);
    private volatile static ConfigUtil configUtil = null;

    private static Properties properties = new Properties();
    private static final String PROPERTIES_FILE = "com.boniara.seleniumexample/onliner/config.properties";

    private ConfigUtil() {
    }

    public static String getProperty(String key) {
        InputStream inputStream = null;
        String property = null;
        try {
            inputStream = ConfigUtil.class.getClassLoader().getResourceAsStream(PROPERTIES_FILE);
            synchronized(properties) {
                properties.load(inputStream);
                property = properties.getProperty(key);
            }
        } catch (IOException e) {
            LOG.error(e);
        }
        return property;
    }

    public static String get(String url) {
        if(! HttpUtil.validate(url))
            try {
                throw new UrlNoValidException("Url " + url + " is not valid");
            } catch (UrlNoValidException e) {
                LOG.error(e);
            }
        return url;
    }

    public static ConfigUtil getInstance() {
        if(configUtil == null) {
            synchronized (ConfigUtil.class) {
                if(configUtil == null)
                    configUtil = new ConfigUtil();
            }
        }
        return configUtil;
    }
}
