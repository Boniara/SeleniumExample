package com.boniara.seleniumexample.onliner.pages;

import com.boniara.seleniumexample.utils.ConfigUtil;
import com.boniara.seleniumexample.utils.DriverStorage;
import com.boniara.seleniumexample.utils.HttpUtil;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

public class BasePage {

    private static final Logger LOG = Logger.getLogger(BasePage.class);

    protected String url;
    protected WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.url = ConfigUtil.getProperty("url");
    }

    public synchronized void openPage() {
        getDriver().get(url);
        LOG.debug("Page " + url + " is opened");
    }

    protected synchronized String setPageAbsoluteUrl(String url) {
        this.url = url;
        return url;
    }

    protected synchronized String setPageUrl(String postfixUrl) {
        this.url = ConfigUtil.getProperty("url") + postfixUrl;
        return ConfigUtil.getProperty("url") + postfixUrl;
    }

    protected synchronized boolean validateUrl(String url) {
        return HttpUtil.validate(url);
    }

    private Long getThreadId() {
        return Thread.currentThread().getId();
    }

    protected synchronized WebDriver getDriver() {
        return DriverStorage.getInstance().get(getThreadId());
    }
}
