package com.pluralsight;

import com.pluralsight.enums.BreadSize;
import com.pluralsight.enums.BreadType;
import com.pluralsight.utils.Console;

public class SandwichBuilder {
    private Sandwich sandwich = new Sandwich();

    public void sandwichScreen() {
        int userSelection = 0;

        do {
            sandwich = new Sandwich();
            Bread bread = new Bread();
            try {
                userSelection = displaySandwichMenu();
                switch (userSelection) {
                    case 1:
                        // Select bread
                        selectBread(bread);
                        break;
                    case 2:
                        // Select size
                        selectBreadSize(bread);
                        break;
                    case 3:
                        // Select toppings
                        break;
                    case 4:
                        // Toggle toasted option
                        break;
                    case 5:
                        // Complete sandwich
                        break;
                    case 0:
                        System.out.println("Canceling Sandwich Creation...");
                        break;
                }
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        } while (userSelection != 0 && userSelection != 5);
    }

    private static int displaySandwichMenu() throws IllegalArgumentException {
        String menu = """
                Build Your Sandwich
                Please select from the following choices:
                \t1. Select Bread
                \t2. Choose Size
                \t3. Select Toppings
                \t4. Toasting Options
                \t5. Complete Sandwich
                \t0. Cancel
                Enter choice:\s""";
        String selection = Console.PromptForString(menu);
        return switch (selection.trim()) {
            case "1" -> 1;
            case "2" -> 2;
            case "3" -> 3;
            case "4" -> 4;
            case "5" -> 5;
            case "0", "EXIT", "E", "Q", "QUIT" -> 0;
            default -> throw new IllegalArgumentException("Invalid selection: " + selection);
        };
    }

    private BreadType selectBread(Bread bread) {

        String menu = """

                Select Bread Type
                \t1. White
                \t2. Wheat
                \t3. Rye
                \t4. Wrap
                Enter choice:\s""";
        String selection = Console.PromptForString(menu);
        switch (selection.trim().toUpperCase()) {
            case "1", "WHITE" -> bread.setBreadType(BreadType.WHITE);
            case "2", "WHEAT" -> bread.setBreadType(BreadType.WHEAT);
            case "3", "RYE" -> bread.setBreadType(BreadType.RYE);
            case "4", "WRAP" -> bread.setBreadType(BreadType.WRAP);
            default -> throw new IllegalArgumentException("Invalid bread selection: " + selection);
        }
        sandwich.setBread(bread);
        return bread.getBreadType();
    }

    private BreadSize selectBreadSize(Bread bread) {
        String menu = """

                Select Bread Size
                \t1. 4 inch
                \t2. 8 inch
                \t3. 12 inch
                Enter choice:\s""";
        String selection = Console.PromptForString(menu);
        switch (selection.trim().toUpperCase()) {
            case "1", "4 INCH" -> bread.setBreadSize(BreadSize.FOUR_INCH);
            case "2", "8 INCH" -> bread.setBreadSize(BreadSize.EIGHT_INCH);
            case "3", "12 INCH" -> bread.setBreadSize(BreadSize.TWELVE_INCH);
            default -> throw new IllegalArgumentException("Invalid bread size selection: " + selection);
        }
        sandwich.setBread(bread);
        return bread.getBreadSize();
    }

}
