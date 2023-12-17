package pl.kwolszczak.pages.support;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pl.kwolszczak.models.BasketLine;
import pl.kwolszczak.models.Product;

public class Component extends SupportPage{

    protected Component(WebDriver driver, WebElement parent) {
        super(driver, parent);
    }

    protected BasketLine toBasketLineModel(WebElement name, WebElement quantity, WebElement price) {
        var product = new Product(name.getText(), parsePrice(price));
        return new BasketLine(product, getQuantity(quantity));
    }


    private int getQuantity(WebElement we) {
        if (we.getAttribute("value") == null) {
            return parseDigits(we);
        }
        return Integer.parseInt(we.getAttribute("value"));
    }


}
