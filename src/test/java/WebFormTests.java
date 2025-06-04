import extensions.AllureExtension;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import pages.WebFormPage;
import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(AllureExtension.class)
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

