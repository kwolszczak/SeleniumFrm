package pl.kwolszczak.pages.common;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

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

    private void setThumbnails() {
        thumbnailsComponents = new LinkedList<>();
        thumbnailsComponents = products.stream().map(we-> new ThumbnailComponent(driver,we)).toList();
    }

    public String getRandomProductName() {
        int size = thumbnailsComponents.size();
        int index = random.nextInt(0,size);
        return thumbnailsComponents.get(index).getName();
    }

    public List<String> getProductsNames(){
        return thumbnailsComponents.stream().map(ThumbnailComponent::getName).toList();
    }



}
