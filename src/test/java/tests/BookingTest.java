package tests;

import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.Home;
import pages.Results;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleContains;
import static org.testng.Assert.assertTrue;

public class BookingTest {

    private WebDriver driver;
    private WebDriverWait wait;
    private Home home;
    private Results results;
    private final String propertyName = "Hotel Louis II";

    @BeforeClass
    public void setUp() {
        driver = new EdgeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        driver.manage().window().maximize();
        home = new Home(driver, wait);
    }

    @Test(priority = 1)
    public void gotoPage() {
        driver.get("https://www.booking.com");
    }

    @Test(priority = 2)
    public void enterDestination() {
        home.dismissModal();
        home.enterDestination("Paris");
    }

    @Test(priority = 3)
    public void selectDates() {
        home.selectDates(2023, 4, 4, 24, 30);
    }

    @Test(priority = 4)
    public void addChild() {
        home.addChild("6 years old");
    }

    @Test(priority = 5)
    public void submit() {
        results = home.submit();
    }

    @Test(priority = 6)
    public void selectProperty() {
        results.selectProperty(propertyName);
    }

    @Test(priority = 7)
    public void verifyProperty() {
        assertTrue(wait.until(titleContains(propertyName)));
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}