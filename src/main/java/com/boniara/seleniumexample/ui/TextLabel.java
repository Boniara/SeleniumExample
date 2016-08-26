package com.boniara.seleniumexample.ui;

import com.boniara.seleniumexample.utils.ConfigUtil;
import com.boniara.seleniumexample.utils.StackTraceUtil;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;

public class TextLabel {

    private static final Logger LOG = Logger.getLogger(TextLabel.class);
    private StackTraceUtil stackTraceUtil;

    private WebElement webElement;
    private String textFieldName;

    public TextLabel(WebElement webElement){
        this.webElement = webElement;
        this.stackTraceUtil = new StackTraceUtil();
        this.textFieldName = stackTraceUtil.getUINameFrom(ConfigUtil.getProperty("pages.package"));
    }

    public TextLabel(WebElement webElement, String textFieldName){
        this.webElement = webElement;
        this.textFieldName = textFieldName;
    }

    public String getValue() {
        LOG.debug("Get value " + webElement.getText() + " from text field " + textFieldName);
        return webElement.getText();
    }
}
