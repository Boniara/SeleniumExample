package com.boniara.seleniumexample.utils;

import org.openqa.selenium.WebDriver;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DriverStorage {

    private static volatile Map<Long, WebDriver> driverMap;

    static {
        driverMap = new ConcurrentHashMap<>();
    }

    public static void put(Long idThread, WebDriver driver) {
        driverMap.put(idThread, driver);
    }

    public  static WebDriver get() {
        return driverMap.get(getThreadId());
    }

    private static Long getThreadId() {
        return Thread.currentThread().getId();
    }
}
