package com.boniara.seleniumexample.ui;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;

public class Image extends BaseUI {

    private static final Logger LOG = Logger.getLogger(Image.class);

    public Image(WebElement webElement){
        super(webElement);
    }

    public Image(WebElement webElement, String imageName){
        super(webElement, imageName);
    }

    public boolean isDisplayed() {
        LOG.debug("Image " + webElementName + " is present");
        return (webElement.isEnabled() && webElement.isDisplayed());
    }
}
