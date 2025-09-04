
import extensions.AllureExtension;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import pages.LoginPage;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.assertj.core.api.Assertions.assertThat;


@ExtendWith(AllureExtension.class)
public class LoginPagesTests {

    LoginPage login;

    @BeforeEach
    void setup() {
        login = new LoginPage("chrome");
    }

    @AfterEach
    void teardown() {
        login.quit();
    }

    @Test
    void testLoginSuccess() {
        login.successfulSignIn();
        assertThat(login.successMessageIsPresent()).isTrue();
    }

    @Test
    void testLoginFalse() {
        login.signIn("dsads", "sdsasd");
        assertEquals("Invalid credentials", login.invalidCredentials());
    }


}
