package pl.kwolszczak.pages.basket;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pl.kwolszczak.models.Basket;
import pl.kwolszczak.models.BasketLine;
import pl.kwolszczak.pages.checkout.CheckoutPage;
import pl.kwolszczak.pages.common.CommonPage;
import pl.kwolszczak.pages.support.BasketQueryable;

import java.util.List;

public class CartPage extends CommonPage implements BasketQueryable {

    @FindBy(css = "ul.cart-items li")
    private List<WebElement> basket;

    @FindBy(css = "div#cart-subtotal-products span.value")
    private WebElement totalPrice;

    @FindBy(css = "span.no-items")
    private WebElement emptyBasketInfo;

    @FindBy(css = "div.card.cart-summary a.btn.btn-primary")
    private WebElement proceedToCheckoutBtn;


    private List<BasketLineComponent> basketLineComponents;

    public CartPage(WebDriver driver) {
        super(driver);
        basketLineComponents = setComponents(BasketLineComponent.class, basket);
    }


    public String getInfoAboutEmptyBasket() {
        return emptyBasketInfo.getText().trim();
    }

    public double getTotalPrice() {
        return getPrice(totalPrice);
    }

    public CheckoutPage proceedToCheckout() {
        clickIt(proceedToCheckoutBtn);
        return new CheckoutPage(driver);
    }

    public CartPage removeProduct(String name, Basket basket) {

        int basketSize = basketLineComponents.size();
        for (var basketLine : basketLineComponents) {

            var basketLineModel = basketLine.toBasketLineModel();
            if (basketLineModel.getProduct().getName().equals(name)) {

                basket.removeBasketLine(basketLineModel);
                basketLine.removeProduct();

                wait.until(wd -> {
                    this.basketLineComponents = this.basket.stream().map(we -> new BasketLineComponent(driver, we)).toList();
                    return basketLineComponents.size() < basketSize;
                });
                break;
            }
        }
        return this;
    }

    public List<BasketLine> getBasketLines() {
        return basketLineComponents.stream().map(BasketLineComponent::toBasketLineModel).toList();
    }

    @Override
    public Basket toBasketModel() {
        var newBasket = new Basket();
        for (var basketLine : getBasketLines()) {
            newBasket.addBasketLine(basketLine);
        }
        return newBasket;
    }
}
