package com.pluralsight;

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
                        order.addDrinks(1);
                        break;
                    case 3:
                        // Add Chips
                        order.addChips(1);
                        break;
                    case 4:
                        // Checkout
                        break;
                    case 5:
                        System.out.println("Canceling Order...");
                        return;
                    case 0:
                        System.out.println("Exiting...");
                        System.exit(0);
                    default:
                        System.out.println("Please enter a valid number.");
                }
            }
            catch (IllegalArgumentException e) {
                System.out.println("Please enter a valid number.");
            }
        } while (userSelection != 0);
    }

    private static String formatSandwichSummary(Order order) {
        if (order.getSandwiches().isEmpty()) {
            return "[none]";
        }
        
        if (order.getSandwiches().size() == 1) {
            Sandwich sandwich = order.getSandwiches().get(0);
            StringBuilder summary = new StringBuilder();
            
            // Check if sandwich is incomplete
            if (sandwich.getBread() == null || 
                sandwich.getBread().getBreadType() == null || 
                sandwich.getBread().getBreadSize() == null) {
                return "[incomplete sandwich]";
            }
            
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
        // Check if there's at least one sandwich with bread and size before allowing checkout
        boolean canCheckout = false;
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

        // Add validation message if sandwich is incomplete
        String sandwichSummary = formatSandwichSummary(order);

        String drinkCount = order.getDrinks() > 0
                ? String.format("[%d drink%s ($%.2f/drink)]", 
                    order.getDrinks(), 
                    order.getDrinks() > 1 ? "s" : "",
                    Order.DRINK_PRICE)
                : String.format("[none ($%.2f/drink)]", Order.DRINK_PRICE);
                
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
                \t4. Checkout %s
                \t5. Cancel Order
                \t0. Exit
                Enter choice:\s""".formatted(sandwichSummary, drinkCount, chipCount, totalPrice);
        
        String selection;

        do {
            selection = Console.PromptForString(menu);
        } while (selection.isEmpty());

        if (selection.trim().equals("4") && !canCheckout) {
            throw new IllegalArgumentException("Cannot checkout without a complete sandwich! Please add bread and size first.");
        }

        return switch (selection.trim().toUpperCase()) {
            case "1" -> 1;
            case "2" -> 2;
            case "3" -> 3;
            case "4" -> 4;
            case "5", "EXIT", "E", "Q", "QUIT" -> 5;
            case "0" -> 0;
            default -> throw new IllegalArgumentException("Invalid selection: " + selection);
        };
    }
}
