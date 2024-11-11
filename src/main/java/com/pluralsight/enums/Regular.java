package com.pluralsight.enums;

public enum Regular {
    LETTUCE(0.0),
    PEPPERS(0.0),
    ONIONS(0.0),
    TOMATOES(0.0),
    JALAPENOS(0.0),
    CUCUMBERS(0.0),
    PICKLES(0.0),
    GUACAMOLE(0.0),
    MUSHROOMS(0.0);

    private double price;

    Regular(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }
}
