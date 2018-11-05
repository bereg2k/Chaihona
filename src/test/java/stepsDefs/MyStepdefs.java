package stepsDefs;

import cucumber.api.java.ru.И;
import cucumber.api.java.ru.Когда;
import cucumber.api.java.ru.Тогда;
import org.openqa.selenium.WebDriver;
import pages.CartPage;
import pages.MainPage;
import pages.OrderPage;

/**
 * Created by student on 05.11.2018.
 */
public class MyStepdefs extends BaseDefs {
    WebDriver driver;
    MainPage mainPage = new MainPage(driver);
    OrderPage orderPage = new OrderPage(driver);
    CartPage cartPage;

    @Когда("^открыто меню Доставки$")
    public void openMainMenu() throws Throwable {
        mainPage.clickMainMenu();
    }

    @И("^выбран раздел Меню Доставки \"([^\"]*)\"$")
    public void chooseMenuItem(String text) throws Throwable {
        mainPage.chooseDeliveryItem(text);
    }

    @И("^заказан товар \"([^\"]*)\"$")
    public void orderItem(String text) throws Throwable {
        orderPage.addToCart(text);
    }

    @И("^открыта Корзина$")
    public void openCart() throws Throwable {
        cartPage = new CartPage(driver, orderPage.getUserOrderList());
    }

    @Тогда("^проверить в Корзине заказанные товары$")
    public void checkCartItems() throws Throwable {
        cartPage.checkItemsInCart();
    }

    @И("^проверить общую сумму в Корзине$")
    public void checkCartTotalSum() throws Throwable {
        cartPage.checkCartTotalSum();
    }

    @Когда("^очищена Корзина$")
    public void clearCart() throws Throwable {
        cartPage.clearCart();
    }

    @Тогда("^проверить что Корзина пуста$")
    public void checkCartEmptiness() throws Throwable {
        cartPage.isEmpty();
    }
}
