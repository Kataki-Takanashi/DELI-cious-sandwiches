package com.pluralsight;

import java.util.ArrayList;
import java.util.List;

import com.pluralsight.utils.Console;

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
                    topping.updatePriceBySize(sandwich.getBread().getBreadSize());
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

    public String generateOrderSummary() {
        StringBuilder summary = new StringBuilder();
        summary.append("\n====== Order Summary ======\n");
        summary.append("Order #").append(orderNumber).append("\n\n");
        
        // Add Sandwiches
        summary.append("Sandwiches:\n");
        if (sandwiches.isEmpty()) {
            summary.append("No sandwiches ordered\n");
        } else {
            for (int i = 0; i < sandwiches.size(); i++) {
                Sandwich sandwich = sandwiches.get(i);
                summary.append(String.format("%d. %s bread (%d\") %s\n", 
                    i + 1,
                    sandwich.getBread().getBreadType().toString(),
                    sandwich.getBread().getBreadSize().getInches(),
                    sandwich.isToasted() ? "(Toasted)" : ""
                ));
                
                // Add toppings
                if (sandwich.getToppings() != null && !sandwich.getToppings().isEmpty()) {
                    summary.append("   Toppings: ");
                    sandwich.getToppings().forEach(topping -> {
                        if (topping.isMeat()) summary.append(topping.getMeat()).append(", ");
                        if (topping.isCheese()) summary.append(topping.getCheese()).append(", ");
                        if (topping.isRegular()) summary.append(topping.getRegular()).append(", ");
                        if (topping.isSauce()) summary.append(topping.getSauce()).append(", ");
                    });
                    summary.setLength(summary.length() - 2); // Gets rid of the comma at the end
                    summary.append("\n");
                }
            }
        }
        
        // Add Drinks and Chips
        if (drinks > 0) {
            summary.append(String.format("\nDrinks: %d ($%.2f each)\n", drinks, DRINK_PRICE));
        }
        if (chips > 0) {
            summary.append(String.format("Chips: %d ($%.2f each)\n", chips, CHIPS_PRICE));
        }
        
        // Total
        summary.append(String.format("\nTotal Price: $%.2f\n", totalPrice));
        
        return summary.toString();
    }

    public boolean checkout() {
        // Display order summary
        System.out.println(generateOrderSummary());
        
        // Process payment
        System.out.println("\n====== Payment ======");
        if (processPayment()) {
            OrderFileManager.saveOrder(this); // Save the order receipt
            System.out.println("\nThank you for your order!");
            System.out.println("Your order number is: " + orderNumber);
            
            // Wait for user to press Enter
            Console.PromptForString("Press Enter to continue...");
            
            return true;
        } else {
            System.out.println("\nPayment failed. Order cancelled.");
            
            // Wait for user to press Enter
            Console.PromptForString("Press Enter to continue...");
            
            return false;
        }
    }

    private boolean processPayment() {
        return true; // Future implementation
    }
}
