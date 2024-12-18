package com.pluralsight.service;

import com.pluralsight.enums.DrinkSize;
import com.pluralsight.model.Order;
import com.pluralsight.model.Sandwich;
import com.pluralsight.model.SandwichBuilder;
import com.pluralsight.ui.specialsScreen;
import com.pluralsight.utils.Console;
import java.util.List;
import java.util.stream.Collectors;

public class OrderUserInterface {
    public static void orderScreen() {
        int userSelection = 0;
        Order order = new Order();

        // Main Menu Loop
        do {
            try {
                userSelection = displayMainMenu(order);
                switch (userSelection) {
                    case 1:
                        // Add Sandwich
                        Sandwich sandwich = new SandwichBuilder().sandwichScreen();
                        if (sandwich != null) {
                            order.addSandwich(sandwich);
                        }
                        break;
                    case 2:
                        // Add Drink
                        addDrink(order);
                        break;
                    case 3:
                        // Add Chips
                        order.addChips(1);
                        break;
                    case 4:
                        // Specials
                        specialsScreen.display(order);
                        break;
                    case 5:
                        // Checkout
                        order.checkout();
                        order = new Order(); // Resets the order
                        break;
                    case 6:
                        System.out.println("Canceling Order...");
                        return;
                    default:
                        System.out.println("Please enter a valid number.");
                }
            }
            catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        } while (userSelection != 6);
    }

    private static String formatSandwichSummary(Order order) {
        if (order.getSandwiches().isEmpty()) {
            return "[none]";
        }
        
        if (order.getSandwiches().size() == 1) {
            Sandwich sandwich = order.getSandwiches().get(0);
            StringBuilder summary = new StringBuilder();
            
            // Add bread size and type
            summary.append("[")
                   .append(sandwich.getBread().getBreadSize().getInches())
                   .append("\" ")
                   .append(sandwich.getBread().getBreadType());
            
            // Get toppings
            if (sandwich.getToppings() != null && !sandwich.getToppings().isEmpty()) {
                summary.append(" with ");
                
                // Convert toppings to a readable list
                List<String> toppingNames = sandwich.getToppings().stream()
                    .<String>map(topping -> {
                        if (topping.isMeat()) {
                            String name = topping.getMeat().name().toLowerCase().replace('_', ' ');
                            return topping.isExtra() ? "extra " + name : name;
                        }
                        if (topping.isCheese()) {
                            String name = topping.getCheese().name().toLowerCase().replace('_', ' ');
                            return topping.isExtra() ? "extra " + name : name;
                        }
                        if (topping.isRegular()) return topping.getRegular().name().toLowerCase().replace('_', ' ');
                        if (topping.isSauce()) return topping.getSauce().name().toLowerCase().replace('_', ' ');
                        return "";
                    })
                    .filter(name -> !name.isEmpty())
                    .collect(Collectors.toList());
                
                // Format the list nicely with commas and "and"
                for (int i = 0; i < toppingNames.size(); i++) {
                    if (i > 0) {
                        if (i == toppingNames.size() - 1) {
                            summary.append(toppingNames.size() > 2 ? ", and " : " and ");
                        } else {
                            summary.append(", ");
                        }
                    }
                    summary.append(toppingNames.get(i));
                }
            }
            
            // Add toasted status if it is toasted
            if (sandwich.isToasted()) {
                summary.append(" (toasted)");
            }
            
            summary.append("]");
            return summary.toString();
        }
        
        // Multiple sandwiches - just show the count
        return "[" + order.getSandwiches().size() + " sandwiches]";
    }

    private static int displayMainMenu(Order order) throws IllegalArgumentException {
        // Check if there's at least one sandwich with bread and size set before allowing checkout
        boolean canCheckout = false; // TODO: Remove, make sure its not needed
        if (!order.getSandwiches().isEmpty()) {
            for (Sandwich sandwich : order.getSandwiches()) {
                if (sandwich.getBread() != null && 
                    sandwich.getBread().getBreadType() != null && 
                    sandwich.getBread().getBreadSize() != null) {
                    canCheckout = true;
                    break;
                }
            }
        }

        // These are the summaries that are displayed in the main menu, they help the user see what they have in thir order and the pricex
        String sandwichSummary = formatSandwichSummary(order);

        String drinkCount = order.getDrinks().values().stream().mapToInt(Integer::intValue).sum() > 0
                ? String.format("[%d drink%s ($%.2f total)]", 
                    order.getDrinks().values().stream().mapToInt(Integer::intValue).sum(),
                    order.getDrinks().values().stream().mapToInt(Integer::intValue).sum() > 1 ? "s" : "",
                    order.getDrinks().entrySet().stream()
                        .mapToDouble(entry -> entry.getKey().getPrice() * entry.getValue())
                        .sum())
                : "[none]";
                
        String chipCount = order.getChips() > 0
                ? String.format("[%d bag%s ($%.2f/bag)]", 
                    order.getChips(), 
                    order.getChips() > 1 ? "s" : "",
                    Order.CHIPS_PRICE)
                : String.format("[none ($%.2f/bag)]", Order.CHIPS_PRICE);

        String totalPrice = String.format("[Total: $%.2f]", order.getTotalPrice());

        String menu = """
                
                Order Screen
                Please select from the following choices:
                \t1. Add Sandwich %s
                \t2. Add Drink %s
                \t3. Add Chips %s
                \t4. Add Specialty Sandwich
                \t5. Checkout %s
                \t6. Cancel Order
                Enter choice:\s""".formatted(sandwichSummary, drinkCount, chipCount, totalPrice);
        
        String selection;

        do {
            selection = Console.PromptForString(menu);
        } while (selection.isEmpty());

        if (selection.trim().equals("5") && order.getTotalPrice() == 0) {
            throw new IllegalArgumentException("Cannot checkout without any items! Please add at least one item to your order.");
        }

        return switch (selection.trim().toUpperCase()) {
            case "1" -> 1;
            case "2" -> 2;
            case "3" -> 3;
            case "4" -> 4;
            case "5" -> 5;
            case "6", "EXIT", "E", "Q", "QUIT" -> 6;
            default -> throw new IllegalArgumentException("Invalid selection: " + selection);
        };
    }

    private static void addDrink(Order order) {
        String menu = """
                
                Select Drink Size
                \t1. Small  ($%.2f)
                \t2. Medium ($%.2f)
                \t3. Large  ($%.2f)
                \t0. Cancel
                Enter choice:\s""".formatted(
                DrinkSize.SMALL.getPrice(),
                DrinkSize.MEDIUM.getPrice(),
                DrinkSize.LARGE.getPrice());

        while (true) {
            String selection = Console.PromptForString(menu);
            switch (selection.trim().toUpperCase()) {
                case "1", "S", "SMALL":
                    order.addDrink(DrinkSize.SMALL);
                    System.out.println("Small drink added!");
                    return;
                case "2", "M", "MEDIUM":
                    order.addDrink(DrinkSize.MEDIUM);
                    System.out.println("Medium drink added!");
                    return;
                case "3", "L", "LARGE":
                    order.addDrink(DrinkSize.LARGE);
                    System.out.println("Large drink added!");
                    return;
                case "0", "CANCEL":
                    System.out.println("Drink selection cancelled.");
                    return;
                default:
                    System.out.println("Invalid selection. Please try again.");
            }
        }
    }
}
