package com.boniara.seleniumexample.ui;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;

public class CheckBox extends BaseUI {

    private static final Logger LOG = Logger.getLogger(CheckBox.class);

    public CheckBox(WebElement webElement){
        super(webElement);
    }

    public CheckBox(WebElement webElement, String checkBoxName){
        super(webElement, checkBoxName);
    }

    public void check() {
        LOG.debug("Click " + webElementName + " checkbox");
        if(webElement.isEnabled() && webElement.isDisplayed())
            webElement.click();
    }
}
