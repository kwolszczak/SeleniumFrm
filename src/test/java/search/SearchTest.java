package search;

import base.BaseTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import pl.kwolszczak.models.Product;
import pl.kwolszczak.pages.home.HomePage;

import java.util.List;

class SearchTest extends BaseTest {

    @RepeatedTest(10)
    @DisplayName("Search product")
    void searchTest() {

        HomePage homePage = new HomePage(driver);
        Product randomProduct = homePage.getRandomProduct();
        String productName = randomProduct.getName();

        List<Product> foundedProducts = homePage
                .search(productName)
                .getProducts();

        Assertions.assertThat(foundedProducts)
                .usingRecursiveFieldByFieldElementComparator()
                .contains(randomProduct);
    }

    @RepeatedTest(10)
    @DisplayName("Search product- drop down")
    void searchTest_dropDown() {

        var productName = "HUMMINGBIRD";
        HomePage homePage = new HomePage(driver);

        var searchHints = homePage
                .search(productName, false)
                .getSearchHints();

        Assertions.assertThat(searchHints)
                .anySatisfy(item -> Assertions.assertThat(item).contains(productName));
    }
}
