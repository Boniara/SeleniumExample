package com.boniara.seleniumexample.utils;

import org.apache.commons.validator.routines.UrlValidator;
import org.apache.log4j.Logger;

public class HttpUtil {

    private static final Logger LOG = Logger.getLogger(HttpUtil.class);

    public HttpUtil() {
    }

    public synchronized static boolean validate(String url) {
        UrlValidator validator = new UrlValidator();
        return validator.isValid(url);
    }
}
