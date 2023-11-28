package basket;

import base.BaseTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import pl.kwolszczak.models.Basket;
import pl.kwolszczak.models.UserFactory;
import pl.kwolszczak.pages.basket.CartPage;
import pl.kwolszczak.pages.basket.ProductPopUpPage;
import pl.kwolszczak.pages.categories.CategoryPage;
import pl.kwolszczak.pages.home.HomePage;
import pl.kwolszczak.pages.product.ProductPage;
import pl.kwolszczak.providers.UrlProvider;

public class BasketTest extends BaseTest {

    @RepeatedTest(3)
    @DisplayName("Generic - add product to basket")
    void addProductToBasket_generic() throws InterruptedException {
        int quantityOfProduct = random.nextInt(1, 8);
        Basket basket = new Basket();

        for (int i = 0; i < 10; i++) {
            at(HomePage.class)
                    .openRandomProductPage();
            at(ProductPage.class)
                    .addToBasket(quantityOfProduct, basket);
            at(ProductPopUpPage.class)
                    .continueShopping();
            at(ProductPage.class)
                    .goHomePage();
        }
        driver.get(UrlProvider.CART);

        var actualBasket = at(CartPage.class)
                .toBasketModel();

        Assertions.assertThat(actualBasket)
                .usingRecursiveComparison()
                .isEqualTo(basket);
    }

    @RepeatedTest(3)
    @DisplayName("Add product to basket")
    void addProductToBasket() {

        var searchedProduct = "THE BEST IS YET POSTER";
        var quantity = 3;
        var basket = new Basket();

        driver.get(UrlProvider.ART);
        at(CategoryPage.class)
                .openProductPage(searchedProduct);

        at(ProductPage.class)
                .addToBasket(quantity, basket);

        var actualBasket = at(ProductPopUpPage.class)
                .toBasketModel();

        Assertions.assertThat(actualBasket)
                .usingRecursiveComparison()
                .isEqualTo(basket);
    }

    @RepeatedTest(3)
    @DisplayName("Remove")
    void remove() {

        var us1 = UserFactory.getAlreadyRegistredUser();
        var us2 = UserFactory.getRandomUser();
        System.out.println(us1);
        System.out.println(us2);
    }
}
