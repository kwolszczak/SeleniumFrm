package pl.kwolszczak.pages.categories;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pl.kwolszczak.pages.support.Component;

public class FilterComponent extends Component {

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

    protected void setPrice(double from, double to) {

        while (getPriceFrom() < from) {
            leftBtn.sendKeys(Keys.ARROW_RIGHT);

            var spinner = driver.findElement(By.cssSelector("div.faceted-overlay"));
            wait.until(ExpectedConditions.invisibilityOf(spinner));
        }

        while (getPriceTo() > to) {
            rightBtn.sendKeys(Keys.ARROW_LEFT);

            var spinner = driver.findElement(By.cssSelector("div.faceted-overlay"));
            wait.until(ExpectedConditions.invisibilityOf(spinner));
        }

    }

    protected void clearAllFilters() {
        clearAll.click();
        wait.until(ExpectedConditions.refreshed(ExpectedConditions.invisibilityOf(
                clearAll
        )));
    }

    private double getPriceFrom() {
        double priceFrom = Double.parseDouble((priceLabel).getText().split("-")[0].replaceAll("[$]", ""));
        return priceFrom;
    }

    private double getPriceTo() {
        double priceTo = Double.parseDouble((priceLabel).getText().split("-")[1].replaceAll("[$]", ""));
        return priceTo;
    }

}
