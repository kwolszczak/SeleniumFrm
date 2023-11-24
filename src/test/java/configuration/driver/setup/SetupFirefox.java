package configuration.driver.setup;


import org.openqa.selenium.firefox.FirefoxDriver;

public class SetupFirefox extends SetupDriver {

    @Override
    void initDriverWithOptions() {
        driver = new FirefoxDriver();

        if (maximize) {
            this.driver.manage().window().maximize();
        }
    }
}
