package screens;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

public class LoginScreen extends BaseScreen {

    @AndroidFindBy(accessibility = "username")
    private WebElement usernameField;

    @AndroidFindBy(accessibility = "password")
    private WebElement passwordField;

    @AndroidFindBy(accessibility = "loginBtn")
    private WebElement loginButton;

    @AndroidFindBy(xpath = "//android.widget.TextView[contains(@text, 'Invalid')]")
    private WebElement errorMessage;

    @AndroidFindBy(xpath = "//android.widget.TextView[contains(@text, 'You are logged in')]")
    private WebElement successMessage;

    public LoginScreen(AndroidDriver driver) {
        super(driver);
    }

    public LoginScreen enterUsername(String username) {
        enterText(usernameField, username);
        return this;
    }

    public LoginScreen enterPassword(String password) {
        enterText(passwordField, password);
        return this;
    }

    public LoginScreen tapLoginButton() {
        clickElement(loginButton);
        return this;
    }

    public boolean isErrorMessageDisplayed() {
        try {
            return waitForElement(errorMessage).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isSuccessMessageDisplayed() {
        try {
            return waitForElement(successMessage).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public String getSuccessMessage() {
        return getText(successMessage);
    }

    public String getErrorMessage() {
        return getText(errorMessage);
    }
}