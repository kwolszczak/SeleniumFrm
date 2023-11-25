package pl.kwolszczak.pages.common;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
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
    private List<WebElement> searchList;

    public CommonPage(WebDriver driver) {
        super(driver);
        header = new HeaderComponent(driver, headerSection);
        footer = new FooterComponent(driver, footerSection);
    }

    public SearchResultPage search(String product) {
        header.search(product);
        return new SearchResultPage(driver);
    }

    public CommonPage fillSearch(String product) {
        header.fillInSearch(product);
        return this;
    }

    public List<String> getSearchResults() {
        wait.until(ExpectedConditions.visibilityOfAllElements(searchList));
        return searchList.stream().map(WebElement::getText).toList();
    }

}
