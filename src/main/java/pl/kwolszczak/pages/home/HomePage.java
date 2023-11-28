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
        thumbnails.getProducts().get(randomProductIndex()).openProductDetailsPage();
        return new ProductPage(driver);
    }

    public Product toRandomProductModel() {
        var index = randomProductIndex();
        return thumbnails.getProductsModels().get(index);
    }

    private int randomProductIndex() {
        int size = thumbnails.getProducts().size();
        return random.nextInt(0, size);
    }
}
