package pages;


import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;

import java.util.Map;

import static org.junit.Assert.assertEquals;

public class CartPage extends BasePage {

    private int totalSum = 0;

    public CartPage(WebDriver driver) {
        super(driver);
    }

    @Step("проверяем наличие заказанных товаров в Корзине")
    public void checkItemsInCart() {
        for (Object o : getLocker().getUserOrderList().entrySet()) {
            Map.Entry pair = (Map.Entry) o;
            assertEquals(pair.getKey().toString(), findByXpath("//tbody/tr/td[@class='product-name']/a[contains(text(),'" + pair.getKey() + "')]").getText());
            totalSum += Integer.valueOf(pair.getValue().toString());
        }
    }

    @Step("проверяем итоговую сумму заказа в Корзине")
    public void checkCartTotalSum() {
        checkElementText(findByXpath("//span[@id='cartPrice']"), String.valueOf(totalSum));
    }

    @Step("очищаем Корзину")
    public void clearCart() {
        for (Object o : getLocker().getUserOrderList().entrySet()) {
            Map.Entry pair = (Map.Entry) o;
            click("//tbody/tr/td[@class='product-name']/a[contains(text(),'" + pair.getKey().toString() + "')]/../../td/a/span");
        }
    }

    @Step("проверяем пуста ли Корзина")
    public void isCartEmpty() {
        checkElementText(findByXpath("//h2"), "Ваша корзина пуста");
    }
}