package com.boniara.seleniumexample.webelements;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CheckBox {

    private static final Logger LOG = Logger.getLogger(CheckBox.class);

    private WebElement webElement;
    private String checkBoxName;

    public CheckBox(WebElement webElement){
        ExpectedConditions.elementToBeClickable(webElement);
        this.webElement = webElement;
        this.checkBoxName = this.webElement.getTagName();
    }

    public CheckBox(WebElement webElement, String checkBoxName){
        ExpectedConditions.elementToBeClickable(webElement);
        this.webElement = webElement;
        this.checkBoxName = checkBoxName;
    }

    public void click() {
        LOG.debug("Click " + checkBoxName + " checkbox");
        if(webElement.isEnabled() && webElement.isDisplayed())
            webElement.click();
    }
}
