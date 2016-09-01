package com.boniara.seleniumexample.onliner.pages.onliner;

import com.boniara.seleniumexample.onliner.pages.BasePage;
import com.boniara.seleniumexample.ui.Link;
import com.boniara.seleniumexample.ui.TextLabel;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PhonePage extends BasePage {

    @FindBy(xpath = "//div[@class='b-offers-desc__info-specs']/p[1]")
    private WebElement phoneDescription;

    @FindBy(xpath = "//a[@class='b-offers-desc__reviews']")
    private WebElement reviewLink;

    public PhonePage(WebDriver driver) {
        super(driver);
    }

    public String getPhoneDescription() {
        TextLabel textLabel = new TextLabel(phoneDescription);
        return textLabel.getValue();
    }

    public ReviewsPage reviewsPageClick() {
        Link link = new Link(reviewLink);
        link.click();
        return PageFactory.initElements(getDriver(), ReviewsPage.class);
    }
}
