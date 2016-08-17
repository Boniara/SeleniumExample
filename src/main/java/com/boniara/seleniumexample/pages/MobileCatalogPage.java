package com.boniara.seleniumexample.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class MobileCatalogPage {

    private WebDriver driver;

    @FindBy(xpath = "//ul[@class='schema-filter__list']" +
            "//label[contains(@class, 'schema-filter') and descendant::span[contains(text(), 'Xiaomi')]]" +
            "//span[@class='i-checkbox']")
    private WebElement xiaomiCheckbox;

    @FindBy(xpath = "//label[contains(@class, 'schema-filter') and" +
            " descendant::span[contains(text(), '2016')]]//span[@class='i-checkbox']")
    private WebElement yearOfProductionCheckbox;

    @FindBy(xpath = "//div[@class='schema-product__group'][1]" +
            "//a[./span[@data-bind='html: product.full_name']]")
    private WebElement productGroup;

    @FindBy(xpath = "//div[@class='schema-product__group' and" +
            " descendant::span[@data-bind='text: product.reviews.count' and" +
            " contains(text(),//span[@data-bind='text: product.reviews.count']" +
            "/text()[not(.<//span[@data-bind='text: product.reviews.count']/text())])]]" +
            "//a[descendant::span[@data-bind='html: product.full_name']]")
    private WebElement maxReviewPhone;

    public MobileCatalogPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }

    public void xiaomiCheckboxClick() {
        ExpectedConditions.elementToBeClickable(xiaomiCheckbox);
        xiaomiCheckbox.click();
    }

    public void yearOfProductionCheckboxClick() {
        ExpectedConditions.elementToBeClickable(yearOfProductionCheckbox);
        yearOfProductionCheckbox.click();
    }

    public PhonePage maxReviewsPageClick() {
        ExpectedConditions.elementToBeClickable(productGroup);
        maxReviewPhone.click();
        return new PhonePage(driver);
    }
}
