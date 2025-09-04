import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class CookiesTest {
    WebDriver driver;

    @BeforeEach
    void start() {
        driver = new ChromeDriver();
    }

    @AfterEach
    void teardown() {
        driver.quit();
    }

    @Test
    void countCookieTest(){
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/cookies.html");
        Set<Cookie> cookies = driver.manage().getCookies();
        assertThat(cookies).hasSize(2);
    }

    @Test
    void userNameCookieTest(){
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/cookies.html");
        WebDriver.Options options = driver.manage();
        Cookie username = options.getCookieNamed("username");
        assertThat(username.getValue()).isEqualTo("John Doe");
    }

    @Test
    void dateCookieTest(){
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/cookies.html");
        WebDriver.Options options = driver.manage();
        Cookie date = options.getCookieNamed("date");
        assertThat(date.getValue()).isEqualTo("10/07/2018");
    }

    @Test
    void deleteCookieTest(){
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/cookies.html");
        driver.manage().deleteCookieNamed("username");
        Set<Cookie> cookies = driver.manage().getCookies();
        assertThat(cookies).hasSize(1);
    }

    @Test
    void addCookieTest(){
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/cookies.html");
        driver.manage().addCookie(new Cookie("Test", "Test-1"));
        Set<Cookie> cookies = driver.manage().getCookies();
        assertThat(cookies).hasSize(3);
        WebDriver.Options options = driver.manage();
        Cookie test = options.getCookieNamed("Test");
        assertThat(test.getValue()).isEqualTo("Test-1");
    }

    @Test
    void deleteAllCookieTest(){
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/cookies.html");
        driver.manage().deleteAllCookies();
        Set<Cookie> cookies = driver.manage().getCookies();
        assertThat(cookies).hasSize(0);
    }
}
