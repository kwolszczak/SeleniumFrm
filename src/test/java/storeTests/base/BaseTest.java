package storeTests.base;

import configuration.AppConf;
import configuration.TestDataConf;
import configuration.driver.DriverFactory;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import pl.kwolszczak.pages.support.SupportPage;

import java.util.Random;
import java.util.function.Consumer;

@Slf4j
public class BaseTest {
    protected WebDriver driver;
    protected static Random random;

    @BeforeAll
    static void setConf() {
        log.info(">>>> START BEFORE ALL >>>>");

        AppConf.getInstance();
        TestDataConf.getInstance();
        random = new Random();
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

    @SneakyThrows
    public <T extends SupportPage> T at(Class<T> pageType, Consumer<T> pageAction) {
        var page = at(pageType);
        pageAction.accept(page);
        return page;
    }
}
