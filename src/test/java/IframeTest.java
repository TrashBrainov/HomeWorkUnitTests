import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class IframeTest {
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
    void iframeTest() {
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/iframes.html");
        assertThrows(NoSuchElementException.class, () -> driver.findElement(By.className("lead")));
        WebElement iframeElement = driver.findElement(By.id("my-iframe"));
        driver.switchTo().frame(iframeElement);
        assertThrows(NoSuchElementException.class, () -> driver.findElement(By.className("display-6")));
        assertThat(driver.findElement(By.className("lead")).getText()).contains("Lorem ipsum dolor sit amet");
        driver.switchTo().defaultContent();
        assertThat(driver.findElement(By.className("display-6")).getText()).contains("IFrame");
    }
}
