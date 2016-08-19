package com.boniara.seleniumexample.pages.onliner;

import com.boniara.seleniumexample.webelements.Link;
import com.boniara.seleniumexample.webelements.TextField;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PhonePage extends BasePage {

    @FindBy(xpath = "//div[@class='b-offers-desc__info-specs']/p[1]")
    private WebElement phoneDescription;

    @FindBy(xpath = "//a[@href='http://catalog.onliner.by/mobile/xiaomi/note3pro16gr/reviews'" +
            " and @class='b-offers-desc__reviews']")
    private WebElement reviewLink;

    public PhonePage(WebDriver driver) {
        super(driver);
    }

    public String getPhoneDescription() {
        TextField textField = new TextField(phoneDescription);
        return textField.getValue();
    }

    public ReviewsPage reviewsPageClick() {
        Link link = new Link(reviewLink);
        link.click();
        return PageFactory.initElements(driver, ReviewsPage.class);
    }
}
