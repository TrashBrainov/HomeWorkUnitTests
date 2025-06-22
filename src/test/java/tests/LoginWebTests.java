package tests;

import org.junit.jupiter.api.Test;
import pages.LoginPageAppium;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginWebTests extends BaseWebTest {

    @Test
    public void testLoginSuccess() {

        String validUsername = "user";
        String validPassword = "user";

        LoginPageAppium loginPage = new LoginPageAppium(driver);

        LoginPageAppium.open()
                .login(validUsername, validPassword);

        assertTrue(loginPage.isAlertDisplayed());
        assertTrue(loginPage.isAlertSuccess());
        assertTrue(loginPage.getAlertText().contains("logged in"));
    }

    @Test
    public void testLoginFailureInvalidUsername() {
        String invalidUsername = "wronguser";
        String validPassword = "user";

        LoginPageAppium loginPage = new LoginPageAppium(driver);

        LoginPageAppium.open()
                .login(invalidUsername, validPassword);

        assertTrue(loginPage.isAlertDisplayed());
        assertTrue(loginPage.isAlertError());
        assertTrue(loginPage.getAlertText().contains("Invalid"));
    }

    @Test
    public void testLoginFailureInvalidPassword() {
        String validUsername = "user";
        String invalidPassword = "wrongpassword";

        LoginPageAppium loginPage = new LoginPageAppium(driver);

        LoginPageAppium.open()
                .login(validUsername, invalidPassword);

        assertTrue(loginPage.isAlertDisplayed());
        assertTrue(loginPage.isAlertError());
        assertTrue(loginPage.getAlertText().contains("Invalid"));
    }

    @Test
    public void testLoginFailureEmptyCredentials() {
        LoginPageAppium loginPage = new LoginPageAppium(driver);

        LoginPageAppium.open()
                .login("", "");

        assertTrue(loginPage.isAlertDisplayed());
        assertTrue(loginPage.isAlertError());
        assertTrue(loginPage.getAlertText().contains("invalid"));
    }
}