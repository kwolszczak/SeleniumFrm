package pl.kwolszczak.pages.home;

import org.openqa.selenium.WebDriver;
import pl.kwolszczak.models.Product;
import pl.kwolszczak.pages.common.CommonPage;
import pl.kwolszczak.pages.common.ThumbnailListComponent;
import pl.kwolszczak.pages.product.ProductPage;

public class HomePage extends CommonPage {

    private final ThumbnailListComponent thumbnails;

    public HomePage(WebDriver driver) {
        super(driver);
        thumbnails = new ThumbnailListComponent(driver);
    }

    public ProductPage openRandomProductPage() {
        openRandomProductPage(thumbnails);
        return new ProductPage(driver);
    }

    public ProductPage openProductPage(String name) {
        openProductPage(thumbnails, name);
        return new ProductPage(driver);
    }

    public Product getRandomProduct() {
        var index = randomProductIndex(thumbnails);
        return thumbnails.getProductsModels().get(index);
    }

}
