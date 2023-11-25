package pl.kwolszczak.pages.common;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pl.kwolszczak.pages.support.SupportPage;

public class ThumbnailComponent extends SupportPage {

    @FindBy(css = "div.product-description a")
    private WebElement name;
    public ThumbnailComponent(WebDriver driver, WebElement parent) {
        super(driver, parent);
    }

    public String getName() {
        return name.getText();
    }
}
