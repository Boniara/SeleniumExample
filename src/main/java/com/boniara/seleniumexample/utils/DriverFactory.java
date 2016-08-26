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

    private volatile static DriverFactory driverFactory = null;
    private WebDriver driver;
    private Capabilities capabilities;
    protected DriverStorage storage;

    private static final String CHROME_BROWSER = "chrome";
    private static final String FIREFOX_BROWSER = "firefox";

    private DriverFactory() {
    }

    private void getDriverByType() {
        synchronized (DriverFactory.class) {
            String browserName = ConfigUtil.getProperty("browser.name");
            boolean generateAuto = Boolean.valueOf(ConfigUtil.getProperty("run_ss_auto"));
            switch (browserName.toLowerCase()) {
                case CHROME_BROWSER:
                    driver = getChromeDriverInstance(generateAuto);
                    break;
                case FIREFOX_BROWSER:
                    driver = getFirefoxDriverInstance(generateAuto);
                    break;
                default:
                    break;
            }
        }
    }

    private WebDriver getChromeDriverInstance(boolean generateAuto) {
        capabilities = DesiredCapabilities.chrome();
        return getDriver(capabilities, generateAuto);
    }

    private WebDriver getFirefoxDriverInstance(boolean generateAuto) {
        capabilities = DesiredCapabilities.firefox();
        return getDriver(capabilities, generateAuto);
    }

    private WebDriver getDriver(Capabilities capabilities, boolean generateAuto) {
        try {
            if(generateAuto)
                DriverUtil.initDriver();
            driver = new RemoteWebDriver(new URL("http://127.0.0.1:4444/wd/hub"), capabilities);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        return driver;
    }

    public static DriverFactory getInstance() {
        if(driverFactory == null) {
            synchronized(DriverFactory.class) {
                if(driverFactory == null)
                    driverFactory = new DriverFactory();
            }
        }
        return driverFactory;
    }

    public synchronized WebDriver getDriver() {
        synchronized (DriverFactory.class) {
            getDriverByType();
            driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
            storage = DriverStorage.getInstance();
            storage.put(Thread.currentThread().getId(), driver);
            return storage.get(Thread.currentThread().getId());
        }
    }
}
