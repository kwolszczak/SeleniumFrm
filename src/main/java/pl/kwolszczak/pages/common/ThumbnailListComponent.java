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
        thumbnailsComponents = setComponents(ThumbnailComponent.class, products);
    }

    public List<ThumbnailComponent> getProducts() {
        return thumbnailsComponents;
    }

    public ThumbnailComponent getProduct(String name) {
       return thumbnailsComponents.stream().filter(tc ->tc.getName().equals(name)).findFirst().orElse(null);
    }


    public List<Product> getProductsModels() {
        return thumbnailsComponents.stream().map(ThumbnailComponent::toProduct).toList();
    }

}
