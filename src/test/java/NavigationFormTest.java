import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import static org.junit.jupiter.api.Assertions.*;


public class NavigationFormTest {
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
    void openNavigationMenu(){
    driver.get("https://bonigarcia.dev/selenium-webdriver-java");
    WebElement navigationButton = driver.findElement(By.cssSelector("a[href='navigation1.html']"));
    navigationButton.click();
    assertEquals("https://bonigarcia.dev/selenium-webdriver-java/navigation1.html", driver.getCurrentUrl());
    }

    @Test
    void firstPageNavigationButtonsTest() {
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/navigation1.html");
        WebElement previousButton = driver.findElement(By.xpath("//a[text()='Previous']"));
        WebElement parentLiPreviousButton = previousButton.findElement(By.xpath("./.."));
        assertTrue(parentLiPreviousButton.getAttribute("class").contains("disabled"));

        WebElement nextButton = driver.findElement(By.xpath("//a[text()='Next']"));
        WebElement parentLiNextButton = nextButton.findElement(By.xpath("./.."));
        assertFalse(parentLiNextButton.getAttribute("class").contains("disabled"));
    }

    @Test
    void secondPageNavigationButtonsTest(){
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/navigation2.html");
        WebElement previousButton2 = driver.findElement(By.xpath("//a[text()='Previous']"));
        WebElement parentLiPreviousButton2  = previousButton2.findElement(By.xpath("./.."));
        assertFalse(parentLiPreviousButton2.getAttribute("class").contains("disabled"));

        WebElement nextButton2 = driver.findElement(By.xpath("//a[text()='Next']"));
        WebElement parentLiNextButton2  = nextButton2.findElement(By.xpath("./.."));
        assertFalse(parentLiNextButton2.getAttribute("class").contains("disabled"));
    }

    @Test
    void thirdPageNavigationButtonsTest(){
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/navigation3.html");
        WebElement previousButton3 = driver.findElement(By.xpath("//a[text()='Previous']"));
        WebElement parentLiPreviousButton3  = previousButton3.findElement(By.xpath("./.."));
        assertFalse(parentLiPreviousButton3.getAttribute("class").contains("disabled"));

        WebElement nextButton3 = driver.findElement(By.xpath("//a[text()='Next']"));
        WebElement parentLiNextButton3  = nextButton3.findElement(By.xpath("./.."));
        assertTrue(parentLiNextButton3.getAttribute("class").contains("disabled"));
    }

    @Test
    void navigationPreviousAndNextButtonFlowTest(){
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/navigation1.html");
        WebElement nextButton = driver.findElement(By.xpath("//a[text()='Next']"));
        nextButton.click();
        assertEquals("https://bonigarcia.dev/selenium-webdriver-java/navigation2.html", driver.getCurrentUrl());


        WebElement nextButton2 = driver.findElement(By.xpath("//a[text()='Next']"));
        nextButton2.click();
        assertEquals("https://bonigarcia.dev/selenium-webdriver-java/navigation3.html", driver.getCurrentUrl());

        WebElement previousButton3 = driver.findElement(By.xpath("//a[text()='Previous']"));
        previousButton3.click();
        assertEquals("https://bonigarcia.dev/selenium-webdriver-java/navigation2.html", driver.getCurrentUrl());

    }

    @Test
    void navigationNumberPagesFlowTest(){
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/navigation1.html");
        WebElement page2 = driver.findElement(By.xpath("//a[text()='2']"));
        page2.click();
        assertEquals("https://bonigarcia.dev/selenium-webdriver-java/navigation2.html", driver.getCurrentUrl());


        WebElement page3 = driver.findElement(By.xpath("//a[text()='3']"));
        page3.click();
        assertEquals("https://bonigarcia.dev/selenium-webdriver-java/navigation3.html", driver.getCurrentUrl());

        WebElement page1 = driver.findElement(By.xpath("//a[text()='1']"));
        page1.click();
        assertEquals("https://bonigarcia.dev/selenium-webdriver-java/navigation1.html", driver.getCurrentUrl());
    }


}

