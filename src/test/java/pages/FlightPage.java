package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import java.time.Duration;

public class FlightPage {

    WebDriver driver;
    WebDriverWait wait;

    public FlightPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    // Locators
    By from = By.xpath("//input[@placeholder='Where from?']");
    By to = By.xpath("//input[@placeholder='Where to?']");
    By search = By.xpath("//button[contains(text(),'Search')]");

    // 🔥 POPUP HANDLER
    public void handlePopups() {

        try {
            driver.switchTo().activeElement().sendKeys(Keys.ESCAPE);
        } catch (Exception e) {}

        // LOGIN POPUP
        try {
            WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(5));

            WebElement closeBtn = shortWait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[@role='dialog']//button | //button[@aria-label='Close']")));

            closeBtn.click();
            System.out.println("Login popup closed");

        } catch (Exception e) {
            System.out.println("No login popup");
        }

        // COOKIE POPUP
        try {
            WebElement cookie = driver.findElement(
                By.xpath("//button[contains(text(),'Accept')] | //button[contains(text(),'OK')]"));

            cookie.click();
            System.out.println("Cookie popup closed");

        } catch (Exception e) {
            System.out.println("No cookie popup");
        }
    }

    public void enterFrom(String city) {
        WebElement e = wait.until(ExpectedConditions.elementToBeClickable(from));
        e.clear();
        e.sendKeys(city);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//ul/li"))).click();
    }

    public void enterTo(String city) {
        WebElement e = wait.until(ExpectedConditions.elementToBeClickable(to));
        e.clear();
        e.sendKeys(city);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//ul/li"))).click();
    }

    public void selectDate(int days) {
        driver.findElement(By.xpath("//div[contains(@class,'date')]")).click();

        wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("(//div[@aria-disabled='false'])[" + days + "]"))).click();
    }

    public void search() {
        wait.until(ExpectedConditions.elementToBeClickable(search)).click();
    }

    public boolean verify() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.xpath("//div[contains(@class,'flightList')]"))).isDisplayed();
    }
}