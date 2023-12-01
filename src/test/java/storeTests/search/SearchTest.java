package storeTests.search;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import pl.kwolszczak.pages.home.HomePage;
import pl.kwolszczak.pages.search.SearchResultPage;
import storeTests.base.BaseTest;

class SearchTest extends BaseTest {

    @RepeatedTest(3)
    @DisplayName("Search random product")
    void searchRandomProductTest() {

        var randomProduct = at(HomePage.class).getRandomProduct();
        var randomProductName = randomProduct.getName();

        at(HomePage.class)
                .search(randomProductName);
        var allFoundedProducts = at(SearchResultPage.class)
                .getProducts();

        Assertions.assertThat(allFoundedProducts)
                .usingRecursiveFieldByFieldElementComparator()
                .contains(randomProduct);
    }

    @RepeatedTest(3)
    @DisplayName("Search product hints- drop down")
    void searchProductHintsTest_dropDown() {

        var productName = "HUMMINGBIRD";

        var searchHints = at(HomePage.class)
                .search(productName, false)
                .getSearchHints();

        Assertions.assertThat(searchHints)
                .anySatisfy(hint -> Assertions.assertThat(hint).contains(productName));
    }
}
