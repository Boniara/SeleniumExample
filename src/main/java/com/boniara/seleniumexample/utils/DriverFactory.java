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

    private static final String CHROME_BROWSER = "chrome";
    private static final String FIREFOX_BROWSER = "firefox";

    private DriverFactory() {
    }

    public static WebDriver getDriverByType() {
        synchronized (DriverFactory.class) {
            String browserName = ConfigUtil.getProperty("browser.name");
            boolean runSSAuto = Boolean.valueOf(ConfigUtil.getProperty("run_ss_auto"));
            switch (browserName.toLowerCase()) {
                case CHROME_BROWSER:
                    return safetyGet(getChromeDriverInstance(runSSAuto));
                case FIREFOX_BROWSER:
                    return safetyGet(getFirefoxDriverInstance(runSSAuto));
                default:
                    throw new RuntimeException(String.format("Browser $s not found", browserName));
            }
        }
    }

    private static WebDriver getChromeDriverInstance(boolean generateAuto) {
        return safetyGet(DesiredCapabilities.chrome(), generateAuto);
    }

    private static WebDriver getFirefoxDriverInstance(boolean generateAuto) {
        Capabilities capabilities = DesiredCapabilities.firefox();
        return safetyGet(capabilities, generateAuto);
    }

    private static WebDriver safetyGet(Capabilities capabilities, boolean generateAuto) {
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

    private static WebDriver safetyGet(WebDriver driver) {
        DriverStorage.put(Thread.currentThread().getId(), driver);
        DriverStorage.get().manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
        DriverStorage.get().manage().window().maximize();
        return DriverStorage.get();
    }
}
