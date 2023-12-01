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

    protected void setPrice(double min, double max) {
        while (getActualMinPrice() < min) {
            leftBtn.sendKeys(Keys.ARROW_RIGHT);
            spinnerWait();
        }
        while (getActualMaxPrice() > max) {
            rightBtn.sendKeys(Keys.ARROW_LEFT);
            spinnerWait();
        }
    }

    protected void clearAllFilters() {
        clearAll.click();
        spinnerWait();
    }

    private double getActualMinPrice() {
        return Double.parseDouble((priceLabel).getText().split("-")[0].replaceAll("[$]", ""));
    }

    private double getActualMaxPrice() {
        return Double.parseDouble((priceLabel).getText().split("-")[1].replaceAll("[$]", ""));
    }

    private void spinnerWait() {
        var spinner = driver.findElement(By.cssSelector("div.faceted-overlay"));
        wait.until(ExpectedConditions.invisibilityOf(spinner));
    }

}
