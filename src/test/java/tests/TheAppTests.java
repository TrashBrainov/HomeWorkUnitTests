package tests;

import org.junit.jupiter.api.Test;
import screens.EchoScreen;
import screens.LoginScreen;

import static org.junit.jupiter.api.Assertions.*;

public class TheAppTests extends BaseTest {

    @Test
    public void testHomeScreenDisplayed() {
        assertTrue(homeScreen.isDisplayed());
        assertTrue(homeScreen.getNumberOfMenuItems() > 0);
    }

    @Test
    public void testEchoBox() {
        String testMessage = "Hello Appium!";

        EchoScreen echoScreen = homeScreen.navigateToEchoBox();

        echoScreen.enterMessage(testMessage)
                .tapSaveButton();

        assertTrue(echoScreen.isSavedMessageDisplayed());
        assertEquals("Saved: " + testMessage, echoScreen.getSavedMessage());
    }

    @Test
    public void testLoginSuccess() {
        String validUsername = "alice";
        String validPassword = "mypassword";

        LoginScreen loginScreen = homeScreen.navigateToLoginScreen();

        loginScreen.enterUsername(validUsername)
                .enterPassword(validPassword)
                .tapLoginButton();

        assertTrue(loginScreen.isSuccessMessageDisplayed());
        assertTrue(loginScreen.getSuccessMessage().contains(validUsername));
    }

    @Test
    public void testLoginFailure() {
        String invalidUsername = "invalid";
        String invalidPassword = "wrongpassword";

        LoginScreen loginScreen = homeScreen.navigateToLoginScreen();

        loginScreen.enterUsername(invalidUsername)
                .enterPassword(invalidPassword)
                .tapLoginButton();

        assertTrue(loginScreen.isErrorMessageDisplayed(), "Error message should be displayed after invalid login");
        assertTrue(loginScreen.getErrorMessage().contains("Invalid"),
                "Error message should indicate invalid credentials");
    }
}