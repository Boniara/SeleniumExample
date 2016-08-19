package com.boniara.seleniumexample.pages.onliner;

import com.boniara.seleniumexample.webelements.Link;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CatalogPage extends BasePage {

    @FindBy(xpath = "//a[@href='http://catalog.onliner.by/mobile' and" +
            " contains(@class, 'catalog-bar')]")
    private WebElement mobilesPath;

    public CatalogPage(WebDriver driver) {
        super(driver);
    }


    public MobileCatalogPage mobileCatalogClick() {
        Link mobilePathLink = new Link(mobilesPath);
        mobilePathLink.click();
        return PageFactory.initElements(driver, MobileCatalogPage.class);
    }
}
