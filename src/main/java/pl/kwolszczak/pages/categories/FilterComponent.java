package pl.kwolszczak.pages.categories;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pl.kwolszczak.pages.support.SupportPage;

import java.util.List;

public class FilterComponent  extends SupportPage {



    @FindBy(css = "p[id *= 'facet_label']")
    private WebElement priceLabel;

    @FindBy(css = "div#_desktop_search_filters_clear_all > button")
    private WebElement clearAll;

    @FindBy(css = "div[id*='slider-range'] > a:nth-child(3)")
    private WebElement rightBtn;

    @FindBy(css = "div[id*='slider-range'] > a:nth-child(2)")
    private WebElement leftBtn;



    public FilterComponent(WebDriver driver, WebElement parent) {
        super(driver, parent);
    }

    public FilterComponent setPrice(double from, double to) throws InterruptedException {

        while (getPriceFrom() < from) {

            leftBtn.sendKeys(Keys.ARROW_RIGHT);
            wait.until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOfAllElements(
                    List.of(priceLabel,leftBtn, clearAll)
                    )));
        }
        while (getPriceTo() > to) {

            rightBtn.sendKeys(Keys.ARROW_LEFT);
            wait.until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOfAllElements(
                    List.of(priceLabel,rightBtn, clearAll)
            )));
        }
        return this;
    }

    public FilterComponent clearAllFilters()  {
        clearAll.click();
        wait.until(ExpectedConditions.refreshed(ExpectedConditions.invisibilityOf(
                clearAll
        )));
        return this;
    }

    private double getPriceFrom() throws InterruptedException {

        double priceFrom = Double.parseDouble((priceLabel).getText().split("-")[0].replaceAll("[$]",""));
        return priceFrom;
    }

    private double getPriceTo() throws InterruptedException {
        double priceTo = Double.parseDouble((priceLabel).getText().split("-")[1].replaceAll("[$]",""));
        return priceTo;
    }

}
