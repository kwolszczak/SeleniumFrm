package pl.kwolszczak.pages.common;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pl.kwolszczak.models.Product;
import pl.kwolszczak.pages.product.ProductPage;
import pl.kwolszczak.pages.support.SupportPage;

public class ThumbnailComponent extends SupportPage {

    @FindBy(css = "div.product-description a")
    private WebElement name;

    public ThumbnailComponent(WebDriver driver, WebElement parent) {
        super(driver, parent);
    }

    public String getName() {
        return name.getText();
    }

    public void openProductDetails() {
        clickIt(name);
    }


    public Product getProduct() {
        var product = new Product();
        product.setName(getName());
        return product;
    }
}
