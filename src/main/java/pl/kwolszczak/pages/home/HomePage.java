package pl.kwolszczak.pages.home;

import org.openqa.selenium.WebDriver;
import pl.kwolszczak.models.Product;
import pl.kwolszczak.pages.common.CommonPage;
import pl.kwolszczak.pages.common.ThumbnailListComponent;
import pl.kwolszczak.pages.product.ProductPage;

public class HomePage extends CommonPage {

    private ThumbnailListComponent thumbnails;

    public HomePage(WebDriver driver) {
        super(driver);
        thumbnails = new ThumbnailListComponent(driver);
    }

    private int randomProductIndex(){
        int size = thumbnails.getProducts().size();
        int index = random.nextInt(0, size);
        return index;
    }

    public Product toRandomProduct() {
        var index = randomProductIndex();
        return thumbnails.getProductsModels().get(index);
    }

    public ProductPage openRandomProductPage() {
        thumbnails.getProducts().get(randomProductIndex()).openProductDetailsPage();
        return new ProductPage(driver);
    }
}
