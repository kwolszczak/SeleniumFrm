package pl.kwolszczak.pages.common;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pl.kwolszczak.models.Product;

import java.util.LinkedList;
import java.util.List;

public class ThumbnailListComponent extends CommonPage {

    private List<ThumbnailComponent> thumbnailsComponents;

    @FindBy(css = "div.products.row > div")
    private List<WebElement> products;

    public ThumbnailListComponent(WebDriver driver) {
        super(driver);
        setThumbnails();
    }

    public List<ThumbnailComponent> getProducts() {
        return thumbnailsComponents;
    }


    private void setThumbnails() {
        thumbnailsComponents = new LinkedList<>();
        thumbnailsComponents = products.stream().map(we -> new ThumbnailComponent(driver, we)).toList();
    }

    public List<Product> getProductsModels() {
        return thumbnailsComponents.stream().map(ThumbnailComponent::toProduct).toList();
    }


}
