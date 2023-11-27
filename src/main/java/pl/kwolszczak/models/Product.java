package pl.kwolszczak.models;


import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class Product implements Comparable<Product> {
    private String name;
    private String size;
    private String color;
    private int price;


    @Override
    public int compareTo(Product o) {
        return (this.name+this.price).compareTo(o.name+o.price);
    }
}
