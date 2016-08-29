package com.boniara.seleniumexample.onliner;

import com.boniara.seleniumexample.onliner.elements.CheckboxBlock;
import com.boniara.seleniumexample.onliner.elements.SearchIFrame;
import com.boniara.seleniumexample.onliner.pages.*;
import com.boniara.seleniumexample.utils.TestListener;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(TestListener.class)
public class OnlinerTestSuite extends BaseTest {

    @Test(description = "Test check equals of phone descriptions from " +
            "phone page and reviews page")
    public void checkPhoneDescriptions() {
        HomePage homePage = PageFactory.initElements(getDriver(), HomePage.class);
        homePage.openPage();
        CatalogPage catalogPage = homePage.catalogClick();
        MobileCatalogPage mobileCatalogPage = catalogPage.mobileCatalogClick();
        CheckboxBlock checkboxBlock = PageFactory.initElements(getDriver(), CheckboxBlock.class);
        checkboxBlock.getCheckboxBlockForName("Производитель").checkCheckbox("Xiaomi");
        //pause(24);
        //checkboxBlock.getCheckboxBlockForName("Дата выхода на рынок").checkCheckbox(2016);
        pause(8);
        PhonePage phonePage = mobileCatalogPage.maxReviewsPageClick();
        String description = phonePage.getPhoneDescription();
        ReviewsPage reviewsPage = phonePage.reviewsPageClick();
        String descriptionFromReviewsPage = reviewsPage.getPhoneDescription();
        Assert.assertEquals(description, descriptionFromReviewsPage, "Phone description are not identical");
    }

    @Test(description = "Test check search logic from search iframe")
    public void checkSearchLogic() {
        HomePage homePage = PageFactory.initElements(getDriver(), HomePage.class);
        homePage.openPage();
        SearchIFrame frame = homePage.sendToInputField("sams");
        frame.checkResultContains("Sams").assertAll();
    }

    @Test(description = "Test check enables of loginWithFacebook page and visibled of onliner logotype")
    public void checkLoginWithFacebook() {
        HomePage homePage = PageFactory.initElements(getDriver(), HomePage.class);
        homePage.openPage();
        String pId = getDriver().getWindowHandle();
        LoginWithFacebookPage loginWithFacebookPage = homePage.clickAndGetLoginFacebookWindow();
        loginWithFacebookPage.clickCancelButton();
        getDriver().switchTo().window(pId);
        Assert.assertTrue(homePage.isLogotypeDisplayed(), "Onliner logotype is not displayed");
    }
}
