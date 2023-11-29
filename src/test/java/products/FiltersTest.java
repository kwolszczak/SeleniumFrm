package products;

import base.BaseTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.RepeatedTest;
import pl.kwolszczak.pages.categories.CategoryPage;
import pl.kwolszczak.providers.Category;

public class FiltersTest extends BaseTest {

    // @Test
    @RepeatedTest(3)
    void filterTest()  {

        driver.get(Category.ACCESSORIES.getUrl());
        var expectedNumOfProducts = at(CategoryPage.class)
                .getNumberOfProducts();


        var numOfProducts = at(CategoryPage.class)
                .setPrice(13, 15)
                .clearFilters()
                .getNumberOfProducts();

        Assertions.assertThat(numOfProducts).isEqualTo(expectedNumOfProducts);
    }
}
