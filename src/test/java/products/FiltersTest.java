package products;

import base.BaseTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import pl.kwolszczak.models.UserFactory;

public class FiltersTest extends BaseTest {

    @Test
    @Disabled
    void simpleTest() {
        var us1 = UserFactory.getAlreadyRegistredUser();
        var us2 = UserFactory.getRandomUser();
        System.out.println(us1);
        System.out.println(us2);

        Assertions.assertThat(driver.getTitle()).isEqualTo(System.getProperty("environment.eTitle"));
    }
}
