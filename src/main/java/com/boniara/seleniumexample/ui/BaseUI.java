package com.boniara.seleniumexample.ui;

import com.boniara.seleniumexample.utils.ConfigUtil;
import com.boniara.seleniumexample.utils.StackTraceUtil;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class BaseUI {

    protected StackTraceUtil stackTraceUtil;
    protected WebElement webElement;
    protected String webElementName;

    protected BaseUI(WebElement webElement) {
        this.webElement = webElement;
        this.stackTraceUtil = new StackTraceUtil();
        this.webElementName = stackTraceUtil.getUINameFrom(ConfigUtil.getProperty("pages.package"));
    }

    protected BaseUI(WebElement webElement, String webElementName) {
        this.webElement = webElement;
        this.webElementName = webElementName;
    }

    protected ExpectedCondition<WebElement> isClickable(WebElement webElement) {
        return ExpectedConditions.elementToBeClickable(webElement);
    }
}
