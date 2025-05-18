import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class DialogBoxesTest {
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
    void acceptLaunchAlertTest() {
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/dialog-boxes.html");
        driver.findElement(By.id("my-alert")).click();
        Alert alert = driver.switchTo().alert();
        alert.accept();
    }

    @Test
    void getTextLaunchAlertTest() {
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/dialog-boxes.html");
        driver.findElement(By.id("my-alert")).click();
        Alert alert = driver.switchTo().alert();
        String text = alert.getText();
        Assertions.assertEquals("Hello world!", text);
    }

    @Test
    void acceptLaunchConfirmTest() throws InterruptedException {
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/dialog-boxes.html");
        driver.findElement(By.id("my-confirm")).click();
        Alert alert = driver.switchTo().alert();
        alert.accept();
        WebElement result = driver.findElement(By.id("confirm-text"));
        Assertions.assertEquals("You chose: true", result.getText());
    }

    @Test
    void declineLaunchConfirmTest() {
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/dialog-boxes.html");
        driver.findElement(By.id("my-confirm")).click();
        Alert alert = driver.switchTo().alert();
        alert.dismiss();
        WebElement result = driver.findElement(By.id("confirm-text"));
        Assertions.assertEquals("You chose: false", result.getText());
    }

    @Test
    void declineLaunchPromptTest() {
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/dialog-boxes.html");
        driver.findElement(By.id("my-prompt")).click();
        Alert alert = driver.switchTo().alert();
        alert.dismiss();
        WebElement result = driver.findElement(By.id("prompt-text"));
        Assertions.assertEquals("You typed: null", result.getText());
    }

    @Test
    void acceptAndSendKeysLaunchPromptTest() {
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/dialog-boxes.html");
        driver.findElement(By.id("my-prompt")).click();
        Alert alert = driver.switchTo().alert();
        alert.sendKeys("Тестовый тест");
        alert.accept();
        WebElement result = driver.findElement(By.id("prompt-text"));
        Assertions.assertEquals("You typed: Тестовый тест", result.getText());
    }

    @Test
    void acceptAndSendKeysLaunchModalTest() throws InterruptedException {
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/dialog-boxes.html");
        driver.findElement(By.id("my-modal")).click();
        Thread.sleep(2000);
        WebElement saveButton = driver.findElement(By.xpath("btn btn-primary model-button"));
        saveButton.click();
        WebElement result = driver.findElement(By.id("modal-text"));
        Assertions.assertEquals("You chose: Save changes", result.getText());
    }

}
