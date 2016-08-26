package com.boniara.seleniumexample.ui;

import com.boniara.seleniumexample.utils.ConfigUtil;
import com.boniara.seleniumexample.utils.StackTraceUtil;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CheckBox {

    private static final Logger LOG = Logger.getLogger(CheckBox.class);
    private StackTraceUtil stackTraceUtil;

    private WebElement webElement;
    private String checkBoxName;

    public CheckBox(WebElement webElement){
        ExpectedConditions.elementToBeClickable(webElement);
        this.webElement = webElement;
        this.stackTraceUtil = new StackTraceUtil();
        this.checkBoxName = stackTraceUtil.getUINameFrom(ConfigUtil.getProperty("pages.package"));
    }

    public CheckBox(WebElement webElement, String checkBoxName){
        ExpectedConditions.elementToBeClickable(webElement);
        this.webElement = webElement;
        this.checkBoxName = checkBoxName;
    }

    public void check() {
        LOG.debug("Click " + checkBoxName + " checkbox");
        if(webElement.isEnabled() && webElement.isDisplayed())
            webElement.click();
    }
}
