package com.boniara.seleniumexample.pages.onliner;

import com.boniara.seleniumexample.webelements.TextField;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ReviewsPage extends BasePage {

    @FindBy(xpath = "//div[@class='ppdescr']")
    private WebElement phoneDescriptionFromReviewLink;

    public ReviewsPage(WebDriver driver) {
        super(driver);
    }

    public String getPhoneDescription() {
        TextField textField = new TextField(phoneDescriptionFromReviewLink);
        return textField.getValue();
    }
}
