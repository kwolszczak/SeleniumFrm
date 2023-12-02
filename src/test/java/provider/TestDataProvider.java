package provider;

import pl.kwolszczak.models.Order;
import pl.kwolszczak.models.OrderAddress;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class TestDataProvider {

    public static Order getTestOrder() {
        OrderAddress billingAddress = new OrderAddress();
        billingAddress.setAddress(System.getProperty("checkoutTest-billingAddress.address"));
        billingAddress.setCity(System.getProperty("checkoutTest-billingAddress.city"));
        billingAddress.setZipcode(System.getProperty("checkoutTest-billingAddress.zipcode"));
        billingAddress.setState(System.getProperty("checkoutTest-billingAddress.state"));

        OrderAddress deliveryAddress = new OrderAddress();
        deliveryAddress.setAddress(System.getProperty("checkoutTest-deliveryAddress.address"));
        deliveryAddress.setCity(System.getProperty("checkoutTest-deliveryAddress.city"));
        deliveryAddress.setZipcode(System.getProperty("checkoutTest-deliveryAddress.zipcode"));
        deliveryAddress.setState(System.getProperty("checkoutTest-deliveryAddress.state"));

        var today = LocalDate.now();
        var todayStr = today.format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
        var order = new Order();
        order.setBillingAddress(billingAddress);
        order.setDeliveryAddress(deliveryAddress);
        order.setOrderDate(todayStr);
        return order;
    }
}
