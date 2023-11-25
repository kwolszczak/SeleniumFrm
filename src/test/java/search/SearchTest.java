package search;

import base.BaseTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pl.kwolszczak.models.UserFactory;
import pl.kwolszczak.pages.home.HomePage;

class SearchTest extends BaseTest {

    @Test
    @DisplayName("Search product")
    void searchTest() throws InterruptedException {

        HomePage homePage = new HomePage(driver);
        var productName = homePage.getProductName();

        var result = homePage.search(productName)
                .getAllProductsNames();

        Assertions.assertThat(result).contains(productName);
    }

    @Test
    @DisplayName("Search product- drop down")
    void searchTest_dropDown() throws InterruptedException {

        HomePage homePage = new HomePage(driver);
        var productName = "HUMMINGBIRD";

        var result = homePage.fillSearch(productName).getSearchResults();

        Assertions.assertThat(result)
                .anySatisfy(item -> Assertions.assertThat(item).contains(productName));


    }

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
