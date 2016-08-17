package com.boniara.seleniumexample.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.Set;
import java.util.concurrent.TimeUnit;

public class HomePage {

    private WebDriver driver;
    private Set<String> currentWindowHandles;

    @FindBy(xpath = "//a[@href='http://catalog.onliner.by/']")
    private WebElement katalogPath;

    @FindBy(xpath = "//input[@class='fast-search__input']")
    private WebElement inputField;

    @FindBy(xpath = "//div[@id='userbar']//div[@title='Facebook']")
    private WebElement facebookLogin;

    @FindBy(xpath = "//div[@class='b-top-actions']//img[@class='onliner_logo retina-off']")
    private WebElement onlinerLogotype;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.driver.get("https://www.onliner.by/");
        this.driver.manage().timeouts().implicitlyWait(16, TimeUnit.SECONDS);
        this.currentWindowHandles = driver.getWindowHandles();
        PageFactory.initElements(this.driver, this);
    }

    public SearchIFrame sendToInputField(String data) {
        inputField.sendKeys(data);
        inputField.submit();
        return new SearchIFrame(driver, "modal-iframe");
    }

    public LoginWithFacebookPage clickAndGetLoginFacebookWindow() {
        facebookLogin.click();
        Set<String> windowHandles = driver.getWindowHandles();
        windowHandles.removeAll(currentWindowHandles);
        String newWindowHandle = windowHandles.iterator().next();
        driver.switchTo().window(newWindowHandle);
        return new LoginWithFacebookPage(driver);
    }

    public void switchToHomeWindow() {
        driver.switchTo().window(currentWindowHandles.iterator().next());
    }

    public boolean isLogotypeDisplayed() {
        return onlinerLogotype.isDisplayed();
    }

    public CatalogPage catalogClick() {
        katalogPath.click();
        return new CatalogPage(driver);
    }
}
