package com.pluralsight;

import com.pluralsight.utils.Console;

public class OrderUserInterface {
    public static void orderScreen() {
        int userSelection = 0;

        // Main Menu Loop
        do {
            try {
                userSelection = displayMainMenu();
                switch (userSelection) {
                    case 1:
                        // Add Sandwich
                        SandwichBuilder.sandwichScreen();
                        break;
                    case 2:
                        // Add Drink
                        break;
                    case 3:
                        // Add Chips
                        break;
                    case 4:
                        // Checkout
                        break;
                    case 0:
                        System.out.println("Canceling Order...");
                        break;
                }
            }
            catch (IllegalArgumentException e) {
                System.out.println("Please enter a valid number.");
            }
        } while (userSelection != 0);
    }

    private static int displayMainMenu() throws IllegalArgumentException {
        String menu = """
                Order Screen
                Please select from the following choices:
                \t1. Add Sandwich
                \t2. Add Drink
                \t3. Add Chips
                \t4. Checkout
                \t0. Cancel Order
                Enter choice:\s""";
        String selection;

        do {
            selection = Console.PromptForString(menu);
        } while (selection.isEmpty());

        return switch (selection.trim().toUpperCase()) {
            case "1" -> 1;
            case "2" -> 2;
            case "3" -> 3;
            case "4" -> 4;
            case "0", "EXIT", "E", "Q", "QUIT" -> 0;
            default -> throw new IllegalArgumentException("Invalid selection: " + selection);
        };
    }
}
