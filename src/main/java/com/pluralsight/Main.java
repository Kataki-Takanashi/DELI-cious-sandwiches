package com.pluralsight;
import com.pluralsight.service.OrderUserInterface;
import com.pluralsight.utils.Console;

import javafx.application.Application;

public class Main {
    public static void main(String[] args) {
        int userSelection = 1000; // because it cant start at 0

        // Main Menu Loop
        do {
            try {
                userSelection = displayMainMenu();
                switch (userSelection) {
                    case 1:
                        // New Order
                        OrderUserInterface.orderScreen();
                        break;
                    case 999:
                        Application.launch(AppMain.class, args);
                        break; // TODO: Quick Order. (Quick Order shows the last 5 unique orders)
                    case 0: // Possibly allow for users to make their own named orders or "presets"
                        System.out.println("Exiting Shop...");
                        break;
                }
            }
            catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        } while (userSelection != 0);
    }

    private static int displayMainMenu() throws IllegalArgumentException { // TODO: Add Order History, Menu
        String menu = """

                Welcome to the Ali's DELI-cious Sandwich Shop!
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
            case "G" -> 999;
            case "0", "EXIT", "E", "Q", "QUIT" -> 0;
            default -> throw new IllegalArgumentException("Invalid selection: " + selection);
        };
    }
}