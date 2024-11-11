package com.pluralsight;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private List<Sandwich> sandwiches;
    private int drinks;
    private int chips;
    private double totalPrice;
    private static int orderCounter = 0;
    private final int orderNumber;

    public static final double DRINK_PRICE = 2.50;
    public static final double CHIPS_PRICE = 1.50;

    public Order() {
        this.sandwiches = new ArrayList<>();
        this.drinks = 0;
        this.chips = 0;
        this.totalPrice = 0.0;
        this.orderNumber = ++orderCounter; // Adds one to the order counter that is statically set its not an instance variable
    }

    public void addSandwich(Sandwich sandwich) {
        sandwiches.add(sandwich);
        updateTotalPrice();
    }

    public void removeSandwich(int index) {
        if (index >= 0 && index < sandwiches.size()) {
            sandwiches.remove(index);
            updateTotalPrice();
        }
    }

    public List<Sandwich> getSandwiches() {
        return new ArrayList<>(sandwiches);
    }

    public int getDrinks() {
        return drinks;
    }

    public void addDrinks(int drinks) {
        this.drinks++;
        updateTotalPrice();
    }

    public int getChips() {
        return chips;
    }

    public void addChips(int chips) {
        this.chips++;
        updateTotalPrice();
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    private void updateTotalPrice() {
        double total = 0.0;
        
        // Add sandwich prices
        for (Sandwich sandwich : sandwiches) {
            // Add bread price
            if (sandwich.getBread() != null) {
                total += sandwich.getBread().getPrice();
            }
            
            // Add topping prices
            if (sandwich.getToppings() != null) {
                for (Topping topping : sandwich.getToppings()) {
                    total += topping.getPrice();
                }
            }
        }
        
        // Add drink price (if selected)
        total += DRINK_PRICE * drinks;
        
        // Add chips price (if selected)
        total += CHIPS_PRICE * chips;
        
        this.totalPrice = total;
    }
}
