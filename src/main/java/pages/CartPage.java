package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.Map;

import static org.junit.Assert.assertEquals;

public class CartPage extends BasePage {

    private int totalSum = 0;

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public void checkItemsInCart() {
        for (Object o : getLocker().getUserOrderList().entrySet()) {
            Map.Entry pair = (Map.Entry) o;
            assertEquals(pair.getKey().toString(), findByXpath("//tbody/tr/td[@class='product-name']/a[contains(text(),'" + pair.getKey() + "')]").getText());
            totalSum += Integer.valueOf(pair.getValue().toString());
        }
    }

    public void checkCartTotalSum() {
        checkElementText(findByXpath("//span[@id='cartPrice']"), String.valueOf(totalSum));
    }

    public void clearCart() {
        for (Object o : getLocker().getUserOrderList().entrySet()) {
            Map.Entry pair = (Map.Entry) o;
            click("//tbody/tr/td[@class='product-name']/a[contains(text(),'" + pair.getKey().toString() + "')]/../../td/a/span");
        }
    }

    public void isCartEmpty() {
        checkElementText(findByXpath("//h2"), "Ваша корзина пуста");
    }
}