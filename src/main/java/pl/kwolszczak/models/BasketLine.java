package pl.kwolszczak.models;

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


    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public int compareTo(BasketLine o) {
        return this.product.compareTo(o.product);
    }
}
