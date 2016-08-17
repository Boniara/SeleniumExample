package com.boniara.seleniumexample.utils;

import java.io.File;

public class ChromeDriverLocal {

    private static ChromeDriverLocal chromeDriverLocal = null;

    private String pathToDriver;

    private ChromeDriverLocal() {
        this.pathToDriver = getPathToDriver();
    }

    private static String getPathToDriver() {
        File chromeDriver = new File("src/main/resources/chromedriver.exe");
        return chromeDriver.getAbsolutePath();
    }

    public static ChromeDriverLocal getInstance() {
        if(chromeDriverLocal == null) {
            chromeDriverLocal = new ChromeDriverLocal();
        }
        return chromeDriverLocal;
    }

    public void initDriver() {
        System.setProperty("webdriver.chrome.driver", pathToDriver);
    }
}
