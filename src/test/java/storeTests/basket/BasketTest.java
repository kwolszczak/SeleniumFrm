package storeTests.basket;

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
import pl.kwolszczak.pages.checkout.OrderConfirmationPage;
import pl.kwolszczak.pages.history.HistoryOrderDetailsPage;
import pl.kwolszczak.pages.history.HistoryOrderPage;
import pl.kwolszczak.pages.home.HomePage;
import pl.kwolszczak.pages.product.ProductPage;
import pl.kwolszczak.providers.UrlProvider;
import provider.TestDataProvider;
import storeTests.base.BaseTest;

class BasketTest extends BaseTest {

    @RepeatedTest(3)
    @DisplayName("Generic - add product to basket")
    void addProductToBasket_generic() {

        int numberOfRepetitions = 10;
        int quantityOfProduct = random.nextInt(1, 5);
        Basket basket = new Basket();

        for (int i = 0; i < numberOfRepetitions; i++) {
            var randomProductName = at(HomePage.class)
                    .getRandomProduct()
                    .getName();

            at(HomePage.class)
                    .openProductPage(randomProductName);
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
    void removeProduct() {

        int numberOfRepetitions = 2;
        int quantityOfProduct = 1;
        String emptyBasketInfo = "There are no more items in your cart";

        Basket basket = new Basket();
        Product[] randomProducts = new Product[numberOfRepetitions];
        for (int i = 0; i < numberOfRepetitions; i++) {
            var randomProduct = at(HomePage.class).getRandomProduct();
            var randomProductName = randomProduct.getName();

            at(HomePage.class)
                    .openProductPage(randomProductName);
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

        var newTotalPrice = at(CartPage.class)
                .removeProduct(productOne.getName(), basket)
                .getTotalPrice();
        Assertions.assertThat(newTotalPrice).isEqualTo(basket.getTotalPrice());

        var zeroPrice = at(CartPage.class)
                .removeProduct(productTwo.getName(), basket)
                .getTotalPrice();
        Assertions.assertThat(zeroPrice)
                .isEqualTo(basket.getTotalPrice())
                .isEqualTo(0.00);

        var actualBasketInfo = at(CartPage.class)
                .getInfoAboutEmptyBasket();
        Assertions.assertThat(actualBasketInfo).isEqualTo(emptyBasketInfo);
    }

    @RepeatedTest(1)
    @DisplayName("Checkout test")
    void checkoutTest() {
        var registredUser = UserFactory.getAlreadyRegistredUser();
        var orderPaymentStatus = System.getProperty("checkoutTest-orderPaymentStatus");
        var productName = System.getProperty("checkoutTest-productName");
        var quantity = Integer.parseInt(System.getProperty("checkoutTest-quantity"));
        var initialBasket = new Basket();
        var order = TestDataProvider.getTestOrder();

        System.out.println(" >>>> START TEST >>>>");

        driver.get(UrlProvider.SIGN_IN);
        at(SigningPage.class)
                .login(registredUser.getEmail(), registredUser.getPassword())
                .goHomePage();
        at(HomePage.class)
                .openProductPage(productName);
        at(ProductPage.class)
                .addToBasket(quantity, initialBasket);
        at(ProductPopUpPage.class)
                .proceedToCheckout();
        at(CartPage.class)
                .proceedToCheckout();
        at(CheckoutPage.class)
                .changeBillingAddress(
                        order.getBillingAddress().getAddress(),
                        order.getBillingAddress().getCity(),
                        order.getBillingAddress().getZipcode(),
                        order.getBillingAddress().getState()
                )
                .acceptShippingMethod()
                .placeOrder();

        String orderNumber = at(OrderConfirmationPage.class)
                .getOrderNumber();
        Double totalPrice = at(OrderConfirmationPage.class)
                .getOrderPrice();
        order.setOrderNumber(orderNumber);
        order.setPrice(totalPrice);

        driver.get(UrlProvider.ORDER_HISTORY);
        at(HistoryOrderPage.class)
                .openOrderDetails(orderNumber);
        var actualOrder = at(HistoryOrderDetailsPage.class)
                .toOrderModel();
        var actualOrderPaymentStatus = at(HistoryOrderDetailsPage.class)
                .getPaymentStatus();

        Assertions.assertThat(actualOrderPaymentStatus)
                .isEqualTo(orderPaymentStatus);
        Assertions.assertThat(actualOrder).usingRecursiveComparison()
                .isEqualTo(order);

    }
}
