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
    protected void initDriver() {
        DriverFactory.getDriver();
    }

    @AfterMethod(alwaysRun = true, description = "Quit driver")
    protected void quitDriver() {
        getDriver().quit();
    }

    protected WebDriver getDriver() {
        return DriverStorage.get();
    }

    protected void pause(Integer seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            LOG.error(e);
        }
    }
}
