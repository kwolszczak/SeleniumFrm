package products;

import base.BaseTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import pl.kwolszczak.pages.categories.CategoryPage;
import pl.kwolszczak.providers.Category;
import pl.kwolszczak.providers.Subcategory;

class ProductsTest extends BaseTest {

    //@Test
    @RepeatedTest(3)
    @DisplayName("Category test")
    void categoryTest() {

        for (var category : Category.values()) {
            var expectedTitle = category.getName();
            driver.get(category.getUrl());
            CategoryPage categoryPage = new CategoryPage(driver);

            step_verifyNumOfProducts(categoryPage);
            step_verifyTitle(categoryPage, expectedTitle);
            step_verifyFilterMenu(categoryPage);
        }
    }

    @RepeatedTest(3)
    @DisplayName("SubCategory test")
    void subCategoryTest() {

        for (var category : Subcategory.values()) {
            var expectedTitle = category.getName();
            driver.get(category.getUrl());
            CategoryPage categoryPage = new CategoryPage(driver);

            step_verifyNumOfProducts(categoryPage);
            step_verifyTitle(categoryPage, expectedTitle);
            step_verifyFilterMenu(categoryPage);
        }
    }

    private void step_verifyNumOfProducts(CategoryPage categoryPage) {
        var numOfProductsLabel = categoryPage.getNumberOfProducts();
        var numOfProducts = categoryPage.getProductsModels().size();
        Assertions.assertThat(numOfProducts).isEqualTo(numOfProductsLabel);
    }

    private void step_verifyTitle(CategoryPage categoryPage, String expectedTitle) {
        var title = categoryPage.getCategoryTitle();
        Assertions.assertThat(title).isEqualToIgnoringCase(expectedTitle);
    }

    private void step_verifyFilterMenu(CategoryPage categoryPage) {
        var isFilterMenuDisplayed = categoryPage.isFilterMenuDisplayed();
        Assertions.assertThat(isFilterMenuDisplayed).isTrue();
    }
}
