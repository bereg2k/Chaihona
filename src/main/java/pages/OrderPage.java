package pages;


import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;

public class OrderPage extends BasePage {

    public OrderPage(WebDriver driver) {
        super(driver);
    }

    @Step("добавляем товар {0} в Корзину")
    public void addToCart(String text) {
        click("//h3/a/span[contains(text(),'" + text + "')]/../../../button");

        getLocker().saveItemPrice(findByXpath("//h3/a/span[contains(text(),'" + text + "')]"),
                findByXpath("//h3/a/span[contains(text(),'" + text + "')]/../../../div/span/span"));
    }
}
