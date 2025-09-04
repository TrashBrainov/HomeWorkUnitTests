package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage{

    private static final String VALID_USER = "user";
    private static final String VALID_PASSWORD = "user";

    By loginInput = By.id("username");
    By passwordInput = By.id("password");
    By submitButton = By.xpath("//button[@type='submit']");

    public LoginPage(String browser) {
        super(browser);
        visit("https://bonigarcia.dev/selenium-webdriver-java/login-form.html");
    }


    public void successfulSignIn() {
        driver.findElement(loginInput).sendKeys(VALID_USER);
        driver.findElement(passwordInput).sendKeys(VALID_PASSWORD);
        driver.findElement(submitButton).click();
    }

    public void signIn(String user, String password) {
        driver.findElement(loginInput).sendKeys(user);
        driver.findElement(passwordInput).sendKeys(password);
        driver.findElement(submitButton).click();
    }

    public boolean successMessageIsPresent() {
        return driver.findElement(By.id("success")).isDisplayed();
    }

    public String invalidCredentials() {
        return driver.findElement(By.id("invalid")).getText();
    }
}

