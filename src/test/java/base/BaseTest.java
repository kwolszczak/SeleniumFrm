package base;

import configuration.AppConf;
import configuration.driver.DriverFactory;

import lombok.SneakyThrows;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import pl.kwolszczak.pages.support.SupportPage;

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

    @SneakyThrows
    public <T extends SupportPage> T at(Class<T> pageType) {
        return pageType.getDeclaredConstructor(WebDriver.class).newInstance(driver);
    }
}
