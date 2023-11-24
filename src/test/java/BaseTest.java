import configuration.driver.DriverFactory;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

 class BaseTest {
    protected static WebDriver driver;

    @Test
    void simpleTest() {

        System.setProperty("environment.url","https://onet.pl");
        System.setProperty("browser.name","chrome");


        driver = new DriverFactory().getDriver();

        Assertions.assertThat(driver.getTitle()).isEqualTo("Onet – Jesteś na bieżąco");
    }

    @AfterAll
    static void tearDown() {
        driver.quit();
    }
}
