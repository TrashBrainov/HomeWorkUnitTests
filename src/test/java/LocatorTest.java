import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LocatorTest {
    WebDriver driver;

    @BeforeEach
    void init() {
        driver = new ChromeDriver();
    }

    @AfterEach
    void close() {
        driver.close();
    }

    @Test
    void headTest() {
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/web-form.html");
        WebElement head = driver.findElement(By.className("display-4"));
        assertEquals("Hands-On Selenium WebDriver with Java", head.getText());
    }

    @Test
    void logoClickTest() {
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/web-form.html");
        WebElement logo = driver.findElement(By.className("img-fluid"));
        logo.click();
        String actualUrl = driver.getCurrentUrl();
        assertEquals("https://github.com/bonigarcia/selenium-webdriver-java", actualUrl);
    }

    @Test
    void titleTest() {
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/web-form.html");
        WebElement title = driver.findElement(By.className("display-6"));
        assertEquals("Web form", title.getText());
    }

    @Test
    void findTextInputTest() {
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/web-form.html");
        WebElement textInput = driver.findElement(By.xpath("//label[contains(text(), 'Text input')]"));
    }

    @Test
    void TextInputTest() {
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/web-form.html");
        WebElement textInput = driver.findElement(By.id("my-text-id"));
        textInput.sendKeys("всем привет");
        String actualTextInput = textInput.getAttribute("value");
        assertEquals("всем привет", actualTextInput);
    }

    @Test
    void findPasswordTest() {
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/web-form.html");
        WebElement password = driver.findElement(By.xpath("//label[contains(text(), 'Password')]"));
    }

    @Test
    void findTextareaTest() {
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/web-form.html");
        WebElement textArea = driver.findElement(By.xpath("//label[contains(text(), 'Textarea')]"));
    }

    @Test
    void findDisableInputTest() {
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/web-form.html");
        WebElement disableInput = driver.findElement(By.xpath("//label[contains(text(), 'Disabled input')]"));
    }

    @Test
    void findReadOnlyTest() {
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/web-form.html");
        WebElement readOnly = driver.findElement(By.xpath("//label[contains(text(), 'Readonly input')]"));
    }

    @Test
    void clickReturnToIndexTest() {
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/web-form.html");
        WebElement button = driver.findElement(By.cssSelector("a[href='./index.html']"));
        button.click();
        String actualUrl = driver.getCurrentUrl();
        assertEquals("https://bonigarcia.dev/selenium-webdriver-java/index.html", actualUrl);
    }

}
