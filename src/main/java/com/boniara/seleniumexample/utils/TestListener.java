package com.boniara.seleniumexample.utils;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;

public class TestListener implements ITestListener {

    private static final Logger LOG = Logger.getLogger(TestListener.class);

    public TestListener() {
    }

    public void onTestStart(ITestResult iTestResult) {
        LOG.debug("Test with thread " + Thread.currentThread().getName() + " is started");
    }

    public void onTestSuccess(ITestResult iTestResult) {
        LOG.debug("Test with thread " + Thread.currentThread().getName() + " is finished success");
    }

    public synchronized void onTestFailure(ITestResult iTestResult) {
        WebDriver driver = DriverStorage.get();
        File file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        try {
            Long currentTime = System.currentTimeMillis();
            FileUtils.copyFile(file, new File(ConfigUtil.getProperty("sc.path") + currentTime + "."
            + ConfigUtil.getProperty("sc.format")));
            LOG.debug("Screenshot with name " + currentTime + "." + ConfigUtil.getProperty("sc.format")
            + " is saved to " + ConfigUtil.getProperty("sc.path"));
        } catch (IOException e) {
            LOG.error(e);
        }
    }

    public void onTestSkipped(ITestResult iTestResult) {
        WebDriver driver = DriverStorage.get();
        driver.quit();
        LOG.debug("Test with thread " + Thread.currentThread().getName() + " is skipped");
    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
        LOG.debug("Test with thread " + Thread.currentThread().getName() + " is failed but within success percentage");
    }

    public void onStart(ITestContext iTestContext) {
    }

    public void onFinish(ITestContext iTestContext) {
    }
}
