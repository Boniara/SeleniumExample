package com.boniara.seleniumexample.pages.onliner;

import com.boniara.seleniumexample.webelements.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.Set;

public class HomePage extends BasePage {

    private Set<String> currentWindowHandles;

    @FindBy(xpath = "//a[@href='http://catalog.onliner.by/']")
    private WebElement catalogPath;

    @FindBy(xpath = "//input[@class='fast-search__input']")
    private WebElement inputField;

    @FindBy(xpath = "//div[@id='userbar']//div[@title='Facebook']")
    private WebElement facebookLogin;

    @FindBy(xpath = "//div[@class='b-top-actions']//img[@class='onliner_logo retina-off']")
    private WebElement onlinerLogotype;

    public HomePage(WebDriver driver) {
        super(driver);
        driver.get("https://www.onliner.by/");
        this.currentWindowHandles = driver.getWindowHandles();
    }

    public SearchIFrame sendToInputField(String data) {
        TextInput textInput = new TextInput(inputField);
        textInput.sendKeys(data);
        textInput.submit();
        return PageFactory.initElements(driver, SearchIFrame.class);
    }

    public LoginWithFacebookPage clickAndGetLoginFacebookWindow() {
        Button button = new Button(facebookLogin);
        button.click();
        Set<String> windowHandles = driver.getWindowHandles();
        windowHandles.removeAll(currentWindowHandles);
        String newWindowHandle = windowHandles.iterator().next();
        driver.switchTo().window(newWindowHandle);
        return PageFactory.initElements(driver, LoginWithFacebookPage.class);
    }

    public void switchToHomeWindow() {
        driver.switchTo().window(currentWindowHandles.iterator().next());
    }

    public boolean isLogotypeDisplayed() {
        Image image = new Image(onlinerLogotype);
        return image.isAccess();
    }

    public CatalogPage catalogClick() {
        Link link = new Link(catalogPath);
        link.click();
        return PageFactory.initElements(driver, CatalogPage.class);
    }
}
