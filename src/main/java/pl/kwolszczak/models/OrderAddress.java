package pl.kwolszczak.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderAddress {
    private String address;
    private String city;
    private String state;
    private String zipcode;

    public OrderAddress() {
    }

    public OrderAddress(String address, String city, String state, String zipcode) {
        this.address = address;
        this.city = city;
        this.state = state;
        this.zipcode = zipcode;
    }
}
