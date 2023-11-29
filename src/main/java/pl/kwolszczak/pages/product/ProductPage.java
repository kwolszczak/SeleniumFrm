package pl.kwolszczak.pages.product;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pl.kwolszczak.models.Basket;
import pl.kwolszczak.models.BasketLine;
import pl.kwolszczak.pages.basket.ProductPopUpPage;
import pl.kwolszczak.pages.common.CommonPage;
import pl.kwolszczak.pages.support.BasketLineQueryable;

public class ProductPage extends CommonPage implements BasketLineQueryable {

    @FindBy(css = "h1[itemprop='name']")
    private WebElement name;
    @FindBy(css = "#quantity_wanted")
    private WebElement quantityInp;
    @FindBy(css = "span[itemprop='price']")
    private WebElement price;
    @FindBy(css = "div.add button")
    private WebElement addToCartBtn;

    public ProductPage(WebDriver driver) {
        super(driver);
    }

    public ProductPopUpPage addToBasket(int quantity, Basket basket) {
        setQuantity(quantity);
        basket.addBasketLine(toBasketLineModel());
        clickIt(addToCartBtn);

        return new ProductPopUpPage(driver);
    }

    public ProductPage setQuantity(int quantity) {
        fillIn(this.quantityInp, String.valueOf(quantity));
        return this;
    }

    @Override
    public BasketLine toBasketLineModel() {
        return toBasketLineModel(name, quantityInp, price);
    }

}
