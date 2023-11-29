package basket;

import base.BaseTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import pl.kwolszczak.models.Basket;
import pl.kwolszczak.models.Product;
import pl.kwolszczak.models.UserFactory;
import pl.kwolszczak.pages.account.SigningPage;
import pl.kwolszczak.pages.basket.CartPage;
import pl.kwolszczak.pages.basket.ProductPopUpPage;
import pl.kwolszczak.pages.categories.CategoryPage;
import pl.kwolszczak.pages.checkout.CheckoutPage;
import pl.kwolszczak.pages.home.HomePage;
import pl.kwolszczak.pages.product.ProductPage;
import pl.kwolszczak.providers.UrlProvider;

public class BasketTest extends BaseTest {

    @RepeatedTest(3)
    @DisplayName("Generic - add product to basket")
    void addProductToBasket_generic()  {

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

        at(ProductPopUpPage.class)
                .continueShopping();

        int numberOfProducts = at(ProductPage.class)
                .getNumberOfProductsInBasket();

        Assertions.assertThat(numberOfProducts).isEqualTo(quantity);
    }

    @RepeatedTest(3)
    @DisplayName("Remove")
    void removeProduct()  {

        int quantityOfProduct = 1;
        String emptyBasketInfo = "There are no more items in your cart";

        Basket basket = new Basket();
        Product[] randomProducts = new Product[2];
        for (int i = 0; i < 2; i++) {
            at(HomePage.class)
                    .openRandomProductPage();
            at(ProductPage.class)
                    .addToBasket(quantityOfProduct, basket);
          randomProducts[i] = at(ProductPopUpPage.class)
                    .toBasketLineModel().getProduct();
            at(ProductPopUpPage.class)
                    .continueShopping();
            at(ProductPage.class)
                    .goHomePage();
        }

        var productOne = randomProducts[0];
        var productTwo = randomProducts[1];

        driver.get(UrlProvider.CART);
        var actualTotalPrice = at(CartPage.class)
                .getTotalPrice();
        Assertions.assertThat(actualTotalPrice).isEqualTo(basket.getTotalPrice());

        var newTotalPrice =at(CartPage.class)
                .removeProduct(productOne.getName(),basket)
                .getTotalPrice();
        Assertions.assertThat(newTotalPrice).isEqualTo(basket.getTotalPrice());

        var zeroPrice =at(CartPage.class)
                .removeProduct(productTwo.getName(),basket)
                .getTotalPrice();
        Assertions.assertThat(zeroPrice)
                .isEqualTo(basket.getTotalPrice())
                .isEqualTo(0.00);

        var actualBasketInfo = at(CartPage.class)
                .getInfoAboutEmptyBasket();
        Assertions.assertThat(actualBasketInfo).isEqualTo(emptyBasketInfo);
    }

    @RepeatedTest(3)
    @DisplayName("Checkout test")
    void checkoutTest() throws InterruptedException {
        var registredUser = UserFactory.getAlreadyRegistredUser();
        var productName = "THE BEST IS YET POSTER";
        var quantity = 1;
        var initialBasket = new Basket();

        driver.get(UrlProvider.SIGN_IN);
        at(SigningPage.class)
                .login(registredUser.getEmail(), registredUser.getPassword())
                .goHomePage();
        at(HomePage.class)
                .openProductPage(productName);
        at(ProductPage.class)
                .addToBasket(quantity,initialBasket);
        at(ProductPopUpPage.class)
                .proceedToCheckout();
        at(CartPage.class)
                .proceedToCheckout();
   at(CheckoutPage.class)
           .changeBillingAddress("posag", "WArsawa", "12345","Idaho" );
        Thread.sleep(4000);

    }
}
