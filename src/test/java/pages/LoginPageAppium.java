package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPageAppium extends BasePageAppium {

    @FindBy(id = "username")
    private WebElement usernameField;

    @FindBy(id = "password")
    private WebElement passwordField;

    @FindBy(css = "button.btn-primary")
    private WebElement submitButton;

    @FindBy(id = "login-alert")
    private WebElement loginAlert;

    private final String url = "https://bonigarcia.dev/selenium-webdriver-java/login-form.html";

    public LoginPageAppium(WebDriver driver) {
        super(driver);
    }

    public static LoginPageAppium open() {
        driver.get(url);
        return this;
    }

    public LoginPageAppium enterUsername(String username) {
        enterText(usernameField, username);
        return this;
    }

    public LoginPageAppium enterPassword(String password) {
        enterText(passwordField, password);
        return this;
    }

    public LoginPageAppium clickSubmitButton() {
        clickElement(submitButton);
        return this;
    }

    public  boolean isAlertDisplayed() {
        try {
            return waitForElement(loginAlert).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public  String getAlertText() {
        return getText(loginAlert);
    }

    public boolean isAlertSuccess() {
        return isAlertDisplayed() && loginAlert.getAttribute("class").contains("alert-success");
    }

    public  boolean isAlertError() {
        return isAlertDisplayed() && loginAlert.getAttribute("class").contains("alert-danger");
    }

    public LoginPageAppium login(String username, String password) {
        return enterUsername(username)
                .enterPassword(password)
                .clickSubmitButton();
    }
}