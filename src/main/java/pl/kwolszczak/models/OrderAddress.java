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
}
