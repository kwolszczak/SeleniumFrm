package pl.kwolszczak.pages.categories;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pl.kwolszczak.pages.support.SupportPage;

import java.util.List;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.awaitility.Awaitility.await;

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

    public FilterComponent setPrice(double from, double to)  {

        while (getPriceFrom() < from) {

            leftBtn.sendKeys(Keys.ARROW_RIGHT);
           /* wait.until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOfAllElements(
                    List.of(priceLabel,leftBtn, clearAll)
                    )));*/
            await().atMost(10, SECONDS).until(() -> isElementPresent(leftBtn));
            await().atMost(10, SECONDS).until(() -> isElementPresent(rightBtn));
        }
        while (getPriceTo() > to) {


            rightBtn.sendKeys(Keys.ARROW_LEFT);
            await().atMost(10, SECONDS).until(() -> isElementPresent(rightBtn));
            await().atMost(10, SECONDS).until(() -> isElementPresent(leftBtn));
          /*  wait.until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOfAllElements(
                    List.of(priceLabel,rightBtn, clearAll)
            )));*/



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

    private double getPriceFrom()  {

        double priceFrom = Double.parseDouble((priceLabel).getText().split("-")[0].replaceAll("[$]",""));
        return priceFrom;
    }

    private double getPriceTo()  {
        double priceTo = Double.parseDouble((priceLabel).getText().split("-")[1].replaceAll("[$]",""));
        return priceTo;
    }

    private static boolean isElementPresent(WebElement webElement) {
        try {
            webElement.isDisplayed();
            return true;
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }

}
