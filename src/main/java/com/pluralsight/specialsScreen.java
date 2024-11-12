package com.pluralsight;

import com.pluralsight.utils.Console;

public class specialsScreen {
    
    public static void display(Order order) {
        int userSelection = 0;

        do {
            try {
                userSelection = displaySpecialsMenu(order);
                switch (userSelection) {
                    case 1:
                        order.addSandwich(SpecialsManager.createBLT());
                        System.out.println("BLT added to order!");
                        return;
                    case 2:
                        order.addSandwich(SpecialsManager.createPhillyCheesesteak());
                        System.out.println("Philly Cheesesteak added to order!");
                        return;
                    case 0:
                        System.out.println("Returning to main menu...");
                        return;
                }
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        } while (userSelection != 0);
    }

    private static int displaySpecialsMenu(Order order) throws IllegalArgumentException {
        // Get descriptions and prices using temporary orders
        Order tempOrder = new Order();
        
        // Calculate BLT price
        tempOrder.addSandwich(SpecialsManager.createBLT());
        double bltPrice = tempOrder.getTotalPrice();
        String bltDesc = formatSandwichDescription(SpecialsManager.createBLT());
        
        // Calculate Philly price
        tempOrder = new Order();
        tempOrder.addSandwich(SpecialsManager.createPhillyCheesesteak());
        double phillyPrice = tempOrder.getTotalPrice();
        String phillyDesc = formatSandwichDescription(SpecialsManager.createPhillyCheesesteak());

        String menu = String.format("""
                
                Signature Sandwiches
                Please select from the following choices:
                \t1. BLT ($%.2f)
                   %s
                \t2. Philly Cheesesteak ($%.2f)
                   %s
                \t0. Back to Main Menu
                Enter choice:\s""",
                bltPrice,
                bltDesc,
                phillyPrice,
                phillyDesc
        );

        String selection = Console.PromptForString(menu);

        return switch (selection.trim().toUpperCase()) {
            case "1", "BLT" -> 1;
            case "2", "PHILLY", "CHEESESTEAK" -> 2;
            case "0", "BACK" -> 0;
            default -> throw new IllegalArgumentException("Invalid selection: " + selection);
        };
    }

    private static String formatSandwichDescription(Sandwich sandwich) {
        StringBuilder desc = new StringBuilder("   ");
        
        // Add toppings description
        if (sandwich.getToppings() != null && !sandwich.getToppings().isEmpty()) {
            sandwich.getToppings().forEach(topping -> {
                if (topping.isMeat()) desc.append(topping.getMeat()).append(", ");
                if (topping.isCheese()) desc.append(topping.getCheese()).append(", ");
                if (topping.isRegular()) desc.append(topping.getRegular()).append(", ");
                if (topping.isSauce()) desc.append(topping.getSauce()).append(", ");
            });
            // Remove last comma and space
            desc.setLength(desc.length() - 2);
        }
        
        return desc.toString();
    }
}
