package com.boniara.seleniumexample.pages.onliner;

import com.boniara.seleniumexample.webelements.CheckBox;
import com.boniara.seleniumexample.webelements.Link;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class MobileCatalogPage extends BasePage {

    private YearOfProduct yearOfProduct;

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
        this.yearOfProduct = new YearOfProduct();
    }

    class YearOfProduct {

        @FindBy(xpath = "//div[@class='schema-filter__fieldset' and descendant::span[text()='Дата выхода на рынок']]" +
                "//span[@class='i-checkbox']")
        private List<WebElement> yearOfProductionCheckboxList;

        @FindBy(xpath = "//div[@class='schema-filter__fieldset' and descendant::span[text()='Дата выхода на рынок']]" +
                "//span[@class='schema-filter__checkbox-text']")
        private List<WebElement> yearOfProductionList;

        public YearOfProduct() {
            PageFactory.initElements(driver, this);
        }

        public List<WebElement> getYearOfProductionCheckboxList() {
            return yearOfProductionCheckboxList;
        }

        public List<WebElement> getYearOfProductionList() {
            return yearOfProductionList;
        }
    }

    public void xiaomiCheckboxClick() {
        CheckBox checkBox = new CheckBox(xiaomiCheckbox);
        checkBox.click();
    }

    public void yearOfProductionCheckboxClick(Integer yearValue) {
        WebElement currentYearCheckBox = getYearOfProductionCheckBox(yearValue);
        ExpectedConditions.attributeToBe(stateAnimated, "class",
                "'schema-filter-button__state schema-filter-button__state_initial " +
                        "schema-filter-button__state_disabled schema-filter-button__state_animated'");
        while(!currentYearCheckBox.isEnabled()) {
            Thread.yield();
        }
        /*ExpectedConditions.attributeToBe(stateControl, "class", "schema-filter-button__state schema-filter-button__state_initial " +
                "schema-filter-button__state_disabled schema-filter-button__state_control");*/
        CheckBox checkBox = new CheckBox(currentYearCheckBox);
        checkBox.click();
    }

    private WebElement getYearOfProductionCheckBox(Integer yearOfProduction) {
        WebElement currentYear = null;
        WebElement currentYearCheckBox = null;
        for(int index = 0; index < yearOfProduct.getYearOfProductionList().size(); index++) {
            currentYear = yearOfProduct.getYearOfProductionList().get(index);
            currentYearCheckBox = currentYear.getText().equals(yearOfProduction.toString())
                    ? yearOfProduct.getYearOfProductionCheckboxList().get(index) : null;
            if(currentYearCheckBox != null)
                return currentYear;
        }
        return currentYear;
    }

    public PhonePage maxReviewsPageClick() {
        ExpectedConditions.attributeToBe(stateAnimated, "class",
                "'schema-filter-button__state schema-filter-button__state_initial " +
                        "schema-filter-button__state_disabled schema-filter-button__state_animated'");
        while(!maxReviewPhone.isEnabled()) {
            Thread.yield();
        }
        ExpectedConditions.elementToBeClickable(link);
        Link link = new Link(maxReviewPhone);
        link.click();
        return PageFactory.initElements(driver, PhonePage.class);
    }
}
