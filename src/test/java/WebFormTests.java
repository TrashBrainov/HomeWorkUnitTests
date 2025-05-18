import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
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
    WebDriver driver;
    @BeforeEach
    void start() {
        driver = new ChromeDriver();
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/web-form.html");
    }

    @AfterEach
    void close() {
        driver.quit();
    }

    @Test
    void textInputTest() {
        WebElement textInput = driver.findElement(By.id("my-text-id"));
        textInput.click();
        textInput.sendKeys("Тестовый текст");
        assertEquals("Тестовый текст",textInput.getAttribute("value"));
    }

    @Test
    void passwordInputTest() {
        WebElement passwordInput = driver.findElement(By.name("my-password"));
        passwordInput.click();
        passwordInput.sendKeys("Password");
        assertEquals("Password",passwordInput.getAttribute("value"));
    }

    @Test
    void textAreaInputTest() {
        WebElement textAreaInput = driver.findElement(By.name("my-textarea"));
        textAreaInput.click();
        textAreaInput.sendKeys("asdsdsadsddsa skdjsadsajdhkj hasjkdh jkshdjksah jdshkdh asjkhdsjkhd jkashdk jashdkj haskddsakj hdskajhd kashd kjashdk jhasdjk shdjksah khdskajhdjkash89-099-");
        assertEquals("asdsdsadsddsa skdjsadsajdhkj hasjkdh jkshdjksah jdshkdh asjkhdsjkhd jkashdk jashdkj haskddsakj hdskajhd kashd kjashdk jhasdjk shdjksah khdskajhdjkash89-099-",textAreaInput.getAttribute("value"));
    }

    @Test
    void disabledFieldTest() {
        WebElement disabledInput = driver.findElement(By.name("my-disabled"));
        assertFalse(disabledInput.isEnabled());

        assertThrows(ElementNotInteractableException.class, () -> {
            disabledInput.click();
            disabledInput.sendKeys("Password");
        } );
    }


    @Test
    void readOnlyInputTest() {
        WebElement textInput = driver.findElement(By.name("my-readonly"));
        textInput.click();
        textInput.sendKeys("Тестовый текст");
        assertEquals("Readonly input",textInput.getAttribute("value"));
    }

    @Test
    void textAreaInputClearTest() {
        WebElement textAreaInput = driver.findElement(By.name("my-textarea"));
        textAreaInput.click();
        textAreaInput.sendKeys("asdsdsadsddsa skdjsadsajdhkj hasjkdh jkshdjksah jdshkdh asjkhdsjkhd jkashdk jashdkj haskddsakj hdskajhd kashd kjashdk jhasdjk shdjksah khdskajhdjkash89-099-");
        textAreaInput.clear();
        assertEquals("",textAreaInput.getAttribute("value"));
    }

    @Test
    void selectFromListTest(){
        WebElement dropdownSelectMenu = driver.findElement(By.name("my-select"));
        Select select = new Select(dropdownSelectMenu);
        select.selectByVisibleText("Two");
        assertEquals("Two", select.getFirstSelectedOption().getText());
    }

    @Test
    void dataListInputTest(){
        WebElement dataListInput = driver.findElement(By.name("my-datalist"));
        dataListInput.click();
        dataListInput.sendKeys("San Francisco");
        assertEquals("San Francisco", dataListInput.getAttribute("value"));
    }

    @Test
    void fileUploadTest() throws IOException, InterruptedException {
        String filePath = "src/main/resources/text.txt";

        String content = new String(Files.readAllBytes(Paths.get(filePath)));

        System.out.println("Содержимое файла: " + content);

        URL url = WebFormTests.class.getClassLoader().getResource("text.txt");

        String absolutePath = null;
        if (url != null) {
            absolutePath = new File(url.getPath()).getAbsolutePath();
            System.out.println("Абсолютный путь к файлу: " + absolutePath);
        } else {
            System.out.println("Ресурс не найден.");
        }
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/web-form.html");
        WebElement fileUpload = driver.findElement(By.name("my-file"));
        fileUpload.sendKeys(absolutePath);
        Thread.sleep(5000);
        WebElement submit = driver.findElement(By.xpath("//button[text()='Submit']"));
        submit.click();
        Thread.sleep(5000);
        assertThat(driver.getCurrentUrl()).contains("text.txt");
    }

    @Test
    void dateInputTest(){
        WebElement dataListInput = driver.findElement(By.name("my-date"));
        dataListInput.click();
        dataListInput.sendKeys("12/05/2025");
        assertEquals("12/05/2025", dataListInput.getAttribute("value"));
    }

    @Test
    void selectAllCheckBoxTest(){
        WebElement selectCheckbox1 = driver.findElement(By.id("my-check-1"));
        if (selectCheckbox1.isSelected()) {
            selectCheckbox1.click();
        }
        selectCheckbox1.click();
        WebElement selectCheckbox2 = driver.findElement(By.id("my-check-2"));
        if (selectCheckbox2.isSelected()) {
            selectCheckbox2.click();
        }
        selectCheckbox2.click();

        assertTrue(selectCheckbox1.isSelected(), "Первый чекбокс должен быть выбран");
        assertTrue(selectCheckbox2.isSelected(), "Второй чекбокс должен быть выбран");
    }

    @Test
    void defaultSelectCheckBoxTest(){
        WebElement checkbox1 = driver.findElement(By.id("my-radio-1"));
        WebElement checkbox2 = driver.findElement(By.id("my-radio-2"));

        assertTrue(checkbox1.isSelected(), "Первый чекбокс должен быть выбран");
        assertFalse(checkbox2.isSelected(), "Второй чекбокс не должен быть выбран");
    }

    @Test
    void defaultSelectRadioTest(){
        WebElement radio1 = driver.findElement(By.id("my-radio-1"));
        WebElement radio2 = driver.findElement(By.id("my-radio-2"));

        assertTrue(radio1.isSelected(), "Первый чекбокс должен быть выбран");
        assertFalse(radio2.isSelected(), "Второй чекбокс не должен быть выбран");
    }

    @Test
    void selectSecondAndSelectFirstAfterSecondRadio() {
        WebElement radio1 = driver.findElement(By.id("my-radio-1"));
        WebElement radio2 = driver.findElement(By.id("my-radio-2"));
        radio2.click();

        assertFalse(radio1.isSelected(), "Первый чекбокс не должен быть выбран");
        assertTrue(radio2.isSelected(), "Второй чекбокс должен быть выбран");

        radio1.click();
        assertTrue(radio1.isSelected(), "Первый чекбокс должен быть выбран");
        assertFalse(radio2.isSelected(), "Второй чекбокс не должен быть выбран");
    }


}
