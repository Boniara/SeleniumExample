package com.boniara.seleniumexample.pages;

import com.boniara.seleniumexample.pages.onliner.*;
import com.boniara.seleniumexample.utils.TestListener;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(TestListener.class)
public class OnlinerTestSuite extends BaseTest {

    private HomePage homePage;

    public OnlinerTestSuite() {
    }

    @Test(description = "Test check equals of phone descriptions from " +
            "phone page and reviews page")
    public void checkPhoneDescriptions() throws InterruptedException {
        homePage = PageFactory.initElements(driver, HomePage.class);
        CatalogPage catalogPage = homePage.catalogClick();
        MobileCatalogPage mobileCatalogPage = catalogPage.mobileCatalogClick();
        mobileCatalogPage.xiaomiCheckboxClick();
        Thread.sleep(1000);
        mobileCatalogPage.yearOfProductionCheckboxClick(2016);
        Thread.sleep(5000);
        PhonePage phonePage = mobileCatalogPage.maxReviewsPageClick();
        String description = phonePage.getPhoneDescription();
        ReviewsPage reviewsPage = phonePage.reviewsPageClick();
        String descriptionFromReviewsPage = reviewsPage.getPhoneDescription();
        Assert.assertEquals(description, descriptionFromReviewsPage, "Phone description are not identical." +
                " Expected value = " + description + " actual value = " + descriptionFromReviewsPage);
    }

    @Test(description = "Test check search logic from search iframe")
    public void checkSearchLogic() throws InterruptedException {
        homePage = PageFactory.initElements(driver, HomePage.class);
        SearchIFrame frame = homePage.sendToInputField("sams");
        frame.checkResultContains("Sams").assertAll();
    }

    @Test(description = "Test check enables of loginWithFacebook page and visibled of onliner logotype")
    public void checkLoginWithFacebook() throws InterruptedException {
        homePage = PageFactory.initElements(driver, HomePage.class);
        LoginWithFacebookPage loginWithFacebookPage = homePage.clickAndGetLoginFacebookWindow();
        loginWithFacebookPage.clickCancelButton();
        homePage.switchToHomeWindow();
        Assert.assertTrue(homePage.isLogotypeDisplayed(), "Onliner logotype is not displayed");
    }
}
