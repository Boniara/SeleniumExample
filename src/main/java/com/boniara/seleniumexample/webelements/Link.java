package com.boniara.seleniumexample.webelements;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;

public class Link {

    private static final Logger LOG = Logger.getLogger(Link.class);

    private WebElement webElement;
    private String linkName;

    public Link(WebElement webElement){
        this.webElement = webElement;
        this.linkName = this.webElement.getTagName();
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
