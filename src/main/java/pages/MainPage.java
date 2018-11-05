package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by user on 03.11.2018.
 */
public class MainPage extends BasePage {

    @FindBy(xpath = "//div[@id='pw-confirm__close']")
    WebElement subscriptionNoButton;

    @FindBy(xpath = "//ul[@id='desktopMenuMain']//a[@href='/catalog' and contains(@class,'nav-link-1')]")
    WebElement mainMenuButton;

    @FindBy(xpath = "//ul[@id='desktopMenuMain']//div[@class='main-menu__wrap']//ul[contains(@class,'main-menu__list--double-column')]")
    WebElement deliveryItems;

    @FindBy(xpath = "//a[@title='Перейти в корзину']")
    WebElement cart;

    public MainPage(WebDriver driver) {
        super(driver);

        if (subscriptionNoButton.isDisplayed()) click(subscriptionNoButton);
    }

    @Step("Выбираем пункт меню {0} в главном Меню Доставки")
    public void chooseDeliveryItem(String text) {
        deliveryItems.findElement(By.xpath(".//li/a[contains(text(),'" + text + "')]")).click();
    }

    @Step("Раскрываем меню доставки")
    public void clickMainMenu() {
        click(mainMenuButton);
    }

    @Step("Открываем Корзину")
    public void openCart() {
        click(cart);
    }

    public boolean isPageLoaded() {
        return false;
    }
}
