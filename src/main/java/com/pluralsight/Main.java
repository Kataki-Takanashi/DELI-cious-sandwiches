package com.pluralsight;
import com.pluralsight.utils.Console;

public class Main {
    public static void main(String[] args) {
        int userSelection = 0;

        // Main Menu Loop
        do {
            try {
                userSelection = displayMainMenu();
                switch (userSelection) {
                    case 1:
                        // New Order
                        OrderUserInterface.orderScreen();
                        break;
                    case 2:
                        // GUI
                        break;
                    case 0:
                        System.out.println("Exiting Shop...");
                        break;
                }
            }
            catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        } while (userSelection != 0);
    }

    private static int displayMainMenu() throws IllegalArgumentException {
        String menu = """

                Welcome to the Shop!
                Please select from the following choices:
                \t1. New Order
                \t0. Exit
                Enter choice:\s""";
        String selection;

        do {
            selection = Console.PromptForString(menu);
        } while (selection.isEmpty());

        return switch (selection.trim().toUpperCase()) {
            case "1" -> 1;
            case "2" -> 2;
            case "0", "EXIT", "E", "Q", "QUIT" -> 0;
            default -> throw new IllegalArgumentException("Invalid selection: " + selection);
        };
    }
}