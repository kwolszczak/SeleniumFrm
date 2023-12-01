package pl.kwolszczak.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Order {
    private String orderDate;
    private String price;
    private String orderNumber;
    private OrderAddress deliveryAddress;
    private OrderAddress billingAddress;
}
