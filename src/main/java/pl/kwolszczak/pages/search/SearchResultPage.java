package pl.kwolszczak.pages.search;

import org.openqa.selenium.WebDriver;
import pl.kwolszczak.models.Product;
import pl.kwolszczak.pages.common.CommonPage;
import pl.kwolszczak.pages.common.ThumbnailListComponent;

import java.util.List;

public class SearchResultPage extends CommonPage {
    private ThumbnailListComponent thumbnails;

    public SearchResultPage(WebDriver driver) {
        super(driver);
        thumbnails = new ThumbnailListComponent(driver);
    }

    public List<Product> getProducts() {
        return thumbnails.getProductsModels();
    }

}
