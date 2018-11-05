package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.Map;

import static org.junit.Assert.assertEquals;

public class CartPage extends BasePage {

    private int totalSum = 0;

    public CartPage(WebDriver driver, BasePage page) {
        super(driver);
        this.userOrderList = page.userOrderList;
    }

    @Override
    public boolean isPageLoaded() {
        return false;
    }

    public void checkItemsInCart() {
        for (Object o : userOrderList.entrySet()) {
            Map.Entry pair = (Map.Entry) o;
            assertEquals(pair.getKey().toString(), findByXpath("//tbody/tr/td[@class='product-name']/a[contains(text(),'" + pair.getKey() + "')]").getText());
            totalSum += Integer.valueOf(pair.getValue().toString());
        }
    }

    public void checkCartTotalSum() {
        assertEquals(String.valueOf(totalSum), findByXpath("//span[@id='cartPrice']").getText());
    }

    public void clearCart() {
        for (Object o : userOrderList.entrySet()) {
            Map.Entry pair = (Map.Entry) o;
            click("//tbody/tr/td[@class='product-name']/a[contains(text(),'" + pair.getKey().toString() + "')]/../../td/a/span");
        }
    }

    public void isEmpty() {
        waitForVisible(By.xpath("//h2"));
        assertEquals("Ваша корзина пуста", findByXpath("//h2").getText());
    }
}