package com.boniara.seleniumexample.onliner.elements;

import com.boniara.seleniumexample.onliner.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.asserts.SoftAssert;
import java.util.List;

public class SearchIFrame extends BasePage {

    @FindBy(xpath = "//div[@class='search__content-wrapper']//div[@class='result__wrapper']//a[@class='product__title-link']")
    private List<WebElement> productTitleList;

    public SearchIFrame(WebDriver driver) {
        super(driver);
        driver.switchTo().frame(driver.findElement(By.className("modal-iframe")));
    }

    public SoftAssert checkResultContains(String containsString) {
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(! productTitleList.isEmpty(), "Result list is empty");
        for(WebElement productTitle: productTitleList) {
            String title = productTitle.getText();
            softAssert.assertTrue(title.contains(containsString), "Product title text don`t contains the product" +
                    " title text from reviews page. Expected value = " + title + " actual value = " + containsString);
        }
        return softAssert;
    }
}
