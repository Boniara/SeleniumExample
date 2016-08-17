package com.boniara.seleniumexample.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PhonePage {

    private WebDriver driver;

    @FindBy(xpath = "//div[@class='b-offers-desc__info-specs']/p[1]")
    private WebElement phoneDescription;

    @FindBy(xpath = "//a[@href='http://catalog.onliner.by/mobile/xiaomi/note3pro16gr/reviews'" +
            " and @class='b-offers-desc__reviews']")
    private WebElement reviewLink;

    public PhonePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }

    public String getPhoneDescription() {
        return phoneDescription.getText();
    }

    public ReviewsPage reviewsPageClick() {
        reviewLink.click();
        return new ReviewsPage(driver);
    }
}
