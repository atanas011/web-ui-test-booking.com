package tests;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.openqa.selenium.support.ui.ExpectedConditions.numberOfWindowsToBe;
import static org.openqa.selenium.support.ui.ExpectedConditions.titleContains;
import static org.testng.Assert.assertTrue;

public class BookingTest {

    private WebDriver driver;

    @BeforeClass
    public void setUp() {
        driver = new EdgeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.booking.com");
    }

    @Test
    public void testBooking() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));

        // handle occasional modal
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(By
                    .cssSelector("[aria-label='Dismiss sign-in info.']"))).click();
        } catch (WebDriverException e) {
            System.out.println(e.getMessage());
        }

        driver.findElement(By.id(":Ra9:")).sendKeys("Paris");

        driver.findElement(By.cssSelector("[data-testid='date-display-field-start']")).click();
        LocalDate checkIn = LocalDate.of(2023, 3, 19);
        LocalDate checkOut = LocalDate.of(2023, 3, 25);
        selectDate(driver, checkIn);
        selectDate(driver, checkOut);

        driver.findElement(By.cssSelector("[data-testid='searchbox-form-button-icon']")).click();
        driver.findElement(By.cssSelector("[id='group_children']~div>[class*='d64a4ea64d']")).click();
        Select dropDownAge = new Select(driver.findElement(By.name("age")));
        dropDownAge.selectByVisibleText("6 years old");

        driver.findElement(By.cssSelector("[type='submit']")).click();

        // store tab's ID
        String originalWin = driver.getWindowHandle();

        // open in new tab
        driver.findElement(By.xpath("//div[text()='Novotel Suites Paris Montreuil Vincennes']")).click();

        wait.until(numberOfWindowsToBe(2));

        // switch to new tab
        for (String handle : driver.getWindowHandles()) {
            if (!originalWin.contentEquals(handle)) {
                driver.switchTo().window(handle);
                break;
            }
        }

        assertTrue(wait.until(titleContains("Novotel Suites Paris Montreuil Vincennes")));
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }

    private static void selectDate(WebDriver driver, LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;
        driver.findElement(By.xpath(String.format("//span[@data-date='%s']",
                formatter.format(date)))).click();
    }
}