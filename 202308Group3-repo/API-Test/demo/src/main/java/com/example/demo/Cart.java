package com.example.demo;
import java.util.ArrayList;
import java.util.List;
import com.example.demo.rest.Deal;

public class Cart {

    private List<Deal> items;

    public Cart() {
        this.items = new ArrayList<>();
    }

    public List<Deal> getItems() {
        return items;
    }

    public void addItem(Deal item) {
        items.add(item);
    }

    public void removeItem(Deal item) {
        items.remove(item);
    }

    public void clearCart() {
        items.clear();
    }

    public double calculateTotal() {
        return items.stream()
                .mapToDouble(item -> Double.parseDouble(item.getNormalPrice().replaceAll("[^0-9.]", "")))
                .sum();
    }
}
