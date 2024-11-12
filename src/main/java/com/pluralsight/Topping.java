package com.pluralsight;

import com.pluralsight.enums.*;
import java.util.Objects;

public class Topping {
    private Meat meat;
    private Cheese cheese;
    private Regular regular;
    private Sauce sauce;
    private boolean isExtra;
    private double price;

    public Topping(Meat meat, boolean isExtra) {
        this.meat = meat;
        this.isExtra = isExtra;
        updatePrice();
    }

    public Topping(Cheese cheese, boolean isExtra) {
        this.cheese = cheese;
        this.isExtra = isExtra;
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

    public boolean isExtra() {
        return isExtra;
    }

    public Meat getMeat() {
        return meat;
    }

    public Cheese getCheese() {
        return cheese;
    }

    public Regular getRegular() {
        return regular;
    }

    public Sauce getSauce() {
        return sauce;
    }

    @Override
    public String toString() {
        if (meat != null) {
            return isExtra ? "EXTRA " + meat.name() : meat.name();
        } else if (cheese != null) {
            return isExtra ? "EXTRA " + cheese.name() : cheese.name();
        } else if (regular != null) {
            return regular.name();
        } else if (sauce != null) {
            return sauce.name();
        }
        return "Unknown Topping"; // TODO: Consider throwing an exception instead
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Topping topping = (Topping) o;
        return isExtra == topping.isExtra && 
               Objects.equals(meat, topping.meat) && 
               Objects.equals(cheese, topping.cheese) && 
               Objects.equals(regular, topping.regular) && 
               Objects.equals(sauce, topping.sauce);
    }

    @Override
    public int hashCode() {
        return Objects.hash(meat, cheese, regular, sauce, isExtra);
    }
}
