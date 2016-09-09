package com.boniara.seleniumexample.ui;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;

public class TextLabel extends BaseUI {

    private static final Logger LOG = Logger.getLogger(TextLabel.class);

    public TextLabel(WebElement webElement){
        super(webElement);
    }

    public TextLabel(WebElement webElement, String textFieldName){
        super(webElement, textFieldName);
    }

    public String getValue() {
        LOG.debug("Get value " + getElement().getText() + " from text field " + webElementName);
        return getElement().getText();
    }
}
