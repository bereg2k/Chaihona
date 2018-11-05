package pages;

import org.openqa.selenium.WebDriver;

public class OrderPage extends BasePage {

    public OrderPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isPageLoaded() {
        return false;
    }

    public void addToCart(String text) {
        click("//h3/a/span[contains(text(),'" + text + "')]/../../../button");
        saveItemPrice(findByXpath("//h3/a/span[contains(text(),'" + text + "')]"),
                findByXpath("//h3/a/span[contains(text(),'" + text + "')]/../../../div/span/span"));
    }
}
