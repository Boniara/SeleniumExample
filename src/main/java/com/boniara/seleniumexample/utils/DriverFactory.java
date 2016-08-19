package com.boniara.seleniumexample.utils;

import com.boniara.seleniumexample.utils.drivers.ChromeDriver;
import com.boniara.seleniumexample.utils.drivers.FireFoxDriver;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class DriverFactory {

    private static DriverFactory driverFactory = null;
    private static WebDriver driver;
    private static Capabilities capabilities;

    private DriverFactory() {
    }

    public static WebDriver getChromeDriverInstance() {
        ChromeDriver.getInstance();
        capabilities = DesiredCapabilities.chrome();
        return getDriver(capabilities);
    }

    public static WebDriver getFirefoxDriverInstance() {
        FireFoxDriver.getInstance();
        capabilities = DesiredCapabilities.firefox();
        return getDriver(capabilities);
    }

    private static WebDriver getDriver(Capabilities capabilities) {
        try {
            driver = new RemoteWebDriver(new URL("http://127.0.0.1:4444/wd/hub"), capabilities);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        return driver;
    }

    public static DriverFactory getInstance() {
        if(driverFactory == null) {
            driverFactory = new DriverFactory();
        }
        return driverFactory;
    }

    public static WebDriver getDriver() {
        return driver;
    }
}
