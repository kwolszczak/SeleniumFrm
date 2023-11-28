package pl.kwolszczak.pages.basket;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pl.kwolszczak.models.BasketLine;
import pl.kwolszczak.pages.support.BasketLineQueryable;
import pl.kwolszczak.pages.support.Component;

public class BasketLineComponent extends Component implements BasketLineQueryable {

    @FindBy(css = "div.product-line-info a")
    private WebElement name;

    @FindBy(css = "div.current-price span.price")
    private WebElement price;

    @FindBy(css = "div.input-group.bootstrap-touchspin input")
    private WebElement quantityInp;

    @FindBy(css = "span.product-price strong")
    private WebElement totalPrice;

    public BasketLineComponent(WebDriver driver, WebElement parent) {
        super(driver, parent);
    }

    @Override
    public BasketLine toBasketLineModel() {
        return toBasketLineModel(name, quantityInp, price);
    }

}
