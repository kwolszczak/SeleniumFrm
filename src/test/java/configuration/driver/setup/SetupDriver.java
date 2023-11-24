package configuration.driver.setup;

import lombok.Getter;
import org.openqa.selenium.WebDriver;

public abstract class SetupDriver {

    @Getter
    protected WebDriver driver;

    protected boolean headless = false;
    protected int implicitTimeout = 5;
    protected String downloadDir = "\\src\\download";
    protected boolean maximize = true;

    public SetupDriver() {
        initBrowserSettings();
        initDriverWithOptions();
    }

    private void initBrowserSettings() {

        this.implicitTimeout = System.getProperty("environment.implicitTimeout") == null || Integer.parseInt(System.getProperty("environment.implicitTimeout")) <= 0 ? this.implicitTimeout : Integer.parseInt(System.getProperty("environment.implicitTimeout"));
        this.downloadDir = System.getProperty("browser.downloadDir") == null ? this.downloadDir : System.getProperty("browser.downloadDir");
        this.headless = System.getProperty("browser.headless") == null ? this.headless : Boolean.parseBoolean(System.getProperty("browser.headless"));
        this.maximize = System.getProperty("browser.maximize") == null ? this.maximize : Boolean.parseBoolean(System.getProperty("browser.maximize"));
    }

    abstract void initDriverWithOptions();
}
