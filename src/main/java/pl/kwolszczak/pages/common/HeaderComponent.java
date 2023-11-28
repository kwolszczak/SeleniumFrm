package pl.kwolszczak.pages.common;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pl.kwolszczak.pages.support.Component;
import pl.kwolszczak.pages.support.SupportPage;

public class HeaderComponent extends Component {

    @FindBy(css = "div#search_widget input[type='text']")
    private WebElement searchInp;

    @FindBy(css = "div#search_widget button")
    private WebElement searchBtn;

    @FindBy(css = "img.logo.img-responsive")
    private WebElement homeBtn;


    public HeaderComponent(WebDriver driver, WebElement parent) {
        super(driver, parent);
    }

    public void search(String product, boolean click) {
        if (click) {
            fillIt(searchInp, product);
            clickIt(searchBtn);
        } else {
            fillIt(searchInp, product);
        }
    }

    public void goHomePage() {
        clickIt(homeBtn);
    }
}
