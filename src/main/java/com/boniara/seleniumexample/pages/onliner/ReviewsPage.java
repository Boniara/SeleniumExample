package com.boniara.seleniumexample.pages.onliner;

import com.boniara.seleniumexample.pages.BasePage;
import com.boniara.seleniumexample.ui.TextLabel;
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
        TextLabel textLabel = new TextLabel(phoneDescriptionFromReviewLink);
        return textLabel.getValue();
    }
}
