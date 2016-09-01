package com.boniara.seleniumexample.onliner.pages.onliner;

import com.boniara.seleniumexample.onliner.pages.BasePage;
import com.boniara.seleniumexample.ui.Link;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class MobileCatalogPage extends BasePage {

    @FindBy(xpath = "//ul[@class='schema-filter__list']" +
            "//label[contains(@class, 'schema-filter') and descendant::span[contains(text(), 'Xiaomi')]]" +
            "//span[@class='i-checkbox']")
    private WebElement xiaomiCheckbox;

    @FindBy(xpath = "//div[contains(@class, 'schema-filter-button__state_disabled')]" +
            "//span[contains(@class, 'sub_control')]")
    private WebElement productGroup;

    @FindBy(xpath = "//div[contains(@class, 'state_animated')]")
    private WebElement stateAnimated;

    @FindBy(xpath = "//div[contains(@class, 'state_control')]")
    private WebElement stateControl;

    @FindBy(xpath = "//div[@class='schema-product__group'][1]//a[./span[@data-bind='html: product.full_name']]")
    private WebElement link;

    @FindBy(xpath = "//div[@class='schema-product__group' and" +
            " descendant::span[@data-bind='text: product.reviews.count' and" +
            " contains(text(),//span[@data-bind='text: product.reviews.count']" +
            "/text()[not(.<//span[@data-bind='text: product.reviews.count']/text())])]]" +
            "//a[descendant::span[@data-bind='html: product.full_name']]")
    private WebElement maxReviewPhone;

    public MobileCatalogPage(WebDriver driver) {
        super(driver);
        setPageUrl("/mobile");
    }

    public PhonePage maxReviewsPageClick() {
        while(!maxReviewPhone.isEnabled()) {
            Thread.yield();
        }
        ExpectedConditions.elementToBeClickable(link);
        Link link = new Link(maxReviewPhone);
        link.click();
        return PageFactory.initElements(getDriver(), PhonePage.class);
    }
}
