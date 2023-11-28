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
        // //1
        //setThumbnailsComponents();

        //2
        thumbnailsComponents = setComponents(ThumbnailComponent.class, products);
    }

    public List<ThumbnailComponent> getProducts() {
        return thumbnailsComponents;
    }

    public ThumbnailComponent getProduct(String name) {
       return thumbnailsComponents.stream().filter(tc ->tc.getName().equals(name)).findFirst().orElse(null);
    }


//    //1
//    private void setThumbnailsComponents() {
//        thumbnailsComponents = new LinkedList<>();
//        thumbnailsComponents = products.stream().map(we -> new ThumbnailComponent(driver, we)).toList();
//    }

    public List<Product> getProductsModels() {
        return thumbnailsComponents.stream().map(ThumbnailComponent::toProduct).toList();
    }

}
