package com.boniara.seleniumexample.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.asserts.SoftAssert;

import java.util.List;

public class SearchIFrame {

    private WebDriver driver;

    @FindBy(xpath = "//div[@class='search__content-wrapper']//div[@class='result__wrapper']//a[@class='product__title-link']")
    private List<WebElement> productTitleList;

    public SearchIFrame(WebDriver driver, String iframeClassName) {
        this.driver = driver;
        this.driver.switchTo().frame(driver.findElement(By.className(iframeClassName)));
        PageFactory.initElements(this.driver, this);
    }

    public SoftAssert checkResultContains(String containsString) {
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(! productTitleList.isEmpty());
        for(WebElement productTitle: productTitleList) {
            String title = productTitle.getText();
            softAssert.assertTrue(title.contains(containsString));
        }
        return softAssert;
    }
}
