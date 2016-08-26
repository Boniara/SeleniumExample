package com.boniara.seleniumexample.ui;

import com.boniara.seleniumexample.utils.ConfigUtil;
import com.boniara.seleniumexample.utils.StackTraceUtil;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;

public class TextInput {

    private static final Logger LOG = Logger.getLogger(TextInput.class);
    private StackTraceUtil stackTraceUtil;

    private WebElement webElement;
    private String inputName;

    public TextInput(WebElement webElement) {
        this.webElement = webElement;
        this.stackTraceUtil = new StackTraceUtil();
        this.inputName = stackTraceUtil.getUINameFrom(ConfigUtil.getProperty("pages.package"));
    }

    public TextInput(WebElement webElement, String inputName) {
        this.webElement = webElement;
        this.inputName = inputName;
    }

    public void sendKeys(String message) {
        synchronized (this) {
            LOG.debug("Send text: " + message + " to text input field " + inputName);
            if (webElement.isEnabled() && webElement.isDisplayed())
                webElement.sendKeys(message);
        }
    }

    public void submit() {
        LOG.debug("Submit " + inputName + " text input field");
        if(webElement.isEnabled() && webElement.isDisplayed())
            webElement.submit();
    }
}
