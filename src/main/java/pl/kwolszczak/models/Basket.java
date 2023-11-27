package pl.kwolszczak.models;

import java.util.ArrayList;
import java.util.List;

public class Basket {
    private List<BasketLine> products;
    private double totalPrice;

    public Basket() {
        this.products = new ArrayList<>();
        this.totalPrice = 0;
    }

    public Basket(List<BasketLine> products) {
        this.products = new ArrayList<>(products);
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void addBasketLine(BasketLine basketLine) {
        //jezeli bl jest to update quantity ???? dodac comparable
        int index;
        if (( index=products.indexOf(basketLine)) != -1){
            products.get(index).increaseQuantity(basketLine.getQuantity());
           // return products.get(index);
        }else {
            //jezeli nie ma to dodaj
            products.add(basketLine);

            //update ceny

            // return basketLine;
        }totalPrice += basketLine.getTotalPrice();
    }


}
