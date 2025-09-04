import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.LoginPage;
import pages.WebFormPage;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.Collection;

public class WebFormTests {
    WebFormPage webPage;

    @BeforeEach
    void setup() {
        webPage = new WebFormPage("chrome");
    }

    @AfterEach
    void teardown() {
        webPage.quit();
    }

    @Test
    void textInputTest() {
        webPage.textInput("Тестовый текст");
        assertEquals("Тестовый текст", webPage.textInputValue());
    }

    @Test
    void passwordInputTest() {
        webPage.passwordInput("Password");
        assertEquals("Password", webPage.passwordInputValue());
    }

    @Test
    void textAreaInputTest() {
        webPage.textAreaInput("asdsdsadsddsa skdjsadsajdhkj hasjkdh jkshdjksah jdshkdh asjkhdsjkhd jkashdk jashdkj haskddsakj hdskajhd kashd kjashdk jhasdjk shdjksah khdskajhdjkash89-099-");
        assertEquals("asdsdsadsddsa skdjsadsajdhkj hasjkdh jkshdjksah jdshkdh asjkhdsjkhd jkashdk jashdkj haskddsakj hdskajhd kashd kjashdk jhasdjk shdjksah khdskajhdjkash89-099-", webPage.textAreaInputValue());
    }

    @Test
    void disabledFieldTest() {
      webPage.disabledField();
        assertFalse(webPage.disabledFieldIsDisabled());
    }


    @Test
    void readOnlyInputTest() {
        webPage.readOnlyInput("Password");
        assertEquals("Readonly input",webPage.readOnlyInputValue());
    }

    @Test
    void textAreaInputClearTest() {
        webPage.textAreaInputClear("asdsdsadsddsa skdjsadsajdhkj hasjkdh jkshdjksah jdshkdh asjkhdsjkhd jkashdk jashdkj haskddsakj hdskajhd kashd kjashdk jhasdjk shdjksah khdskajhdjkash89-099-");
        assertEquals("", webPage.textAreaInputValue());

    }

}

