package pl.kwolszczak.pages.basket;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pl.kwolszczak.models.Basket;
import pl.kwolszczak.models.BasketLine;
import pl.kwolszczak.pages.common.CommonPage;

import java.util.LinkedList;
import java.util.List;

public class CartPage extends CommonPage {

    @FindBy(css = "ul.cart-items li")
    private List<WebElement> basket;

    private List<BasketLineComponent> basketLineComponents;

    public CartPage(WebDriver driver) {
        super(driver);
        setBasketLines();
    }

    private void setBasketLines() {
        basketLineComponents = new LinkedList<>();
        basketLineComponents = basket.stream().map(we -> new BasketLineComponent(driver, we)).toList();
    }

    public List<BasketLine> getBasketLinesModel() {
        return basketLineComponents.stream().map(BasketLineComponent::toBasketLine).toList();
    }

    public Basket getBasketModel() {
        var basket = new Basket();
        for (var bl: getBasketLinesModel()) {
            basket.addBasketLine(bl);
        }
        return basket;
    }


/*    // @Override
    public BasketLine toBasket() {
        var product = new Product(getName(), getPrice());
        return new BasketLine(product, getQuantity());
    }

    // @Override
    public BasketLine toBasketLine() {
        var product = new Product(getName(), getPrice());
        return new BasketLine(product, getQuantity());
    }*/
}
