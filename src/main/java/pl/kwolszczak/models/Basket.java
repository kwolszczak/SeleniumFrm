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
        int index = products.indexOf(basketLine);
        if (index != -1) {
            products.get(index).increaseQuantity(basketLine.getQuantity());
        } else {
            products.add(basketLine);
        }
        updateTotalPrice();
    }

    private void updateTotalPrice() {
        totalPrice = products.stream().mapToDouble(BasketLine::getTotalPrice).sum();
        totalPrice = Math.round(totalPrice * 100.0) / 100.0;
    }

    public void removeBasketLine(BasketLine basketLine) {
        if (products.contains(basketLine)) {
            products.remove(basketLine);
            updateTotalPrice();
        }
    }

    public List<BasketLine> getProducts() {
        return products;
    }
}
