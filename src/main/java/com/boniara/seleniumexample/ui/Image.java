package com.boniara.seleniumexample.ui;

import com.boniara.seleniumexample.utils.ConfigUtil;
import com.boniara.seleniumexample.utils.StackTraceUtil;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;

public class Image {

    private static final Logger LOG = Logger.getLogger(Image.class);
    private StackTraceUtil stackTraceUtil;

    private WebElement webElement;
    private String imageName;

    public Image(WebElement webElement){
        this.webElement = webElement;
        this.stackTraceUtil = new StackTraceUtil();
        this.imageName = stackTraceUtil.getUINameFrom(ConfigUtil.getProperty("pages.package"));
    }

    public Image(WebElement webElement, String imageName){
        this.webElement = webElement;
        this.imageName = imageName;
    }

    public boolean isDisplayed() {
        LOG.debug("Image " + imageName + " is present");
        if(webElement.isEnabled() && webElement.isDisplayed())
            return true;
        return false;
    }
}
