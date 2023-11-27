package pl.kwolszczak.models;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
public class BasketLine implements Comparable<BasketLine>{
    private Product product;
    private double totalPrice;
    private int quantity;

    public BasketLine(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
        updateTotalPrice();
    }

    public void increaseQuantity(int quantity){
      this.quantity += quantity;
      updateTotalPrice();
    }

    public void updateTotalPrice(){
        this.totalPrice= this.quantity * product.getPrice();
    }


    @Override
    public int compareTo(BasketLine o) {
        return this.product.compareTo(o.product);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BasketLine that = (BasketLine) o;
        return Objects.equals(product, that.product);
    }

    @Override
    public int hashCode() {
        return Objects.hash(product);
    }
}
