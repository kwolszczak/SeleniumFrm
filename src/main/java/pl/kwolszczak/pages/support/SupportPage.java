package pl.kwolszczak.pages.support;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pl.kwolszczak.models.BasketLine;
import pl.kwolszczak.models.Product;
import pl.kwolszczak.pages.common.ThumbnailListComponent;

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

    protected void fillIn(WebElement element, String value) {
        element.clear();
        element.sendKeys(value);
    }

    protected void clickIt(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
    }

    protected void selectIt(WebElement element) {
        element.click();
    }

    protected int getDigits(WebElement element) {
        return Integer.parseInt(element.getText().replaceAll("\\D", "").trim());
    }

    protected BasketLine toBasketLineModel(WebElement name, WebElement quantity, WebElement price) {
        var product = new Product(getName(name), getPrice(price));
        return new BasketLine(product, getQuantity(quantity));
    }

    protected Double getPrice(WebElement we) {
        return Double.parseDouble(we.getText().replaceAll("[$]", "").trim());
    }

    private int getQuantity(WebElement we) {
        if (we.getAttribute("value") == null) {
            return Integer.parseInt(we.getText().replaceAll("\\D",""));
        }
        return Integer.parseInt(we.getAttribute("value"));
    }

    private String getName(WebElement we) {
        return we.getText();
    }

    protected void openRandomProductPage(ThumbnailListComponent thumbnails) {
        thumbnails.getProducts().get(randomProductIndex(thumbnails)).openProductDetailsPage();
    }
    protected void openProductPage(ThumbnailListComponent thumbnails,String name) {
        thumbnails.getProduct(name).openProductDetailsPage();
    }
    protected int randomProductIndex(ThumbnailListComponent thumbnails) {
        int size = thumbnails.getProducts().size();
        return random.nextInt(0, size);
    }

    protected <T extends Component> List<T> setComponents(Class<T> componentType, List<WebElement> webElements) {
       return  webElements.stream().map(we -> {
            try {
                return componentType.getDeclaredConstructor(WebDriver.class, WebElement.class).newInstance(driver, we);
            } catch (InstantiationException | InvocationTargetException | IllegalAccessException | NoSuchMethodException e) {
                throw new RuntimeException(e);
            }
        }).toList();
    }

}
