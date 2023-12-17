package pl.kwolszczak.pages.basket;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pl.kwolszczak.models.Basket;
import pl.kwolszczak.models.BasketLine;
import pl.kwolszczak.pages.checkout.CheckoutPage;
import pl.kwolszczak.pages.common.CommonPage;
import pl.kwolszczak.pages.support.BasketQueryable;

import java.util.List;
import java.util.stream.Collectors;

public class CartPage extends CommonPage implements BasketQueryable {

    @FindBy(css = "ul.cart-items li")
    private List<WebElement> basketWE;

    @FindBy(css = "div#cart-subtotal-products span.value")
    private WebElement totalPrice;

    @FindBy(css = "span.no-items")
    private WebElement emptyBasketInfo;

    @FindBy(css = "div.card.cart-summary a.btn.btn-primary")
    private WebElement proceedToCheckoutBtn;

    private List<BasketLineComponent> basketLineComponents;
    private By basketLocator =  By.cssSelector("ul.cart-items li");

    public CartPage(WebDriver driver) {
        super(driver);
        basketLineComponents = setComponents(BasketLineComponent.class, basketWE);
    }


    public String getInfoAboutEmptyBasket() {
        return emptyBasketInfo.getText().trim();
    }

    public double getTotalPrice() {
        return parsePrice(totalPrice);
    }

    public CheckoutPage proceedToCheckout() {
        click(proceedToCheckoutBtn);
        return new CheckoutPage(driver);
    }


    public CartPage removeProduct(String productName, Basket basket) {
        BasketLineComponent basketLineToRemove = findBasketLineByName(productName);

        if (basketLineToRemove != null) {
            removeBasketLine(basket, basketLineToRemove);
        }

        return this;
    }

    private BasketLineComponent findBasketLineByName(String productName) {
        return basketLineComponents.stream()
                .filter(basketLine -> basketLine.toBasketLineModel().getProduct().getName().equals(productName))
                .findFirst()
                .orElse(null);
    }

    private void removeBasketLine(Basket basket, BasketLineComponent basketLine) {
        int basketSize = basketLineComponents.size();
        var basketLineModel = basketLine.toBasketLineModel();

        basket.removeBasketLine(basketLineModel);
        basketLine.removeProduct();

        wait.until(ExpectedConditions.numberOfElementsToBe(basketLocator, basketSize -1));
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
