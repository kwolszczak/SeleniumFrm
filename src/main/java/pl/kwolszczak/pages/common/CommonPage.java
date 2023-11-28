package pl.kwolszczak.pages.common;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pl.kwolszczak.pages.home.HomePage;
import pl.kwolszczak.pages.search.SearchResultPage;
import pl.kwolszczak.pages.support.SupportPage;

import java.util.List;

public class CommonPage extends SupportPage {

    private HeaderComponent header;
    private FooterComponent footer;

    @FindBy(css = "#header")
    private WebElement headerSection;

    @FindBy(css = "#footer")
    private WebElement footerSection;

    @FindBy(css = "ul#ui-id-1 li a span:nth-child(3)")
    private List<WebElement> searchHints;

    public CommonPage(WebDriver driver) {
        super(driver);
        header = new HeaderComponent(driver, headerSection);
        footer = new FooterComponent(driver, footerSection);
    }

    public SearchResultPage search(String product) {
        header.search(product, true);
        return new SearchResultPage(driver);
    }

    public CommonPage search(String product, boolean click) {
        if (!click) {
            header.search(product, false);
        }
        return this;
    }


    public HomePage goHomePage(){
        header.goHomePage();
        return new HomePage(driver);
    }

    public List<String> getSearchHints() {
        wait.until(ExpectedConditions.visibilityOfAllElements(searchHints));
        return searchHints.stream().map(WebElement::getText).toList();
    }

    public int getNumberOfProductsInBasket() {
        return header.getNumberOfProductsInBasket();
    }

}
