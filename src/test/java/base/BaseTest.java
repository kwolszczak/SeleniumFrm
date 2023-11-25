package base;

import configuration.AppConf;
import configuration.driver.DriverFactory;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;

public class BaseTest {
    protected WebDriver driver;

    @BeforeAll
    static void setConf() {
        AppConf conf = AppConf.getInstance();
    }

    @BeforeEach
    void setUp() {
        driver = new DriverFactory().getDriver();
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }
}
