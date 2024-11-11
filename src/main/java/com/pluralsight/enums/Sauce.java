package com.pluralsight.enums;

public enum Sauce {
    MAYO(0.0),
    MUSTARD(0.0),
    KETCHUP(0.0),
    RANCH(0.0),
    THOUSAND_ISLANDS(0.0),
    VINAIGRETTE(0.0);

    private double price;

    Sauce(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }
}
