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
public class MyStepdefs {
    private WebDriver driver = Hooks.driver;
    private MainPage mainPage = new MainPage(driver);
    private OrderPage orderPage = new OrderPage(driver);
    private CartPage cartPage = new CartPage(driver);

    @Когда("^открыто меню Доставки$")
    public void openMainMenu() {
        mainPage.clickMainMenu();
    }

    @И("^выбран раздел Меню Доставки \"([^\"]*)\"$")
    public void chooseMenuItem(String text) {
        mainPage.chooseDeliveryItem(text);
    }

    @И("^заказан товар \"([^\"]*)\"$")
    public void orderItem(String text) {
        orderPage.addToCart(text);
    }

    @И("^открыта Корзина$")
    public void openCart() {
        mainPage.openCart();
    }

    @Тогда("^проверить в Корзине заказанные товары$")
    public void checkCartItems() {
        cartPage.checkItemsInCart();
    }

    @И("^проверить общую сумму в Корзине$")
    public void checkCartTotalSum() {
        cartPage.checkCartTotalSum();
    }

    @Когда("^очищена Корзина$")
    public void clearCart() {
        cartPage.clearCart();
    }

    @Тогда("^проверить что Корзина пуста$")
    public void checkCartEmptiness() {
        cartPage.isCartEmpty();
    }
}
