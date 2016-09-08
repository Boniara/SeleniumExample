package com.boniara.seleniumexample.pages;

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

    public void openPage() {
        getDriver().get(url);
        LOG.debug("Page " + url + " is opened");
    }

    protected String setPageAbsoluteUrl(String url) {
        this.url = url;
        return url;
    }

    protected void setPageUrl(String postfixUrl) {
        this.url = ConfigUtil.getProperty("url") + postfixUrl;
    }

    protected boolean validateUrl(String url) {
        return HttpUtil.validate(url);
    }

    protected WebDriver getDriver() {
        return DriverStorage.get();
    }
}
