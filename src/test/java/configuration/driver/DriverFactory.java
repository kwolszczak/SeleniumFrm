package configuration.driver;

import configuration.browser.Browser;
import configuration.driver.setup.SetupChrome;
import configuration.driver.setup.SetupDriver;
import configuration.driver.setup.SetupEdge;
import configuration.driver.setup.SetupFirefox;
import lombok.Getter;
import lombok.SneakyThrows;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class DriverFactory {

    @Getter
    private WebDriver driver;
    private String browserName = "chrome";
    private final String url;
    private final Logger log;
    private Browser browser;

    public DriverFactory() {
        log = LoggerFactory.getLogger(DriverFactory.class);
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
            case CHROME -> driver = setUpDriver(SetupChrome.class);
            case FIREFOX -> driver = setUpDriver(SetupFirefox.class);
            case EDGE -> driver = setUpDriver(SetupEdge.class);
            default -> driver = new SafariDriver();
        }
    }

    private void navigateUrl() {
        log.info("Open appUrl from config {}", url);
        driver.get(url);
    }

    @SneakyThrows
    private  <T extends SetupDriver> WebDriver setUpDriver(Class<T> driverType) {
        return driverType.getDeclaredConstructor().newInstance().getDriver();
    }

}
