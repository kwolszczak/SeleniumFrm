package basket;

import base.BaseTest;
import pl.kwolszczak.models.Basket;
import pl.kwolszczak.models.UserFactory;
import pl.kwolszczak.pages.basket.ProductPopUpPage;
import pl.kwolszczak.pages.home.HomePage;
import pl.kwolszczak.pages.product.ProductPage;
import pl.kwolszczak.providers.UrlProvider;

import java.util.Random;

public class Test extends BaseTest {

    @org.junit.jupiter.api.Test
    void test() throws InterruptedException {
        int quantityOfProduct = new Random().nextInt(1,5);
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
        Basket actualBasket = new Basket();


        System.out.println("total price in basket: "+basket.getTotalPrice());
        for (var p: basket.getProducts()) {
            System.out.println("--------------");
            System.out.println("product name: "+p.getProduct().getName());
            System.out.println("total product price: "+p.getTotalPrice());
            System.out.println("quantity: "+ p.getQuantity());
            System.out.println(" price: "+p.getProduct().getPrice());
            System.out.println("--------------");
        }

        Thread.sleep(5000);


        var us1 = UserFactory.getAlreadyRegistredUser();
        var us2 = UserFactory.getRandomUser();
        System.out.println(us1);
        System.out.println(us2);
    }
}
