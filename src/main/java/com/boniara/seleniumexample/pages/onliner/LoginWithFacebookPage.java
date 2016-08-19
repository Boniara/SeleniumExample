package com.boniara.seleniumexample.pages.onliner;

import com.boniara.seleniumexample.webelements.Button;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginWithFacebookPage extends BasePage {

    @FindBy(xpath = "//input[@value='Отмена']")
    private WebElement cancelButton;

    public LoginWithFacebookPage(WebDriver driver) {
        super(driver);
    }

    public void clickCancelButton() {
        Button button = new Button(cancelButton);
        button.click();
    }
}
