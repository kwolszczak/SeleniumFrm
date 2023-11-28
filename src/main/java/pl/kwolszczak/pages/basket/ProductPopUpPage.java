package pl.kwolszczak.pages.basket;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pl.kwolszczak.models.Basket;
import pl.kwolszczak.models.BasketLine;
import pl.kwolszczak.pages.common.CommonPage;
import pl.kwolszczak.pages.product.ProductPage;
import pl.kwolszczak.pages.support.BasketLineQueryable;
import pl.kwolszczak.pages.support.BasketQueryable;

import java.util.Iterator;
import java.util.Set;

public class ProductPopUpPage extends CommonPage implements BasketLineQueryable, BasketQueryable {

    @FindBy(css = "button.btn.btn-secondary")
    private WebElement continueShoppingBtn;

    @FindBy(css = "h6.h6.product-name")
    private WebElement name;

    @FindBy(css = "span.product-quantity")
    private WebElement quantity;

    @FindBy(css = "p.product-price")
    private WebElement price;

    public ProductPopUpPage(WebDriver driver) {
        super(driver);
    }

    public ProductPage continueShopping() {
        clickIt(continueShoppingBtn);
        return new ProductPage(driver);
    }

    @Override
    public BasketLine toBasketLineModel() {
       wait.until(ExpectedConditions.elementToBeClickable(price));
        return toBasketLineModel(name, quantity, price);
    }

    @Override
    public Basket toBasketModel() {
        Basket newBasket = new Basket();
        newBasket.addBasketLine(toBasketLineModel());
        return newBasket;
    }
}
