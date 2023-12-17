package pl.kwolszczak.pages.support;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.lang.reflect.InvocationTargetException;
import java.time.Duration;
import java.util.List;
import java.util.Random;

public class SupportPage {

    protected WebDriver driver;
    protected Random random = new Random();
    protected WebDriverWait wait;
    protected Actions actions;

    protected SupportPage(WebDriver driver) {
        init(driver);
        PageFactory.initElements(driver, this);
    }

    protected SupportPage(WebDriver driver, WebElement parent) {
        init(driver);
        PageFactory.initElements(new DefaultElementLocatorFactory(parent), this);
    }

    private void init(WebDriver driver) {
        Duration timeout = Duration.ofSeconds(Integer.parseInt(System.getProperty("environment.webElementTimeout")));
        this.driver = driver;
        this.wait = new WebDriverWait(driver, timeout);
        this.actions = new Actions(driver);
    }

    protected void fill(WebElement element, String value) {
        element.clear();
        element.sendKeys(value);
    }

    protected void click(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
    }

    protected void select(WebElement element) {
        element.click();
    }

    protected void select(WebElement element, String text) {
        Select select = new Select(element);
        select.selectByVisibleText(text);
    }

    protected int parseDigits(WebElement element) {
        return Integer.parseInt(element.getText().replaceAll("\\D", "").trim());
    }

    protected Double parsePrice(WebElement element) {
        return Double.parseDouble(element.getText().replaceAll("[^\\d.]", "").trim());
    }


    protected <T extends Component> List<T> setComponents(Class<T> componentType, List<WebElement> webElements) {
        return webElements.stream().map(we -> {
            try {
                return componentType.getDeclaredConstructor(WebDriver.class, WebElement.class).newInstance(driver, we);
            } catch (InstantiationException | InvocationTargetException | IllegalAccessException |
                     NoSuchMethodException e) {
                throw new RuntimeException(e);
            }
        }).toList();
    }

}
