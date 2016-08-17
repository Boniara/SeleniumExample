package com.boniara.seleniumexample.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CatalogPage {

    private WebDriver driver;

    @FindBy(xpath = "//a[@href='http://catalog.onliner.by/mobile' and" +
            " contains(@class, 'catalog-bar')]")
    private WebElement mobilesPath;

    public CatalogPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }


    public MobileCatalogPage mobileCatalogClick() {
        mobilesPath.click();
        return new MobileCatalogPage(driver);
    }
}
