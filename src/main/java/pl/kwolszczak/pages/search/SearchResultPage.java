package pl.kwolszczak.pages.search;

import org.openqa.selenium.WebDriver;
import pl.kwolszczak.models.Product;
import pl.kwolszczak.pages.common.CommonPage;
import pl.kwolszczak.pages.common.ThumbnailListComponent;

import java.util.List;

public class SearchResultPage extends CommonPage {

    private final ThumbnailListComponent thumbnails;

    public SearchResultPage(WebDriver driver) {
        super(driver);
        thumbnails = new ThumbnailListComponent(driver);
    }

    public List<Product> getProductsModel() {
        return thumbnails.getProductsModels();
    }

}
