package pl.kwolszczak.pages.common;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pl.kwolszczak.pages.support.Component;

public class HeaderComponent extends Component {

    @FindBy(css = "div#search_widget input[type='text']")
    private WebElement searchInp;

    @FindBy(css = "div#search_widget button")
    private WebElement searchBtn;

    @FindBy(css = "img.logo.img-responsive")
    private WebElement homeBtn;

    @FindBy(css = "span.cart-products-count")
    private WebElement cartBtn;


    public HeaderComponent(WebDriver driver, WebElement parent) {
        super(driver, parent);
    }

    protected void search(String product, boolean click) {
        if (click) {
            fillIn(searchInp, product);
            clickIt(searchBtn);
        } else {
            fillIn(searchInp, product);
        }
    }

    protected void goHomePage() {
        clickIt(homeBtn);
    }

    protected int getNumberOfProductsInBasket() {
        return parseDigits(cartBtn);
    }
}
