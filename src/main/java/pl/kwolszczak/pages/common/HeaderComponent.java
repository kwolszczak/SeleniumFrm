package pl.kwolszczak.pages.common;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pl.kwolszczak.pages.support.SupportPage;

import java.util.List;

public class HeaderComponent extends SupportPage {

    @FindBy(css = "div#search_widget input[type='text']")
    private WebElement searchInp;

    @FindBy(css = "div#search_widget button")
    private WebElement searchBtn;


    public HeaderComponent(WebDriver driver, WebElement parent) {
        super(driver, parent);
    }

    public HeaderComponent search(String product, boolean click) {
        if (click) {
            fill(searchInp, product);
            clickIt(searchBtn);
        } else {
            fill(searchInp, product);
        }
        return this;
    }
}
