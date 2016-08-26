package com.boniara.seleniumexample.utils.drivers;

import java.io.File;
import java.io.IOException;

public class DriverUtil {

    private static final String chromeSystemProperty = "-Dwebdriver.chrome.driver";
    private static final String firefixSystemProperty = "-Dwebdriver.gecko.driver";

    private static String getPathToChromeDriver() {
        File chromeDriver = new File("src/main/resources/chromedriver.exe");
        return chromeDriver.getAbsolutePath();
    }

    private static String getPathToFirefoxDriver() {
        File fireFoxDriver = new File("src/main/resources/geckodriver.exe");
        return fireFoxDriver.getAbsolutePath();
    }

    private static String getPathToStanaloneServer() {
        File seleniumStandaloneServer = new File("src/main/resources/selenium-server-standalone-2.53.1.jar");
        return seleniumStandaloneServer.getAbsolutePath();
    }

    public synchronized static void initDriver() {
        try {
            Runtime.getRuntime().exec("java -jar " + getPathToStanaloneServer() + " " + chromeSystemProperty + "="
                    + getPathToChromeDriver() + " " + firefixSystemProperty + "=" + getPathToFirefoxDriver());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
