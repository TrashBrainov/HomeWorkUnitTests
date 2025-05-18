import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import static org.junit.jupiter.api.Assertions.*;

public class DropDownTests {
    WebDriver driver;
    @BeforeEach
    void start() {
        driver = new ChromeDriver();
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/dropdown-menu.html");
    }

    @AfterEach
    void close() {
        driver.quit();
    }

    @Test
    void mouseClickTest() {
        WebElement leftClickDropDown = driver.findElement(By.id("my-dropdown-1"));
        new Actions(driver)
                .click(leftClickDropDown)
                .perform();

        WebElement rightClickDropDown = driver.findElement(By.id("my-dropdown-2"));
        new Actions(driver)
                .click(rightClickDropDown)
                .perform();

        WebElement doubleClickDropDown = driver.findElement(By.id("my-dropdown-3"));
        new Actions(driver)
                .click(doubleClickDropDown)
                .perform();
    }

}
