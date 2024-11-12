package com.pluralsight;

import com.pluralsight.enums.*;

public class SpecialsManager {
    
    public static Sandwich createBLT() {
        Sandwich sandwich = new Sandwich();
        
        // Set bread
        Bread bread = new Bread();
        bread.setBreadType(BreadType.WHITE);
        bread.setBreadSize(BreadSize.EIGHT_INCH);
        sandwich.setBread(bread);
        
        // Add toppings
        sandwich.addTopping(new Topping(Meat.BACON, false));
        sandwich.addTopping(new Topping(Cheese.CHEDDAR, false));
        sandwich.addTopping(new Topping(Regular.LETTUCE));
        sandwich.addTopping(new Topping(Regular.TOMATOES));
        sandwich.addTopping(new Topping(Sauce.RANCH));
        
        sandwich.setIsToasted(true);
        
        return sandwich;
    }
    
    public static Sandwich createPhillyCheesesteak() {
        Sandwich sandwich = new Sandwich();
        
        // Set bread
        Bread bread = new Bread();
        bread.setBreadType(BreadType.WHITE);
        bread.setBreadSize(BreadSize.EIGHT_INCH);
        sandwich.setBread(bread);
        
        // Add toppings
        sandwich.addTopping(new Topping(Meat.STEAK, false));
        sandwich.addTopping(new Topping(Cheese.AMERICAN, false));
        sandwich.addTopping(new Topping(Regular.PEPPERS));
        sandwich.addTopping(new Topping(Sauce.MAYO));
        
        sandwich.setIsToasted(true);
        
        return sandwich;
    }
}
