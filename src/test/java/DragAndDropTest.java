import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class DragAndDropTest {
    WebDriver driver;
    @BeforeEach
    void start() {
        driver = new ChromeDriver();
    }

    @AfterEach
    void close() {
        driver.quit();
    }

    @Test
    void actionAPIDragAndDropTests() {
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/drag-and-drop.html");

        WebElement draggable = driver.findElement(By.id("draggable"));
        WebElement droppable = driver.findElement(By.id("target"));
        new Actions(driver)
                .dragAndDrop(draggable, droppable)
                .perform();

    }
}
