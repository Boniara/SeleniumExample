package com.boniara.seleniumexample.webelements;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;

public class TextField {

    private static final Logger LOG = Logger.getLogger(TextField.class);

    private WebElement webElement;
    private String textFieldName;

    public TextField(WebElement webElement){
        this.webElement = webElement;
        this.textFieldName = this.webElement.getTagName();
    }

    public TextField(WebElement webElement, String textFieldName){
        this.webElement = webElement;
        this.textFieldName = textFieldName;
    }

    public String getValue() {
        LOG.debug("Get value " + webElement.getText() + " from text field " + textFieldName);
        return webElement.getText();
    }
}
