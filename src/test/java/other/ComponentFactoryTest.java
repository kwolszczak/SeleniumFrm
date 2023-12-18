package other;

import org.junit.jupiter.api.RepeatedTest;
import pl.kwolszczak.pages.categories.CategoryPageCustom;
import pl.kwolszczak.providers.Category;
import storeTests.base.BaseTest;

class ComponentFactoryTest extends BaseTest {

    @RepeatedTest(1)
    void singleComponentTest() throws InterruptedException, NoSuchMethodException {
        driver.get(Category.ACCESSORIES.getUrl());
        new CategoryPageCustom(driver)
                .goHomePage();
        Thread.sleep(5000);

    }

    @RepeatedTest(1)
    void listComponentTest() throws InterruptedException, NoSuchMethodException {
        driver.get(Category.ACCESSORIES.getUrl());
        new CategoryPageCustom(driver)
                .goProductDetails();
        Thread.sleep(5000);
    }
}
