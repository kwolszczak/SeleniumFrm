import configuration.AppConf;
import configuration.driver.DriverFactory;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import pl.kwolszczak.models.UserFactory;

class BaseTest {
    protected static WebDriver driver;

    @Test
    void simpleTest() {


        AppConf.getInstance();
        UserFactory.getAlreadyRegistredUser();
        driver = new DriverFactory().getDriver();

        Assertions.assertThat(driver.getTitle()).isEqualTo(System.getProperty("environment.eTitle"));
    }

    @AfterAll
    static void tearDown() {
        driver.quit();
    }
}
