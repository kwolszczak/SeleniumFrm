package storeTests.products;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import pl.kwolszczak.pages.categories.CategoryPage;
import pl.kwolszczak.providers.Category;
import pl.kwolszczak.providers.Subcategory;
import storeTests.base.BaseTest;

class CategoriesTest extends BaseTest {

    @RepeatedTest(3)
    @DisplayName("Category test")
    void categoryTest() {

        for (var category : Category.values()) {
            var expectedCategoryTitle = category.getTitle();

            driver.get(category.getUrl());
            CategoryPage categoryPage = new CategoryPage(driver);

            step_verifyNumOfProducts(categoryPage);
            step_verifyTitle(categoryPage, expectedCategoryTitle);
            step_verifyFilterMenu(categoryPage);
        }
    }

    @RepeatedTest(3)
    @DisplayName("SubCategory test")
    void subCategoryTest() {

        for (var subcategory : Subcategory.values()) {
            var expectedSubcategoryTitle = subcategory.getTitle();

            driver.get(subcategory.getUrl());
            CategoryPage categoryPage = new CategoryPage(driver);

            step_verifyNumOfProducts(categoryPage);
            step_verifyTitle(categoryPage, expectedSubcategoryTitle);
            step_verifyFilterMenu(categoryPage);
        }
    }

    private void step_verifyNumOfProducts(CategoryPage categoryPage) {
        var numOfProductsLabel = categoryPage.getNumberOfProducts();
        var numOfProducts = categoryPage.getProducts().size();
        Assertions.assertThat(numOfProductsLabel).isEqualTo(numOfProducts);
    }

    private void step_verifyTitle(CategoryPage categoryPage, String expectedCategoryTitle) {
        var actualCategoryTitle = categoryPage.getCategoryTitle();
        Assertions.assertThat(actualCategoryTitle).isEqualToIgnoringCase(expectedCategoryTitle);
    }

    private void step_verifyFilterMenu(CategoryPage categoryPage) {
        var isFilterMenuDisplayed = categoryPage.isFilterMenuDisplayed();
        Assertions.assertThat(isFilterMenuDisplayed).isTrue();
    }
}
