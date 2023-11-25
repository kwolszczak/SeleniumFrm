package pl.kwolszczak.pages.categories;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pl.kwolszczak.models.Product;
import pl.kwolszczak.pages.common.CommonPage;
import pl.kwolszczak.pages.common.ThumbnailListComponent;

import java.util.List;

public class CategoryPage extends CommonPage {
    private ThumbnailListComponent thumbnails;

    @FindBy(css = "#js-product-list-top p")
    private WebElement numberOfProducts;

    @FindBy(css = "#js-product-list-header h1")
    private WebElement categoryTitle;

    @FindBy(css = "div#search_filters")
    private WebElement filterMenu;

    public CategoryPage(WebDriver driver) {
        super(driver);
        thumbnails = new ThumbnailListComponent(driver);
    }

    public List<Product> getProducts(){
        return thumbnails.getProducts();
    }

    public int getNumberOfProducts() {
        return  Integer.parseInt(numberOfProducts.getText().replaceAll("[^0-9]","").trim());
    }

    public String getCategoryTitle() {
        return categoryTitle.getText();
    }

    public boolean isFilterMenuDisplayed() {
        return filterMenu.isDisplayed();
    }
}
