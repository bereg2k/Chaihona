package pageObjectTests;

import org.junit.Test;
import pages.CartPage;
import pages.MainPage;
import pages.OrderPage;

/**
 * Created by user on 03.11.2018.
 */
public class ChaiTest extends BaseTest{

    @Test
    public void myTest() {
        MainPage mainPage = new MainPage(driver);
        OrderPage orderPage = new OrderPage(driver);
        CartPage cart = new CartPage(driver);

        mainPage.clickMainMenu();
        mainPage.chooseDeliveryItem("Бургеры и Шаурма");

        orderPage.addToCart("Шаурма с курицей");
        orderPage.addToCart("Шаурма с телятиной");

        mainPage.clickMainMenu();
        mainPage.chooseDeliveryItem("Плов");

        orderPage.addToCart("Плов Чайханский");

        mainPage.openCart();

        cart.checkItemsInCart();
        cart.checkCartTotalSum();
        cart.clearCart();
        cart.isCartEmpty();
    }
}
