package com.boniara.seleniumexample.components.onliner;

import com.boniara.seleniumexample.pages.BasePage;
import com.boniara.seleniumexample.ui.CheckBox;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.List;

public class CheckboxBlock extends BasePage {

    private List<WebElement> webElementList;
    private List<WebElement> checboxList;

    public enum CheckboxType {

        PRODUCER("Производитель"), ANNOUNCED("Дата выхода на рынок");

        private String type;

        CheckboxType(String type) {
            this.type = type;
        }

        public String getType() {
            return type;
        }
    }

    @FindBy(xpath = "//div[@class='schema-filter__fieldset' and descendant::span[text()='Дата выхода на рынок']]" +
            "//span[@class='i-checkbox']")
    private List<WebElement> yearOfProductionCheckboxList;

    @FindBy(xpath = "//div[@class='schema-filter__fieldset' and descendant::span[text()='Производитель']]" +
            "//span[@class='i-checkbox']")
    private List<WebElement> produceOfPhoneCheckBoxList;

    @FindBy(xpath = "//div[@class='schema-filter__fieldset' and descendant::span[text()='Производитель']]" +
            "//span[@class='schema-filter__checkbox-text']")
    private List<WebElement> produceOfPhoneList;

    @FindBy(xpath = "//div[@class='schema-filter__fieldset' and descendant::span[text()='Дата выхода на рынок']]" +
            "//span[@class='schema-filter__checkbox-text']")
    private List<WebElement> yearOfProductionList;

    public CheckboxBlock(WebDriver driver) {
        super(driver);
        PageFactory.initElements(getDriver(), this);
    }

    private WebElement getCheckbox(String name) {
        WebElement currentYear = null;
        WebElement currentYearCheckBox = null;
        for(int index = 0; index < webElementList.size(); index++) {
            currentYear = webElementList.get(index);
            currentYearCheckBox = currentYear.getText().equals(name)
                    ? checboxList.get(index) : null;
            if(currentYearCheckBox != null)
                return currentYear;
        }
        return currentYear;
    }

    public void checkCheckbox(String name) {
        WebElement currentYearCheckBox = getCheckbox(name);
        CheckBox checkBox = new CheckBox(currentYearCheckBox);
        checkBox.check();
    }

    public void checkCheckbox(Integer name) {
        WebElement currentYearCheckBox = getCheckbox(name.toString());
        CheckBox checkBox = new CheckBox(currentYearCheckBox);
        checkBox.check();
    }

    public CheckboxBlock getCheckboxBlockForName(CheckboxType name) {
        switch(name) {
            case PRODUCER:
                webElementList = produceOfPhoneList;
                checboxList = produceOfPhoneCheckBoxList;
                break;
            case ANNOUNCED:
                webElementList = yearOfProductionList;
                checboxList = yearOfProductionCheckboxList;
                break;
            default: break;
        }
        return this;
    }
}
