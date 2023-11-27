package pl.kwolszczak.pages.support;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Random;

public class SupportPage {
    protected WebDriver driver;
    protected Random random = new Random();
    protected WebDriverWait wait;
    private Duration timeout;

    public SupportPage(WebDriver driver) {
        this.driver = driver;
        initTimeout(driver);
        PageFactory.initElements(driver, this);
    }

    public SupportPage(WebDriver driver, WebElement parent) {
        initTimeout(driver);
        PageFactory.initElements(new DefaultElementLocatorFactory(parent), this);
    }

    private void initTimeout(WebDriver driver) {
        this.timeout = Duration.ofSeconds(Integer.parseInt(System.getProperty("environment.webElementTimeout")));
        this.wait = new WebDriverWait(driver, timeout);
    }

    public void fill(WebElement element, String value) {
        element.clear();
        element.sendKeys(value);
    }

    public void clickIt(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
    }

}
