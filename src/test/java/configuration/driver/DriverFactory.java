package configuration.driver;

import configuration.browser.Browser;
import configuration.driver.setup.SetupChrome;
import configuration.driver.setup.SetupEdge;
import configuration.driver.setup.SetupFirefox;
import lombok.Getter;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.safari.SafariDriver;


@Slf4j
public class DriverFactory {

    @Getter
    private WebDriver driver;
    private String browserName = "chrome";
    private final String url;
    private Browser browser;

    public DriverFactory() {
        url = System.getProperty("environment.url");
        initBrowser();
        initDriver();
        navigateUrl();
    }

    private void initBrowser() {
        browserName = System.getProperty("browser.name") == null ? browserName : System.getProperty("browser.name");
        browser = Browser.get(browserName);
    }

    private void initDriver() {
        log.info("#### Setup {} browser", browserName);
        switch (browser) {
            case CHROME -> driver = new SetupChrome().getDriver();
            case FIREFOX -> driver = new SetupFirefox().getDriver();
            case EDGE -> driver = new SetupEdge().getDriver();
            default -> driver = new SafariDriver();
        }
    }

    private void navigateUrl() {
        log.info("Open appUrl from config {}", url);
        driver.get(url);
    }
}
