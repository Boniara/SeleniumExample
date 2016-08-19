package com.boniara.seleniumexample.utils.drivers;

import java.io.File;
import java.io.IOException;

public class FireFoxDriver {

    private static FireFoxDriver fireFoxDriver = null;

    private String pathToDriver;
    private String pathToStandaloneServer;

    private FireFoxDriver() {
        this.pathToDriver = getPathToDriver();
        this.pathToStandaloneServer = getPathToStanaloneServer();
        initDriver();
    }

    private static String getPathToDriver() {
        File fireFoxDriver = new File("src/main/resources/geckodriver.exe");
        return fireFoxDriver.getAbsolutePath();
    }

    private static String getPathToStanaloneServer() {
        File seleniumStandaloneServer = new File("src/main/resources/selenium-server-standalone-2.53.1.jar");
        return seleniumStandaloneServer.getAbsolutePath();
    }

    private void initDriver() {
        try {
            Runtime.getRuntime().exec("java -jar " + pathToStandaloneServer + " -Dwebdriver.geckodriver.driver=" + pathToDriver);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static FireFoxDriver getInstance() {
        if(fireFoxDriver == null) {
            fireFoxDriver = new FireFoxDriver();
        }
        return fireFoxDriver;
    }
}
