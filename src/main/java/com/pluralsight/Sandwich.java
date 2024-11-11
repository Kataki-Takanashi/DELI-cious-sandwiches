package com.pluralsight;

import java.util.ArrayList;
import java.util.List;

public class Sandwich {
    Bread bread;
    List<Topping> toppings;
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

    public Sandwich(Bread bread, List<Topping> toppings, boolean isToasted) {
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

    public List<Topping> getToppings() {
        return toppings;
    }

    public void setToppings(List<Topping> toppings) {
        this.toppings = toppings;
    }

    public void addTopping(Topping topping) {
        if (toppings == null) {
            toppings = new ArrayList<>();
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
