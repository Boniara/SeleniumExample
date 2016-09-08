package com.boniara.seleniumexample.ui;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;

public class TextInput extends BaseUI {

    private static final Logger LOG = Logger.getLogger(TextInput.class);

    public TextInput(WebElement webElement) {
        super(webElement);
    }

    public TextInput(WebElement webElement, String inputName) {
        super(webElement, inputName);
    }

    public void sendKeys(String message) {
        synchronized (this) {
            LOG.debug("Send text: " + message + " to text input field " + webElementName);
            if (webElement.isEnabled() && webElement.isDisplayed())
                webElement.sendKeys(message);
        }
    }

    public void submit() {
        LOG.debug("Submit " + webElementName + " text input field");
        webElement.submit();
    }
}
