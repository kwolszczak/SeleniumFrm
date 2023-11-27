package search;

import base.BaseTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import pl.kwolszczak.pages.home.HomePage;

class SearchTest extends BaseTest {

    @RepeatedTest(3)
    @DisplayName("Search product")
    void searchTest() {

        var randomProduct = at(HomePage.class)
                .getRandomProduct();

        var foundedProducts = at(HomePage.class)
                .search(randomProduct.getName())
                .getProducts();


        Assertions.assertThat(foundedProducts)
                .usingRecursiveFieldByFieldElementComparator()
                .contains(randomProduct);
    }

    @RepeatedTest(10)
    @DisplayName("Search product- drop down")
    void searchTest_dropDown() {

        var productName = "HUMMINGBIRD";

        var searchHints = at(HomePage.class)
                .search(productName, false)
                .getSearchHints();


        Assertions.assertThat(searchHints)
                .anySatisfy(item -> Assertions.assertThat(item).contains(productName));
    }
}
