package com.boniara.seleniumexample.ui;

import com.boniara.seleniumexample.utils.ConfigUtil;
import com.boniara.seleniumexample.utils.StackTraceUtil;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;

public class Link {

    private static final Logger LOG = Logger.getLogger(Link.class);
    private StackTraceUtil stackTraceUtil;

    private WebElement webElement;
    private String linkName;

    public Link(WebElement webElement){
        this.webElement = webElement;
        this.stackTraceUtil = new StackTraceUtil();
        this.linkName = stackTraceUtil.getUINameFrom(ConfigUtil.getProperty("pages.package"));
    }

    public Link(WebElement webElement, String linkName){
        this.webElement = webElement;
        this.linkName = linkName;
    }

    public void click() {
        LOG.debug("Click " + linkName + " link");
        if(webElement.isEnabled() && webElement.isDisplayed())
            webElement.click();
    }
}
