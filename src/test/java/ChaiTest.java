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

        mainPage.clickMainMenu();
        mainPage.chooseDeliveryItem("Бургеры и Шаурма");

        OrderPage orderPage = new OrderPage(driver);
        orderPage.addToCart("Шаурма с курицей");
        orderPage.addToCart("Шаурма с телятиной");

        mainPage.clickMainMenu();
        mainPage.chooseDeliveryItem("Плов");

        orderPage.addToCart("Плов Чайханский");

        mainPage.openCart();

        CartPage cart = new CartPage(driver);
        cart.checkItemsInCart();
        cart.checkCartTotalSum();
        cart.clearCart();
        cart.isCartEmpty();
    }
}
