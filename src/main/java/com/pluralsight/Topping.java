package com.pluralsight;

import com.pluralsight.enums.*;

public class Topping {
    private Meat meat;
    private Cheese cheese;
    private Regular regular;
    private Sauce sauce;
    private double price;

    public Topping(Meat meat) {
        this.meat = meat;
        updatePrice();
    }

    public Topping(Cheese cheese) {
        this.cheese = cheese;
        updatePrice();
    }

    public Topping(Regular regular) {
        this.regular = regular;
        updatePrice();
    }

    public Topping(Sauce sauce) {
        this.sauce = sauce;
        updatePrice();
    }

    private void updatePrice() {
        if (meat != null) {
            price = meat.getPrice();
        } else if (cheese != null) {
            price = cheese.getPrice();
        } else if (regular != null) {
            price = regular.getPrice();
        } else if (sauce != null) {
            price = sauce.getPrice();
        }
    }

    public void updatePriceBySize(BreadSize size) {
        if (meat != null) {
            meat.setPriceBySize(size);
            price = meat.getPrice();
        } else if (cheese != null) {
            cheese.setPriceBySize(size);
            price = cheese.getPrice();
        }
        // Regular and Sauce prices don't change with size
    }

    public double getPrice() {
        return price;
    }

    public boolean isMeat() {
        return meat != null;
    }

    public boolean isCheese() {
        return cheese != null;
    }

    public boolean isRegular() {
        return regular != null;
    }

    public boolean isSauce() {
        return sauce != null;
    }
}
