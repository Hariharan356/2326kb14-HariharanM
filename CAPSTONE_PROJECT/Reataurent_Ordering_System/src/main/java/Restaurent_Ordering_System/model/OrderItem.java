package main.java.Restaurent_Ordering_System.model;

public class OrderItem {
    private final MenuItem item;
    private int quantity;

    public OrderItem(MenuItem item, int quantity) {
        if (quantity <= 0)
            throw new IllegalArgumentException("Quantity must be > 0");
        this.item = item;
        this.quantity = quantity;
    }

    public MenuItem getItem() {
        return item;
    }

    public int getQuantity() {
        return quantity;
    }

    public void addQuantity(int q) {
        this.quantity += q;
    }

    public double getSubtotal() {
        return quantity * item.getPrice();
    }

    @Override
    public String toString() {
        return String.format("%-20s x%-2d ₹%.2f", item.getName(), quantity, getSubtotal());
    }
}
