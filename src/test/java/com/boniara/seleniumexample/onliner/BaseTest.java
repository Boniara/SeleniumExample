package com.boniara.seleniumexample.onliner;

import com.boniara.seleniumexample.utils.DriverFactory;
import com.boniara.seleniumexample.utils.DriverStorage;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {

    private static final Logger LOG = Logger.getLogger(BaseTest.class);

    @BeforeMethod(alwaysRun = true,description = "Init driver")
    protected synchronized void initDriver() {
        DriverFactory.getInstance().getDriver();
    }

    @AfterMethod(alwaysRun = true, description = "Quit driver")
    protected synchronized void quitDriver() {
        getDriver().quit();
    }

    private Long getThreadId() {
        return Thread.currentThread().getId();
    }

    protected synchronized WebDriver getDriver() {
        return DriverStorage.getInstance().get(getThreadId());
    }

    protected void pause(Integer seconds) {
        try {
            Thread.currentThread().join(seconds * 1000);
        } catch (InterruptedException e) {
            LOG.debug(e);
        }
    }
}
