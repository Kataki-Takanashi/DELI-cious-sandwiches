package com.pluralsight.enums;

public enum Cheese {
    AMERICAN(0.75),
    PROVOLONE(0.75),
    CHEDDAR(0.75),
    SWISS(0.75);

    private double price;

    Cheese(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public void setPriceBySize(BreadSize size) {
        switch (size) {
            case FOUR_INCH -> this.price = 0.75;
            case EIGHT_INCH -> this.price = 1.50;
            case TWELVE_INCH -> this.price = 2.25;
        }
    }
}
