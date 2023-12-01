package pl.kwolszczak.pages.categories;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pl.kwolszczak.models.Product;
import pl.kwolszczak.pages.common.CommonPage;
import pl.kwolszczak.pages.common.ThumbnailListComponent;
import pl.kwolszczak.pages.product.ProductPage;

import java.util.List;

public class CategoryPage extends CommonPage {

    private ThumbnailListComponent thumbnails;
    private FilterComponent filter;

    @FindBy(css = "#js-product-list-top p")
    private WebElement numberOfProducts;

    @FindBy(css = "#js-product-list-header h1")
    private WebElement categoryTitle;

    @FindBy(css = "div#search_filters")
    private WebElement filterMenu;

    public CategoryPage(WebDriver driver) {
        super(driver);
        thumbnails = new ThumbnailListComponent(driver);
        if (isFilterMenuDisplayed()) {
            filter = new FilterComponent(driver, filterMenu);
        }
    }

    public ProductPage openProductPage(String name) {
        openProductPage(thumbnails, name);
        return new ProductPage(driver);
    }

    public int getNumberOfProducts() {
        return getDigits(numberOfProducts);
    }

    public String getCategoryTitle() {
        return categoryTitle.getText();
    }

    public boolean isFilterMenuDisplayed() {
        return filterMenu.isDisplayed();
    }

    public CategoryPage clearFilters() {
        filter.clearAllFilters();
        return this;
    }

    public CategoryPage setPrice(int minPrice, int maxPrice) {
        filter.setPrice(minPrice, maxPrice);
        return this;
    }

    public List<Product> getProducts() {
        return thumbnails.getProductsModels();
    }
}
