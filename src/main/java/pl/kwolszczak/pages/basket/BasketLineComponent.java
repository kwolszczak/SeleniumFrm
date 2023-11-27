package pl.kwolszczak.pages.basket;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pl.kwolszczak.models.BasketLine;
import pl.kwolszczak.models.Product;
import pl.kwolszczak.pages.support.SupportPage;

public class BasketLineComponent extends SupportPage {

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

    private Double getPrice() {
        return Double.parseDouble(price.getText().replaceAll("[$]","").trim());
    }

    private int getQuantity() {
        return Integer.parseInt(quantityInp.getAttribute("value"));
    }

    private String getName() {
        return name.getText();
    }

    public BasketLine toBasketLine() {
        var product = new Product(getName(), getPrice());
        return new BasketLine(product, getQuantity());
    }
}
