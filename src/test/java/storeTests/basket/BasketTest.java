package storeTests.basket;

import storeTests.base.BaseTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import pl.kwolszczak.models.*;
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

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class BasketTest extends BaseTest {

    @RepeatedTest(3)
    @DisplayName("Generic - add product to basket")
    void addProductToBasket_generic() {

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
    void removeProduct() {

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
    void checkoutTest() throws InterruptedException {
        var orderPaymentStatus = "Awaiting check payment";
        var registredUser = UserFactory.getAlreadyRegistredUser();
        var productName = "THE BEST IS YET POSTER";
        var quantity = 1;
        var initialBasket = new Basket();
        OrderAddress billingAddress = new OrderAddress();
        billingAddress.setAddress("Kasprzaka");
        billingAddress.setCity("Warsaw");
        billingAddress.setZipcode("12345");
        billingAddress.setState("Idaho");

        OrderAddress deliveryAddress = new OrderAddress();
        deliveryAddress.setAddress("6th Avenue");
        deliveryAddress.setCity("New York");
        deliveryAddress.setZipcode("10019");
        deliveryAddress.setState("New York");

        Order order = new Order();
        order.setBillingAddress(billingAddress);
        order.setDeliveryAddress(deliveryAddress);

        LocalDate today = LocalDate.now();
        String todayStr = today.format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
        order.setOrderDate(todayStr);

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
                .changeBillingAddress(billingAddress.getAddress(), billingAddress.getCity(), billingAddress.getZipcode(), billingAddress.getState())
                .acceptShippingMethod()
                .placeOrder();

        String orderNumber = at(OrderConfirmationPage.class)
                .getOrderNumber();
        var totalPrice = at(OrderConfirmationPage.class)
                .getOrderPrice();

        order.setOrderNumber(orderNumber);
        order.setPrice(totalPrice);

        driver.get(UrlProvider.ORDER_HISTORY);
        at(HistoryOrderPage.class)
                .openOrderDetails(orderNumber);
        var orderDetails = at(HistoryOrderDetailsPage.class);


        Assertions.assertThat(orderDetails.getPayment())
                .isEqualTo(orderPaymentStatus);
        Assertions.assertThat(orderDetails.getPrice())
                .isEqualTo(order.getPrice());
        Assertions.assertThat(orderDetails.getDate())
                .isEqualTo(order.getOrderDate());
        Assertions.assertThat(orderDetails.getDeliveryAddress())
                .contains(deliveryAddress.getAddress())
                .contains(deliveryAddress.getCity())
                .contains(deliveryAddress.getState())
                .contains(deliveryAddress.getZipcode());
        Assertions.assertThat(orderDetails.getInvoiceAddress())
                .contains(billingAddress.getAddress())
                .contains(billingAddress.getCity())
                .contains(billingAddress.getState())
                .contains(billingAddress.getZipcode());

    }
}
