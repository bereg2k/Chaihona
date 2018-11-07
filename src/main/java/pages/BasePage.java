package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import other.Locker;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 * Created by user on 03.11.2018.
 */
abstract class BasePage {

    private WebDriver driver;
    private static Locker locker = Locker.getInstance();

    Locker getLocker() {
        return locker;
    }

    BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    void click(WebElement element) {
        scroll(element);
        new WebDriverWait(driver, 5).until(ExpectedConditions.elementToBeClickable(element));
        element.click();
    }

    private void scroll(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
    }

    private void waitForVisible(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    private void waitForVisible(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    private void selectByText(WebElement element, String text) {
        new Select(element).selectByVisibleText(text);
    }

    WebElement findByXpath(String xpath) {
        return findByLocator(By.xpath(xpath));
    }

    WebElement findByLocator(By locator) {
        return driver.findElement(locator);
    }

    void click(String xpath) {
        waitForVisible(By.xpath(xpath));
        findByXpath(xpath).click();
    }

    void checkElementText(WebElement element, String expectedText) {
        waitForVisible(element);
        assertEquals("Значения текста не соотвествует ожидаемому",
                expectedText, element.getText());
    }

    void fillElement(By locator, String text) {
        WebElement element = findByLocator(locator);
        if (element.getTagName().equalsIgnoreCase("select")) {
            selectByText(element, text);
        } else {
            element.sendKeys(text);
        }
    }

    void fillElement(String xpath, String text) {
        fillElement(By.xpath(xpath), text);
    }

    boolean isElementPresented(String xpath) {
        return driver.findElements(By.xpath(xpath)).size() != 0;
    }
}
