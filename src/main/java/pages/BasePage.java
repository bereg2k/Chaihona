package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 * Created by user on 03.11.2018.
 */
public abstract class BasePage {

    public abstract boolean isPageLoaded();

    WebDriver driver;

    public Map<String, String> getUserOrderList() {
        return userOrderList;
    }

    Map<String, String> userOrderList = new HashMap<>();

    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void click(WebElement element) {
        new WebDriverWait(driver, 5).until(ExpectedConditions.elementToBeClickable(element));
        element.click();
    }

    public void scroll(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }

    void waitForVisible(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    void selectByText(WebElement element, String text) {
        new Select(element).selectByVisibleText(text);
    }

    WebElement findByXpath(String xpath) {
        return findByLocator(By.xpath(xpath));
    }

    WebElement findByLocator(By locator) {
        return driver.findElement(locator);
    }

    void click(By locator) {
        waitForVisible(locator);
        findByLocator(locator).click();
    }

    void click(String xpath) {
        waitForVisible(By.xpath(xpath));
        findByXpath(xpath).click();
    }

    void checkElementText(WebElement element, String expectedText) {
        assertEquals("Значения текст не соотвествует ожидаемому",
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

    void saveItemPrice(WebElement name, WebElement price) {
        userOrderList.put(name.getText(), price.getText().substring(0, 3));
    }

    void openWebPage(String url){
        driver.get(url);
    }
}
