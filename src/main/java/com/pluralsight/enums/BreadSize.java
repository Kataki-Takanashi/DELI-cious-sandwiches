package com.pluralsight.enums;

public enum BreadSize {
    FOUR_INCH(4.00),
    EIGHT_INCH(6.00),
    TWELVE_INCH(8.00);

    private double price;

    BreadSize(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public int getInches() {
        return switch (this) {
            case FOUR_INCH -> 4;
            case EIGHT_INCH -> 8;
            case TWELVE_INCH -> 12;
        };
    }
}
