package com.boniara.seleniumexample.onliner.pages.onliner;

import com.boniara.seleniumexample.onliner.pages.BasePage;
import com.boniara.seleniumexample.ui.Button;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginWithFacebookPage extends BasePage {

    private static final String URL = "https://www.facebook.com/" +
            "login.php?skip_api_login=1&api_key=837293952989787&signed_next=1&next=https%3A%2F%2Fwww." +
            "facebook.com%2Fv2.2%2Fdialog%2Foauth%3Fredirect_uri%3Dhttps%253A%252F%252Fuser.api.onliner." +
            "by%252Fsocials%252Ffacebook%253Fsession_id%253D6Zc8Jq7xjlMSdkLQGKB1%26display%" +
            "3Dpopup%26state%3D37eb9c76dc92749b08ab1639cc9b4e20%26scope%3Dpublic_profile%252Cemail%26client" +
            "_id%3D837293952989787%26ret%3Dlogin%26sdk%3Dphp-sdk-4.0.15%26logger" +
            "_id%3D97502e82-9029-40f4-948e-e4bedf7dd5c8&cancel_url=https%3A%2F%2Fuser.api.onliner.by" +
            "%2Fsocials%2Ffacebook%3Fsession_id%3D6Zc8Jq7xjlMSdkLQGKB1%26error%3Daccess_denied%26error" +
            "_code%3D200%26error_description%3DPermissions%2Berror%26error_reason%3Duser" +
            "_denied%26state%3D37eb9c76dc92749b08ab1639cc9b4e20%23_%3D_&display=popup&locale" +
            "=ru_RU&logger_id=97502e82-9029-40f4-948e-e4bedf7dd5c8";

    @FindBy(xpath = "//input[@value='Отмена']")
    private WebElement cancelButton;

    public LoginWithFacebookPage(WebDriver driver) {
        super(driver);
        setPageAbsoluteUrl(URL);
    }

    public void clickCancelButton() {
        Button button = new Button(cancelButton);
        button.click();
    }
}
