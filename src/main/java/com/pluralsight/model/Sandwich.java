package com.pluralsight.model;

import java.util.HashSet;
import java.util.Set;

public class Sandwich {
    private Bread bread;
    private Set<Topping> toppings; // This is a set to avoid duplicate toppings
    private boolean isToasted;

    /**
     * Default values:
     * - bread: null
     * - toppings: null 
     * - isToasted: false
     */
    public Sandwich() {
        this(null, null, false);
    }

    public Sandwich(Bread bread, Set<Topping> toppings, boolean isToasted) {
        this.bread = bread;
        this.toppings = toppings;
        this.isToasted = isToasted;
    }

    public Bread getBread() {
        return bread;
    }

    public void setBread(Bread bread) {
        this.bread = bread;
    }

    public Set<Topping> getToppings() {
        return toppings;
    }

    public void setToppings(Set<Topping> toppings) {
        this.toppings = toppings;
    }

    public void addTopping(Topping topping) {
        if (toppings == null) {
            toppings = new HashSet<>();
        }
        toppings.add(topping);
    }

    public boolean isToasted() {
        return isToasted;
    }

    public void setIsToasted(boolean isToasted) {
        this.isToasted = isToasted;
    }

    public double getPrice() {
        double price = 0.0;

        // Add bread price if bread exists and has valid size
        if (bread != null && bread.getBreadSize() != null) {
            price += bread.getPrice();
        }

        // Add topping prices
        if (toppings != null) {
            for (Topping topping : toppings) {
                topping.updatePriceBySize(bread.getBreadSize());
                price += topping.getPrice();
            }
        }

        return price;
    }
}
