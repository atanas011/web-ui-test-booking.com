package pages;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Home {

    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By closeModalBtn = By.cssSelector("[aria-label='Dismiss sign-in info.']");
    private final By whereTo = By.id(":Ra9:");
    private final By datePicker = By.cssSelector("[data-testid='date-display-field-start']");
    private static final String dataDate = "//span[@data-date";
    private final By numsPicker = By.cssSelector("[data-testid='searchbox-form-button-icon']");
    private final By addChildBtn = By.cssSelector("[id='group_children']~div>[class*='d64a4ea64d']");
    private final By ageDropdown = By.name("age");
    private final By searchBtn = By.cssSelector("[type='submit']");

    public Home(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public void dismissModal() {
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(closeModalBtn)).click();
        } catch (WebDriverException e) {
            System.out.println(e.getMessage());
        }
    }

    public void enterDestination(String destination) {
        driver.findElement(whereTo).sendKeys(destination);
    }

    public void selectDates(int year, int monthIn, int monthOut, int dayIn, int dayOut) {
        driver.findElement(datePicker).click();
        selectDate(LocalDate.of(year, monthIn, dayIn));
        selectDate(LocalDate.of(year, monthOut, dayOut));
    }

    public void addChild(String age) {
        driver.findElement(numsPicker).click();
        driver.findElement(addChildBtn).click();
        new Select(driver.findElement(ageDropdown)).selectByVisibleText(age);
    }

    public Results submit() {
        driver.findElement(searchBtn).click();
        return new Results(driver, wait);
    }

    private void selectDate(LocalDate date) {
        driver.findElement(By.xpath(String.format(dataDate + "='%s']",
                DateTimeFormatter.ISO_LOCAL_DATE.format(date)))).click();
    }
}