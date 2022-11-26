import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class CardApplicationSuccessTest {

    private WebDriver driver;
        @BeforeAll
        static void setUpAll() {
            WebDriverManager.chromedriver().setup();
            //System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
        }
        @BeforeEach
        void setUp() {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--disable-dev-shm-usage");
            options.addArguments("--no-sandbox");
            options.addArguments("--headless");
            driver = new ChromeDriver(options);
        }
        @AfterEach
        void tearDown() {
            driver.quit();
            driver = null;
        }

        @Test
        void shouldRunSuccessfullyWithAllData() {
            driver.get("http://localhost:7777/");
            driver.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("Мария Васильева");
            driver.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("+79001112233");
            driver.findElement(By.cssSelector("[data-test-id=agreement]")).click();
            driver.findElement(By.className("button_theme_alfa-on-white")).click();
            String expected = "  Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.";
            String actual = driver.findElement(By.cssSelector("[data-test-id=order-success]")).getText();
            Assertions.assertEquals(expected, actual);
        }

        @Test
        void shouldRunSuccessfullyWithDoubleFirstAndLastName() {
            driver.get("http://localhost:7777/");
            driver.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("Анна-Мария Иванова-Васильева");
            driver.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("+70000000000");
            driver.findElement(By.cssSelector("[data-test-id=agreement]")).click();
            driver.findElement(By.className("button_theme_alfa-on-white")).click();
            String expected = "  Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.";
            String actual = driver.findElement(By.cssSelector("[data-test-id=order-success]")).getText();
            Assertions.assertEquals(expected, actual);
        }

        @Test
        void shouldRunSuccessfullyWithPlusAndZerosAsPhone() {
            driver.get("http://localhost:7777/");
            driver.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("Мария Васильева");
            driver.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("+00000000000");
            driver.findElement(By.cssSelector("[data-test-id=agreement]")).click();
            driver.findElement(By.className("button_theme_alfa-on-white")).click();
            String expected = "  Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.";
            String actual = driver.findElement(By.cssSelector("[data-test-id=order-success]")).getText();
            Assertions.assertEquals(expected, actual);
        }

        @Test
        void shouldRunSuccessfullyWithNameInCaps() {
            driver.get("http://localhost:7777/");
            driver.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("МАРИЯ ВАСИЛЬЕВА");
            driver.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("+39001231122");
            driver.findElement(By.cssSelector("[data-test-id=agreement]")).click();
            driver.findElement(By.className("button_theme_alfa-on-white")).click();
            String expected = "  Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.";
            String actual = driver.findElement(By.cssSelector("[data-test-id=order-success]")).getText();
            Assertions.assertEquals(expected, actual);
        }

    }

