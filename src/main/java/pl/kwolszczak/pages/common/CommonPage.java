package pl.kwolszczak.pages.common;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pl.kwolszczak.models.BasketLine;
import pl.kwolszczak.models.Product;
import pl.kwolszczak.pages.home.HomePage;
import pl.kwolszczak.pages.search.SearchResultPage;
import pl.kwolszczak.pages.support.SupportPage;

import java.util.List;

public class CommonPage extends SupportPage {

    private HeaderComponent header;
    private FooterComponent footer;

    @FindBy(css = "#header")
    private WebElement headerSection;

    @FindBy(css = "#footer")
    private WebElement footerSection;

    @FindBy(css = "ul#ui-id-1 li a span:nth-child(3)")
    private List<WebElement> searchHints;

    public CommonPage(WebDriver driver) {
        super(driver);
        header = new HeaderComponent(driver, headerSection);
        footer = new FooterComponent(driver, footerSection);
    }

    public SearchResultPage search(String product) {
        header.search(product, true);
        return new SearchResultPage(driver);
    }

    public CommonPage search(String product, boolean searchClick) {
        if (!searchClick) {
            header.search(product, false);
        }
        return this;
    }


    public HomePage goHomePage(){
        header.goHomePage();
        return new HomePage(driver);
    }

    protected void openProductPage(ThumbnailListComponent thumbnails,String name) {
        thumbnails.getProduct(name).openProductDetailsPage();
    }

    protected int randomProductIndex(ThumbnailListComponent thumbnails) {
        int size = thumbnails.getProducts().size();
        return random.nextInt(0, size);
    }

    public List<String> getSearchHints() {
        wait.until(ExpectedConditions.visibilityOfAllElements(searchHints));
        return searchHints.stream().map(WebElement::getText).toList();
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


    public int getNumberOfProductsInBasket() {
        return header.getNumberOfProductsInBasket();
    }

}
