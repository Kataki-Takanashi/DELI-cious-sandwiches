package com.pluralsight;

import com.pluralsight.utils.Console;

public class SandwichBuilder {
    public static void sandwichScreen() {
        int userSelection = 0;

        do {
            try {
                userSelection = displaySandwichMenu();
                switch (userSelection) {
                    case 1:
                        // Select bread
                        break;
                    case 2:
                        // Select size
                        break;
                    case 3:
                        // Toggle toasted option
                        break;
                    case 4:
                        // Complete sandwich
                        break;
                    case 0:
                        System.out.println("Canceling Sandwich Creation...");
                        break;
                }
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        } while (userSelection != 0 && userSelection != 4);
    }

    private static int displaySandwichMenu() throws IllegalArgumentException {
        String menu = """

                Build Your Sandwich
                Please select from the following choices:
                \t1. Select Bread
                \t2. Choose Size
                \t3. Toasting Options
                \t4. Complete Sandwich
                \t0. Cancel
                Enter choice:\s""";
        String selection = Console.PromptForString(menu);
        return switch (selection.trim()) {
            case "1" -> 1;
            case "2" -> 2;
            case "3" -> 3;
            case "4" -> 4;
            case "0", "EXIT", "E", "Q", "QUIT" -> 0;
            default -> throw new IllegalArgumentException("Invalid selection: " + selection);
        };
    }
}
