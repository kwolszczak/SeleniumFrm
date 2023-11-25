package pl.kwolszczak.pages.home;

import org.openqa.selenium.WebDriver;
import pl.kwolszczak.pages.common.CommonPage;
import pl.kwolszczak.pages.common.ThumbnailListComponent;

public class HomePage extends CommonPage {

    private ThumbnailListComponent thumbnails;

    public HomePage(WebDriver driver) {
        super(driver);
        thumbnails = new ThumbnailListComponent(driver);
    }


    public String getProductName() {
        return thumbnails.getRandomProductName();
    }


}
