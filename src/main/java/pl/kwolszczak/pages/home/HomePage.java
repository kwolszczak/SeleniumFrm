package pl.kwolszczak.pages.home;

import org.openqa.selenium.WebDriver;
import pl.kwolszczak.models.Product;
import pl.kwolszczak.pages.common.CommonPage;
import pl.kwolszczak.pages.common.ThumbnailListComponent;

public class HomePage extends CommonPage {

    private ThumbnailListComponent thumbnails;

    public HomePage(WebDriver driver) {
        super(driver);
        thumbnails = new ThumbnailListComponent(driver);
    }

    public Product getRandomProduct() {
        int size = thumbnails.getProducts().size();
        int index = random.nextInt(0, size);
        return thumbnails.getProducts().get(index);
    }
}
