package com.pluralsight.enums;

public enum DrinkSize {
    SMALL(1.50),
    MEDIUM(2.00),
    LARGE(2.50);

    private double price;

    DrinkSize(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }
}
