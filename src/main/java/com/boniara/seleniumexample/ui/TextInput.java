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
        LOG.debug("Send text: " + message + " to text input field " + webElementName);
        getElement().sendKeys(message);
    }

    public void submit() {
        LOG.debug("Submit " + webElementName + " text input field");
        getElement().submit();
    }
}
