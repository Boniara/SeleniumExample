package com.boniara.seleniumexample.utils.drivers;

import java.io.File;
import java.io.IOException;

public class ChromeDriver {

    private static ChromeDriver chromeDriver = null;

    private String pathToDriver;
    private String pathToStandaloneServer;

    private ChromeDriver() {
        this.pathToDriver = getPathToDriver();
        this.pathToStandaloneServer = getPathToStanaloneServer();
        initDriver();
    }

    private static String getPathToDriver() {
        File chromeDriver = new File("src/main/resources/chromedriver.exe");
        return chromeDriver.getAbsolutePath();
    }

    private static String getPathToStanaloneServer() {
        File seleniumStandaloneServer = new File("src/main/resources/selenium-server-standalone-2.53.1.jar");
        return seleniumStandaloneServer.getAbsolutePath();
    }

    private void initDriver() {
        try {
            Runtime.getRuntime().exec("java -jar " + pathToStandaloneServer + " -Dwebdriver.chrome.driver=" + pathToDriver);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static ChromeDriver getInstance() {
        if(chromeDriver == null) {
            chromeDriver = new ChromeDriver();
        }
        return chromeDriver;
    }
}
