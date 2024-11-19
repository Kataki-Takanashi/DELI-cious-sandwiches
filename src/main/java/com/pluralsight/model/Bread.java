package com.pluralsight.model;

import com.pluralsight.enums.BreadSize;
import com.pluralsight.enums.BreadType;

public class Bread {
    private BreadType breadType;
    private BreadSize breadSize;
    private double price;

    public Bread() {
        this(null, null);
    }

    public Bread(BreadType breadType, BreadSize breadSize) {
        this.breadType = breadType;
        this.breadSize = breadSize;
        updatePrice();
    }

    public BreadType getBreadType() {
        return breadType;
    }

    public BreadSize getBreadSize() {
        return breadSize;
    }

    public void setBreadType(BreadType breadType) {
        this.breadType = breadType;
    }

    public void setBreadSize(BreadSize breadSize) {
        this.breadSize = breadSize;
        updatePrice();
    }

    private void updatePrice() {
        if (breadSize == null) {
            this.price = 0.0;
            return;
        }
        
        this.price = switch (breadSize) {
            case FOUR_INCH -> 5.50;
            case EIGHT_INCH -> 7.00;
            case TWELVE_INCH -> 8.50;
        };
    }

    public double getPrice() {
        return price;
    }
}
