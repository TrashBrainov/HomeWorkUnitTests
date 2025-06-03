package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class WebFormPage extends BasePage{
    By textInput = By.id("my-text-id");
    By passwordInput = By.name("my-password");
    By textAreaInput = By.name("my-textarea");
    By disabledField = By.name("my-disabled");
    By readOnlyInput = By.name("my-readonly");
    By selectFromList = By.name("my-select");

    public WebFormPage(String browser) {
        super(browser);
        visit("https://bonigarcia.dev/selenium-webdriver-java/web-form.html");
    }

    public void textInput(String text) {
        driver.findElement(textInput).click();
        driver.findElement(textInput).sendKeys(text);
    }

    public void passwordInput(String password) {
        driver.findElement(passwordInput).click();
        driver.findElement(passwordInput).sendKeys(password);
    }

    public void textAreaInput(String text) {
        driver.findElement(textAreaInput).click();
        driver.findElement(textAreaInput).sendKeys(text);
    }

    public void disabledField() {
        driver.findElement(disabledField);
    }

    public void readOnlyInput(String text) {
        driver.findElement(readOnlyInput).click();
        driver.findElement(readOnlyInput).sendKeys(text);
    }

    public void textAreaInputClear(String text) {
        driver.findElement(textAreaInput).click();
        driver.findElement(textAreaInput).sendKeys(text);
        driver.findElement(textAreaInput).clear();
    }

    public Object selectFromList() {
        driver.findElement(selectFromList);
        Select select = new Select((WebElement) selectFromList);
        select.selectByVisibleText("Two");

        return null;
    }

    public String textInputValue() {
        return driver.findElement(By.id("my-text-id")).getAttribute("value");
    }

    public String passwordInputValue() {
        return driver.findElement(By.name("my-password")).getAttribute("value");
    }

    public String textAreaInputValue() {
        return driver.findElement(By.name("my-textarea")).getAttribute("value");
    }

    public boolean disabledFieldIsDisabled() {
        return driver.findElement(By.name("my-disabled")).isEnabled();
    }

    public String readOnlyInputValue() {
        return driver.findElement(By.name("my-readonly")).getAttribute("value");
    }

    public String selectFromListValue() {
        return driver.findElement(By.name("my-readonly")).getText();
    }
}
