package pl.kwolszczak.pages.basket;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pl.kwolszczak.pages.common.CommonPage;
import pl.kwolszczak.pages.product.ProductPage;

public class ProductPopUpPage extends CommonPage {

    @FindBy(css = "button.btn.btn-secondary")
    private WebElement continueShoppingBtn;

    public ProductPopUpPage(WebDriver driver) {
        super(driver);
    }

    public ProductPage continueShopping() {
        clickIt(continueShoppingBtn);
        return new ProductPage(driver);
    }
}
