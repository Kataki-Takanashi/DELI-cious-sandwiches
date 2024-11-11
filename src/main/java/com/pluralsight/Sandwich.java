package com.pluralsight;

import java.util.HashSet;
import java.util.Set;

public class Sandwich {
    Bread bread;
    Set<Topping> toppings; // This is a set to avoid duplicate toppings
    boolean isToasted;

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
}
