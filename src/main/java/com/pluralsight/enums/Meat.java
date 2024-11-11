package com.pluralsight.enums;

public enum Meat {
    STEAK(2.00),
    HAM(1.00),
    SALAMI(1.00),
    ROAST_BEEF(1.00),
    CHICKEN(1.00),
    BACON(1.00);

    private double price;

    Meat(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public void setPriceBySize(BreadSize size) {
        switch (size) {
            case FOUR_INCH -> this.price = 1.00;
            case EIGHT_INCH -> this.price = 2.00;
            case TWELVE_INCH -> this.price = 3.00;
        }
    }
}
