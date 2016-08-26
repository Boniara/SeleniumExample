package com.boniara.seleniumexample.utils;

import org.openqa.selenium.WebDriver;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DriverStorage {

    private Map<Long, WebDriver> driverMap;
    private volatile static DriverStorage storage;

    private DriverStorage() {
        this.driverMap = new ConcurrentHashMap();
    }

    public void put(Long idThread, WebDriver driver) {
        synchronized (driverMap) {
            driverMap.put(idThread, driver);
        }
    }

    public WebDriver get(Long idThread) {
        synchronized (driverMap) {
            return driverMap.get(idThread);
        }
    }

    public static DriverStorage getInstance() {
        if(storage == null) {
            synchronized (DriverStorage.class) {
                if(storage == null) {
                    storage = new DriverStorage();
                }
            }
        }
        return storage;
    }
}
