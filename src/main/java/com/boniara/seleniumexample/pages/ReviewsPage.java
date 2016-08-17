package com.boniara.seleniumexample.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ReviewsPage {

    private WebDriver driver;

    @FindBy(xpath = "//div[@class='ppdescr']")
    private WebElement phoneDescriptionFromReviewLink;

    public ReviewsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }

    public String getPhoneDescription() {
        return phoneDescriptionFromReviewLink.getText();
    }
}
