package pl.kwolszczak.pages.categories;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pl.kwolszczak.pages.common.CommonPage;
import pl.kwolszczak.pages.common.HeaderComponent;
import pl.kwolszczak.pages.common.ThumbnailComponent;
import pl.kwolszczak.pages.home.HomePage;
import pl.kwolszczak.pages.product.ProductPage;
import pl.kwolszczak.pages.support.componentFactory.ComponentFactory;
import pl.kwolszczak.pages.support.componentFactory.SetupComponent;

import java.util.List;

public class CategoryPageCustom extends CommonPage {


    @FindBy(css = "#js-product-list-top p")
    private WebElement numberOfProducts;

    @FindBy(css = "#js-product-list-header h1")
    private WebElement categoryTitle;

    @FindBy(css = "div#search_filters")
    private WebElement filterMenu;

    @FindBy(css = "div#_desktop_logo")
    private WebElement logo;

    @SetupComponent(css = "div#search_filters")
    public FilterComponent filter;

    @SetupComponent(css = "#header")
    private HeaderComponent header;

    @SetupComponent(css = "div.products.row > div")
    private List<ThumbnailComponent> thumbnailsComponents;

    public CategoryPageCustom(WebDriver driver) throws NoSuchMethodException {
        super(driver);
        PageFactory.initElements(driver, this);
        ComponentFactory.init(driver, this);

    }

    public HomePage goHomePage() {
        header.goHomePage();

        var home = new HomePage(driver);
        var size = home.search("Humm").getProducts().size();
        System.out.println(size);
        return home;
    }

    public ProductPage goProductDetails() {
        System.out.println(thumbnailsComponents.size());
        thumbnailsComponents.get(0).openProductDetailsPage();
        return new ProductPage(driver);
    }

    public int getNumberOfProducts() {
        return parseDigits(numberOfProducts);
    }

    public String getCategoryTitle() {
        return categoryTitle.getText();
    }

    public boolean isFilterMenuDisplayed() {
        return filterMenu.isDisplayed();
    }

    public CategoryPageCustom clearFilters() {
        filter.clearAllFilters();
        return this;
    }

    public CategoryPageCustom setPrice(int minPrice, int maxPrice) {
        filter.setPrice(minPrice, maxPrice);
        return this;
    }


}
