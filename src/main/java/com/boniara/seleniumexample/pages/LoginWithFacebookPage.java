package com.boniara.seleniumexample.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginWithFacebookPage {

    private WebDriver driver;

    @FindBy(xpath = "//input[@value='Отмена']")
    private WebElement cancelButton;

    public LoginWithFacebookPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }

    public void clickCancelButton() {
        cancelButton.click();
    }
}
