package com.boniara.seleniumexample.pages;

import com.boniara.seleniumexample.utils.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class BaseTest {

    protected WebDriver driver;

    @BeforeClass(description = "Init driver")
    protected void initDriver() {
        driver = DriverFactory.getChromeDriverInstance();
    }

    @AfterClass(description = "Quit driver")
    protected void quitDriver() {
        driver.quit();
    }
}
