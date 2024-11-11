package com.pluralsight.enums;

public enum BreadType {
    WHITE,
    WHEAT,
    RYE,
    WRAP;

    public String toString() {
        return name().toLowerCase(); // The name() method returns the name of the enum constant
    }
}
