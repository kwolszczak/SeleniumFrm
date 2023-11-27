package pl.kwolszczak.pages.product;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pl.kwolszczak.models.Basket;
import pl.kwolszczak.models.BasketLine;
import pl.kwolszczak.models.Product;
import pl.kwolszczak.pages.common.CommonPage;

public class ProductPage extends CommonPage {

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

    public ProductPage addToBasket(int quantity, Basket basket) {
        setQuantityInp(quantity);
        basket.addBasketLine(toBasketLine());
        clickIt(addToCartBtn);

        return this;
    }

    private void setQuantityInp(int quantityInp) {
       fill(this.quantityInp, String.valueOf(quantityInp));
    }

    private Double getPrice() {
        return Double.parseDouble(price.getAttribute("content"));
    }

    private int getQuantity() {
        return Integer.parseInt(quantityInp.getAttribute("value"));
    }

    private String getName() {
        return name.getText();
    }

   // @Override
    public BasketLine toBasketLine() {
        var product = new Product(getName(), getPrice());
        return new BasketLine(product, getQuantity());
    }
}
