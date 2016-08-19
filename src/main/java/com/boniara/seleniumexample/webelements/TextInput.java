package com.boniara.seleniumexample.webelements;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;

public class TextInput {

    private static final Logger LOG = Logger.getLogger(TextInput.class);

    private WebElement webElement;
    private String inputName;

    public TextInput(WebElement webElement) {
        this.webElement = webElement;
        this.inputName = this.webElement.getTagName();
    }

    public TextInput(WebElement webElement, String inputName) {
        this.webElement = webElement;
        this.inputName = inputName;
    }

    public void sendKeys(String message) {
        LOG.debug("Send text: " + message + " to text input field " + inputName);
        if(webElement.isEnabled() && webElement.isDisplayed())
            webElement.sendKeys(message);
    }

    public void submit() {
        LOG.debug("Submit " + inputName + " text input field");
        if(webElement.isEnabled() && webElement.isDisplayed())
            webElement.submit();
    }
}
