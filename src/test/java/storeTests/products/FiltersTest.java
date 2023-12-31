package storeTests.products;

import storeTests.base.BaseTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.RepeatedTest;
import pl.kwolszczak.pages.categories.CategoryPage;
import pl.kwolszczak.providers.Category;

class FiltersTest extends BaseTest {

    @RepeatedTest(3)
    void filterTest()  {
        driver.get(Category.ACCESSORIES.getUrl());
        var expectedNumOfProducts = at(CategoryPage.class)
                .getNumberOfProducts();

        var actualNumOfProducts = at(CategoryPage.class)
                .setPrice(13, 15)
                .clearFilters()
                .getNumberOfProducts();

        Assertions.assertThat(actualNumOfProducts).isEqualTo(expectedNumOfProducts);
    }
}
