package com.boniara.seleniumexample.pages.onliner;

import com.boniara.seleniumexample.components.SearchIFrame;
import com.boniara.seleniumexample.pages.BasePage;
import com.boniara.seleniumexample.ui.Button;
import com.boniara.seleniumexample.ui.Image;
import com.boniara.seleniumexample.ui.Link;
import com.boniara.seleniumexample.ui.TextInput;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.Set;

public class HomePage extends BasePage {

    private static final String URL = "https://www.onliner.by";

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
        setPageAbsoluteUrl("https://www.onliner.by");
    }

    public SearchIFrame sendToInputField(String data) {
        TextInput textInput = new TextInput(inputField);
        textInput.sendKeys(data);
        textInput.submit();
        return PageFactory.initElements(getDriver(), SearchIFrame.class);
    }

    public LoginWithFacebookPage clickAndGetLoginFacebookWindow() {
        Set<String> pId = getDriver().getWindowHandles();
        Button button = new Button(facebookLogin);
        button.click();
        Set<String> windowHandles = getDriver().getWindowHandles();
        windowHandles.removeAll(pId);
        String newWindowHandle = windowHandles.iterator().next();
        getDriver().switchTo().window(newWindowHandle);
        return PageFactory.initElements(getDriver(), LoginWithFacebookPage.class);
    }

    public boolean isLogotypeDisplayed() {
        Image image = new Image(onlinerLogotype);
        return image.isDisplayed();
    }

    public CatalogPage catalogClick() {
        Link link = new Link(catalogPath);
        link.click();
        return PageFactory.initElements(getDriver(), CatalogPage.class);
    }
}
