package pl.kwolszczak.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Order {
    private String orderDate;
    private double price;
    private String orderNumber;
    private OrderAddress deliveryAddress;
    private OrderAddress billingAddress;

    public Order() {
    }

    public Order(String orderNumber, String orderDate, double price) {
        this.orderNumber = orderNumber;
        this.orderDate = orderDate;
        this.price = price;
    }
}
