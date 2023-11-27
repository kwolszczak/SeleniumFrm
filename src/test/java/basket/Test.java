package basket;

import base.BaseTest;
import pl.kwolszczak.models.Basket;
import pl.kwolszczak.models.UserFactory;
import pl.kwolszczak.pages.product.ProductPage;

public class Test extends BaseTest {

    @org.junit.jupiter.api.Test
    void test() throws InterruptedException {
        driver.get("http://146.59.32.4/index.php?id_product=1&id_product_attribute=1&rewrite=hummingbird-printed-t-shirt&controller=product&id_lang=2#/1-size-s/8-color-white");
        Basket basket = new Basket();
        var x=at(ProductPage.class)
                .addToBasket(1, basket);
        Thread.sleep(5000);
        var xy=at(ProductPage.class)
                .addToBasket(1, basket);
        System.out.println("total price in basket: "+basket.getTotalPrice());
        System.out.println(basket.getProducts().get(0).getQuantity());
        System.out.println(basket.getProducts().get(0).getTotalPrice());
        System.out.println(basket.getProducts().get(0).getProduct().getName());
        System.out.println(basket.getProducts().get(0).getProduct().getPrice());
        System.out.println(basket.getProducts().get(1).getQuantity());
        System.out.println(basket.getProducts().get(1).getTotalPrice());
        System.out.println(basket.getProducts().get(1).getProduct().getName());
        System.out.println(basket.getProducts().get(1).getProduct().getPrice());
      //  System.out.println("price of one product: "+x.getPrice());

        Thread.sleep(3000);

        var us1 = UserFactory.getAlreadyRegistredUser();
        var us2 = UserFactory.getRandomUser();
        System.out.println(us1);
        System.out.println(us2);
    }
}
