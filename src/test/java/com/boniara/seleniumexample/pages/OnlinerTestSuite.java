package com.boniara.seleniumexample.pages;

import com.boniara.seleniumexample.utils.ChromeDriverLocal;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class OnlinerTestSuite {

    private WebDriver driver;
    private HomePage homePage;

    public OnlinerTestSuite() {
    }

    @Test(enabled = false)
    public void CheckOnlinerPage() throws InterruptedException {
        CatalogPage catalogPage = homePage.catalogClick();
        MobileCatalogPage mobileCatalogPage = catalogPage.mobileCatalogClick();
        mobileCatalogPage.xiaomiCheckboxClick();
        mobileCatalogPage.yearOfProductionCheckboxClick();
        PhonePage phonePage = mobileCatalogPage.maxReviewsPageClick();
        String description = phonePage.getPhoneDescription();
        ReviewsPage reviewsPage = phonePage.reviewsPageClick();
        String descriptionFromReviewsPage = reviewsPage.getPhoneDescription();
        Assert.assertEquals(description, descriptionFromReviewsPage);
    }

    @Test
    public void samsungCheck() throws InterruptedException {
        SearchIFrame frame = homePage.sendToInputField("sams");
        frame.checkResultContains("Sams").assertAll();
    }

    @Test
    public void loginWithFacebookCheck() throws InterruptedException {
        LoginWithFacebookPage loginWithFacebookPage = homePage.clickAndGetLoginFacebookWindow();
        loginWithFacebookPage.clickCancelButton();
        homePage.switchToHomeWindow();
        Assert.assertTrue(homePage.isLogotypeDisplayed());
    }

    @BeforeClass
    private void initDriver() {
        ChromeDriverLocal chromeDriverLocal = ChromeDriverLocal.getInstance();
        chromeDriverLocal.initDriver();
        driver = new ChromeDriver();
        homePage = new HomePage(driver);
    }

    @AfterClass
    private void quitDriver() {
        driver.quit();
    }
}
