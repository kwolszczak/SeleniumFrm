package products;

import base.BaseTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import pl.kwolszczak.models.UserFactory;
import pl.kwolszczak.pages.categories.CategoryPage;
import pl.kwolszczak.providers.Category;

public class FiltersTest extends BaseTest {

    // @Test
    @Disabled
    @RepeatedTest(10)
    void simpleTest() throws InterruptedException {
        driver.get(Category.ACCESSORIES.getUrl());
        CategoryPage categoryPage = new CategoryPage(driver);
        var expectedNumOfProducts = categoryPage.getNumberOfProducts();

        categoryPage
                .setPrice(13, 15)
                .clearFillters();


        var numOfProducts = categoryPage.getNumberOfProducts();
        Assertions.assertThat(numOfProducts).isEqualTo(expectedNumOfProducts);
    }
}
