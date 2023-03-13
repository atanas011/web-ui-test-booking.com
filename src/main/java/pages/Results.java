package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.openqa.selenium.support.ui.ExpectedConditions.numberOfWindowsToBe;

public class Results {

    private final WebDriver driver;
    private final WebDriverWait wait;

    private static final String propertyPath = "//div[text()='";

    public Results(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public void selectProperty(String propertyName) {
        var originalWin = driver.getWindowHandle();
        driver.findElement(By.xpath(propertyPath + propertyName + "']")).click();

        wait.until(numberOfWindowsToBe(2));
        for (String handle : driver.getWindowHandles()) {
            if (!originalWin.contentEquals(handle)) {
                driver.switchTo().window(handle);
                break;
            }
        }
    }
}