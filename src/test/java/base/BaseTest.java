package base;

import io.qameta.allure.Allure;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import utils.AllureAttachments;
import utils.AllureEnvironment;

import java.time.Duration;

public abstract class BaseTest {

    protected WebDriver driver;
    protected WebDriverWait wait;

    protected final String BASE_URL =
            "https://demoqa.com";

    @BeforeSuite(alwaysRun = true)
    public void createAllureEnvironment() {
        AllureEnvironment.write();
    }

    @Parameters({"browser", "headless"})
    @BeforeMethod(alwaysRun = true)
    public void setUp(
            @Optional("chrome") String browser,
            @Optional("false") String headless
    ) {

        driver = createDriver(
                browser,
                Boolean.parseBoolean(headless)
        );

        driver.manage().window().maximize();

        driver.manage().timeouts()
                .pageLoadTimeout(Duration.ofSeconds(30));

        wait = new WebDriverWait(
                driver,
                Duration.ofSeconds(10)
        );
    }

    private WebDriver createDriver(
            String browser,
            boolean headless
    ) {

        switch (browser.toLowerCase()) {

            case "firefox" -> {

                FirefoxOptions options =
                        new FirefoxOptions();

                if (headless) {
                    options.addArguments("-headless");
                }

                return new FirefoxDriver(options);
            }

            case "edge" -> {

                EdgeOptions options =
                        new EdgeOptions();

                if (headless) {
                    options.addArguments("--headless=new");
                }

                return new EdgeDriver(options);
            }

            default -> {

                ChromeOptions options =
                        new ChromeOptions();

                if (headless) {
                    options.addArguments("--headless=new");
                }

                return new ChromeDriver(options);
            }
        }
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(ITestResult result) {

        if (driver != null && !result.isSuccess()) {

            AllureAttachments.screenshot(
                    driver,
                    "Failure screenshot"
            );

            AllureAttachments.pageSource(
                    driver,
                    "Page source on failure"
            );

            Allure.addAttachment(
                    "Failed test",
                    result.getName()
            );
        }

        if (driver != null) {
            driver.quit();
        }
    }
}