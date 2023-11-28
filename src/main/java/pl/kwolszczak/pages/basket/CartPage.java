package pl.kwolszczak.pages.basket;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pl.kwolszczak.models.Basket;
import pl.kwolszczak.models.BasketLine;
import pl.kwolszczak.pages.common.CommonPage;
import pl.kwolszczak.pages.support.BasketQueryable;

import java.util.List;

public class CartPage extends CommonPage implements BasketQueryable {

    @FindBy(css = "ul.cart-items li")
    private List<WebElement> basket;

    private List<BasketLineComponent> basketLineComponents;

    public CartPage(WebDriver driver) {
        super(driver);
        //1
        //setBasketLines();

        //2
        basketLineComponents = setComponents(BasketLineComponent.class, basket);
    }
//    //1
//    private void setBasketLines() {
//        basketLineComponents = new LinkedList<>();
//        basketLineComponents = basket.stream().map(we -> new BasketLineComponent(driver, we)).toList();
//    }

    public List<BasketLine> getBasketLinesModel() {
        return basketLineComponents.stream().map(BasketLineComponent::toBasketLineModel).toList();
    }

    @Override
    public Basket toBasketModel() {
        var newBasket = new Basket();
        for (var basketLine : getBasketLinesModel()) {
            newBasket.addBasketLine(basketLine);
        }
        return newBasket;
    }
}