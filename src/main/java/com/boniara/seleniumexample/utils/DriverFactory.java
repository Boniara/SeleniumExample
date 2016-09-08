package com.boniara.seleniumexample.utils;

import com.boniara.seleniumexample.utils.drivers.DriverUtil;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class DriverFactory {

    private static Capabilities capabilities;

    private static final String CHROME_BROWSER = "chrome";
    private static final String FIREFOX_BROWSER = "firefox";

    private DriverFactory() {
    }

    private static WebDriver getDriverByType() {
        synchronized (DriverFactory.class) {
            String browserName = ConfigUtil.getProperty("browser.name");
            boolean generateAuto = Boolean.valueOf(ConfigUtil.getProperty("run_ss_auto"));
            switch (browserName.toLowerCase()) {
                case CHROME_BROWSER:
                    return getChromeDriverInstance(generateAuto);
                case FIREFOX_BROWSER:
                    return getFirefoxDriverInstance(generateAuto);
                default:
                    throw new RuntimeException(String.format("Browser $s not found", browserName));
            }
        }
    }

    private static WebDriver getChromeDriverInstance(boolean generateAuto) {
        capabilities = DesiredCapabilities.chrome();
        return getDriver(capabilities, generateAuto);
    }

    private static WebDriver getFirefoxDriverInstance(boolean generateAuto) {
        capabilities = DesiredCapabilities.firefox();
        return getDriver(capabilities, generateAuto);
    }

    private static WebDriver getDriver(Capabilities capabilities, boolean generateAuto) {
        WebDriver driver;
        try {
            if(generateAuto)
                DriverUtil.initDriver();
            driver = new RemoteWebDriver(new URL("http://127.0.0.1:4444/wd/hub"), capabilities);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        return driver;
    }

    public static WebDriver getDriver() {
        WebDriver driver = getDriverByType();
        driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        DriverStorage.put(Thread.currentThread().getId(), driver);
        return DriverStorage.get();
    }
}
