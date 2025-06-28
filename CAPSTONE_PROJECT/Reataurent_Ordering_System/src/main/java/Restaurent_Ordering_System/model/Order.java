package main.java.Restaurent_Ordering_System.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Order {
    private final List<OrderItem> items = new ArrayList<>();
    private double total = 0;
    private boolean cancelled = false;
    private LocalDateTime orderTime;

    public void addItem(MenuItem item, int quantity) {
        if (orderTime == null) {
            orderTime = LocalDateTime.now(); // Set when first item is added
        }

        for (OrderItem oi : items) {
            if (oi.getItem().getId() == item.getId()) {
                oi.addQuantity(quantity);
                recalculateTotal();
                return;
            }
        }

        items.add(new OrderItem(item, quantity));
        recalculateTotal();
    }

    public boolean cancelOrder() {
        if (!items.isEmpty()) {
            items.clear();
            total = 0;
            cancelled = true;
            return true;
        }
        return false;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public double getTotal() {
        return total;
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    public boolean isCancelled() {
        return cancelled;
    }

    public void clear() {
        items.clear();
        total = 0;
        cancelled = false;
        orderTime = null;
    }

    public void printBill() {
        System.out.println("\n------- BILL -------");
        for (OrderItem oi : items) {
            System.out.printf("%s x%d = ₹%.2f%n",
                    oi.getItem().getName(), oi.getQuantity(), oi.getSubtotal());
        }
        System.out.println("---------------------");
        System.out.printf("Total: ₹%.2f%n", total);
    }

    private void recalculateTotal() {
        total = 0;
        for (OrderItem oi : items) {
            total += oi.getSubtotal();
        }
    }

    public LocalDateTime getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(LocalDateTime orderTime) {
        this.orderTime = orderTime;
    }
}
