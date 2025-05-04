import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;

    public class SeleniumTests {
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
        void openSiteTest() {
            driver.get("https://ya.ru/");
            assertEquals("Яндекс — быстрый поиск в интернете", driver.getTitle());
        }
    }

