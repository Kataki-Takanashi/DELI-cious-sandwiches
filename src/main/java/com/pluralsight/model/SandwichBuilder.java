package com.pluralsight.model;

import com.pluralsight.enums.BreadSize;
import com.pluralsight.enums.BreadType;
import com.pluralsight.enums.Cheese;
import com.pluralsight.enums.Meat;
import com.pluralsight.enums.Regular;
import com.pluralsight.enums.Sauce;
import com.pluralsight.utils.Console;
import java.util.stream.Collectors;

public class SandwichBuilder {
    private Sandwich sandwich = new Sandwich();
    Bread bread = new Bread();

    public Sandwich sandwichScreen() {
        int userSelection = 0;

        do {
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
                        // Check for bread and size before allowing toppings
                        if (sandwich.getBread() == null || 
                            sandwich.getBread().getBreadType() == null || 
                            sandwich.getBread().getBreadSize() == null) {
                            System.out.println("Please select bread and size before adding toppings!");
                        } else {
                            selectToppings();
                        }
                        break;
                    case 4:
                        // Toggle toasted option
                        sandwich.setIsToasted(!sandwich.isToasted());
                        break;
                    case 5:
                        // Check for minimum requirements before allowing completion
                        if (sandwich.getBread() == null || 
                            sandwich.getBread().getBreadType() == null || 
                            sandwich.getBread().getBreadSize() == null) {
                            System.out.println("Please select bread and size before completing your sandwich!");
                            userSelection = -1; // Force stay in menu
                        } else {
                            return sandwich;
                        }
                        break;
                    case 0:
                        System.out.println("Canceling Sandwich Creation...");
                        return null;
                }
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        } while (userSelection != 0);
        return null;
    }

    private int displaySandwichMenu() throws IllegalArgumentException {
        String breadType = (sandwich.getBread() != null && sandwich.getBread().getBreadType() != null) 
                ? "[" + sandwich.getBread().getBreadType().toString() + "]" 
                : "[not selected]";

        String breadSize = (sandwich.getBread() != null && sandwich.getBread().getBreadSize() != null)
                ? "[" + sandwich.getBread().getBreadSize().getInches() + " inch]"
                : "[not selected]";

        String sandwichToppings = (sandwich.getToppings() != null && sandwich.getToppings().size() > 0)
                ? sandwich.getToppings().toString().replaceAll("_", " ") // Get rid of underscores like in "ROAST_BEEF"
                : "[not selected]";

        String toastedStatus = sandwich.isToasted() 
                ? "[Toasted]" 
                : "[Not Toasted]";

        String menu = """

                Build Your Sandwich
                Please select from the following choices:
                \t1. Select Bread %s
                \t2. Choose Size %s
                \t3. Select Toppings %s
                \t4. Toggle Toasted %s
                \t5. Complete Sandwich
                \t0. Cancel
                Enter choice:\s""".formatted(breadType, breadSize, sandwichToppings, toastedStatus); // using formatted bc console.PromptForString() needs a string
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

    private void selectToppings() {
        int selection;
        do {
            // Get current selections for each category
            String meatSelections = sandwich.getToppings() != null && sandwich.getToppings().stream().anyMatch(Topping::isMeat)
                ? sandwich.getToppings().stream()
                    .filter(Topping::isMeat)
                    .map(t -> t.toString().replaceAll("[\\[\\]]", ""))
                    .collect(Collectors.joining(", "))
                : "not selected";

            String cheeseSelections = sandwich.getToppings() != null && sandwich.getToppings().stream().anyMatch(Topping::isCheese)
                ? sandwich.getToppings().stream()
                    .filter(Topping::isCheese)
                    .map(t -> t.toString().replaceAll("[\\[\\]]", ""))
                    .collect(Collectors.joining(", "))
                : "not selected";

            String regularSelections = sandwich.getToppings() != null && sandwich.getToppings().stream().anyMatch(Topping::isRegular)
                ? sandwich.getToppings().stream()
                    .filter(Topping::isRegular)
                    .map(t -> t.toString().replaceAll("[\\[\\]]", ""))
                    .collect(Collectors.joining(", "))
                : "not selected";

            String sauceSelections = sandwich.getToppings() != null && sandwich.getToppings().stream().anyMatch(Topping::isSauce)
                ? sandwich.getToppings().stream()
                    .filter(Topping::isSauce)
                    .map(t -> t.toString().replaceAll("[\\[\\]]", ""))
                    .collect(Collectors.joining(", "))
                : "not selected";

            String menu = """
                    
                    Select Topping Category
                    \t1. Meats [%s]
                    \t2. Cheese [%s]
                    \t3. Regular Toppings [%s]
                    \t4. Sauces [%s]
                    \t0. Done
                    Enter choice:\s""".formatted(
                        meatSelections,
                        cheeseSelections,
                        regularSelections,
                        sauceSelections);
                    
            selection = Integer.parseInt(Console.PromptForString(menu).trim());
            
            switch (selection) {
                case 1 -> selectMeats();
                case 2 -> selectCheese();
                case 3 -> selectRegularToppings();
                case 4 -> selectSauces();
                case 0 -> System.out.println("Finishing toppings selection...");
                default -> throw new IllegalArgumentException("Invalid selection: " + selection);
            }
        } while (selection != 0);
    }

    private void selectMeats() {
        boolean selecting = true;
        double extraPrice = switch (sandwich.getBread().getBreadSize()) {
            case FOUR_INCH -> 0.5;
            case EIGHT_INCH -> 1.0;
            case TWELVE_INCH -> 1.5;
        };

        // Get current meat selections
        String currentMeats = sandwich.getToppings() != null 
            ? sandwich.getToppings().stream()
                .filter(Topping::isMeat)
                .map(Object::toString)
                .collect(Collectors.joining(", "))
            : "[none]";

        while (selecting) {
            String menu = String.format("""
                    
                    Select Meats (Multiple Selections Allowed) %s
                    Regular:
                    \t1. Steak      ($%.2f)
                    \t2. Ham        ($%.2f)
                    \t3. Salami     ($%.2f)
                    \t4. Roast Beef ($%.2f)
                    \t5. Chicken    ($%.2f)
                    \t6. Bacon      ($%.2f)
                    Extra Portion:
                    \t7. Extra Steak      (+$%.2f)
                    \t8. Extra Ham        (+$%.2f)
                    \t9. Extra Salami     (+$%.2f)
                    \t10. Extra Roast Beef (+$%.2f)
                    \t11. Extra Chicken    (+$%.2f)
                    \t12. Extra Bacon      (+$%.2f)
                    \t0. Done
                    Enter choice:\s""",
                    currentMeats.equals("[none]") ? "" : "[Selected: " + currentMeats + "]",
                    Meat.STEAK.getPrice(),
                    Meat.HAM.getPrice(),
                    Meat.SALAMI.getPrice(),
                    Meat.ROAST_BEEF.getPrice(),
                    Meat.CHICKEN.getPrice(),
                    Meat.BACON.getPrice(),
                    extraPrice,
                    extraPrice,
                    extraPrice,
                    extraPrice,
                    extraPrice,
                    extraPrice
                );
            
            String selection = Console.PromptForString(menu).trim();
            
            switch (selection.toUpperCase()) {
                // Regular portions
                case "1", "STEAK" -> sandwich.addTopping(new Topping(Meat.STEAK, false  ));
                case "2", "HAM" -> sandwich.addTopping(new Topping(Meat.HAM, false));
                case "3", "SALAMI" -> sandwich.addTopping(new Topping(Meat.SALAMI, false));
                case "4", "ROAST BEEF" -> sandwich.addTopping(new Topping(Meat.ROAST_BEEF, false));
                case "5", "CHICKEN" -> sandwich.addTopping(new Topping(Meat.CHICKEN, false));
                case "6", "BACON" -> sandwich.addTopping(new Topping(Meat.BACON, false));
                // Extra portions
                case "7", "EXTRA STEAK" -> sandwich.addTopping(new Topping(Meat.STEAK, true));
                case "8", "EXTRA HAM" -> sandwich.addTopping(new Topping(Meat.HAM, true));
                case "9", "EXTRA SALAMI" -> sandwich.addTopping(new Topping(Meat.SALAMI, true));
                case "10", "EXTRA ROAST BEEF" -> sandwich.addTopping(new Topping(Meat.ROAST_BEEF, true));
                case "11", "EXTRA CHICKEN" -> sandwich.addTopping(new Topping(Meat.CHICKEN, true));
                case "12", "EXTRA BACON" -> sandwich.addTopping(new Topping(Meat.BACON, true));
                case "0", "DONE" -> selecting = false;
                default -> System.out.println("Invalid selection: " + selection);
            }
        }
    }

    private void selectCheese() {
        boolean selecting = true;
        double extraPrice = switch (sandwich.getBread().getBreadSize()) {
            case FOUR_INCH -> 0.30;
            case EIGHT_INCH -> 0.60;
            case TWELVE_INCH -> 0.90;
        };

        while (selecting) {
            String menu = """
                    
                    Select Cheese (Multiple Selections Allowed)
                    Regular:
                    \t1. American    ($%.2f)
                    \t2. Provolone   ($%.2f)
                    \t3. Cheddar     ($%.2f)
                    \t4. Swiss       ($%.2f)
                    Extra Portion:
                    \t5. Extra American    (+$%.2f)
                    \t6. Extra Provolone   (+$%.2f)
                    \t7. Extra Cheddar     (+$%.2f)
                    \t8. Extra Swiss       (+$%.2f)
                    \t0. Done
                    Enter choice:\s""".formatted(
                        Cheese.AMERICAN.getPrice(),
                        Cheese.PROVOLONE.getPrice(),
                        Cheese.CHEDDAR.getPrice(),
                        Cheese.SWISS.getPrice(),
                        extraPrice,
                        extraPrice,
                        extraPrice,
                        extraPrice
                    );
            
            String selection = Console.PromptForString(menu).trim();
            
            switch (selection.toUpperCase()) {
                // Regular portions
                case "1", "AMERICAN" -> sandwich.addTopping(new Topping(Cheese.AMERICAN, false));
                case "2", "PROVOLONE" -> sandwich.addTopping(new Topping(Cheese.PROVOLONE, false));
                case "3", "CHEDDAR" -> sandwich.addTopping(new Topping(Cheese.CHEDDAR, false));
                case "4", "SWISS" -> sandwich.addTopping(new Topping(Cheese.SWISS, false));
                // Extra portions
                case "5", "EXTRA AMERICAN" -> sandwich.addTopping(new Topping(Cheese.AMERICAN, true));
                case "6", "EXTRA PROVOLONE" -> sandwich.addTopping(new Topping(Cheese.PROVOLONE, true));
                case "7", "EXTRA CHEDDAR" -> sandwich.addTopping(new Topping(Cheese.CHEDDAR, true));
                case "8", "EXTRA SWISS" -> sandwich.addTopping(new Topping(Cheese.SWISS, true));
                case "0", "DONE" -> selecting = false;
                default -> System.out.println("Invalid selection: " + selection);
            }
        }
    }

    private void selectRegularToppings() {
        boolean selecting = true;
        while (selecting) {
            String menu = """
                    
                    Select Regular Toppings (Multiple Selections Allowed)
                    \t1. Lettuce    (Included)
                    \t2. Peppers    (Included)
                    \t3. Onions     (Included)
                    \t4. Tomatoes   (Included)
                    \t5. Jalapeños  (Included)
                    \t6. Cucumbers  (Included)
                    \t7. Pickles    (Included)
                    \t8. Guacamole  (Included)
                    \t9. Mushrooms  (Included)
                    \t0. Done
                    Enter choice:\s""";
            
            String selection = Console.PromptForString(menu).trim();
            
            switch (selection.toUpperCase()) {
                case "1", "LETTUCE" -> sandwich.addTopping(new Topping(Regular.LETTUCE));
                case "2", "PEPPERS" -> sandwich.addTopping(new Topping(Regular.PEPPERS));
                case "3", "ONIONS" -> sandwich.addTopping(new Topping(Regular.ONIONS));
                case "4", "TOMATOES" -> sandwich.addTopping(new Topping(Regular.TOMATOES));
                case "5", "JALAPENOS" -> sandwich.addTopping(new Topping(Regular.JALAPENOS));
                case "6", "CUCUMBERS" -> sandwich.addTopping(new Topping(Regular.CUCUMBERS));
                case "7", "PICKLES" -> sandwich.addTopping(new Topping(Regular.PICKLES));
                case "8", "GUACAMOLE" -> sandwich.addTopping(new Topping(Regular.GUACAMOLE));
                case "9", "MUSHROOMS" -> sandwich.addTopping(new Topping(Regular.MUSHROOMS));
                case "0", "DONE" -> selecting = false;
                default -> System.out.println("Invalid selection: " + selection);
            }
        }
    }

    private void selectSauces() {
        boolean selecting = true;
        while (selecting) {
            String menu = """
                    
                    Select Sauces (Multiple Selections Allowed)
                    \t1. Mayo              (Included)
                    \t2. Mustard           (Included)
                    \t3. Ketchup           (Included)
                    \t4. Ranch             (Included)
                    \t5. Thousand Islands  (Included)
                    \t6. Vinaigrette       (Included)
                    \t0. Done
                    Enter choice:\s""";
            
            String selection = Console.PromptForString(menu).trim();
            
            switch (selection.toUpperCase()) {
                case "1", "MAYO" -> sandwich.addTopping(new Topping(Sauce.MAYO));
                case "2", "MUSTARD" -> sandwich.addTopping(new Topping(Sauce.MUSTARD));
                case "3", "KETCHUP" -> sandwich.addTopping(new Topping(Sauce.KETCHUP));
                case "4", "RANCH" -> sandwich.addTopping(new Topping(Sauce.RANCH));
                case "5", "THOUSAND ISLANDS" -> sandwich.addTopping(new Topping(Sauce.THOUSAND_ISLANDS));
                case "6", "VINAIGRETTE" -> sandwich.addTopping(new Topping(Sauce.VINAIGRETTE));
                case "0", "DONE" -> selecting = false;
                default -> System.out.println("Invalid selection: " + selection);
            }
        }
    }

    public Sandwich completeSandwich() {
        // Check if required fields are set
        if (sandwich.getBread() == null || 
            sandwich.getBread().getBreadType() == null || 
            sandwich.getBread().getBreadSize() == null) {
            System.out.println("Cannot complete sandwich without bread and size selected!");
            return null;
        }
        return sandwich;
    }

}
