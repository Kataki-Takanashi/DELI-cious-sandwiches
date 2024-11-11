import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import com.pluralsight.*;
import com.pluralsight.enums.*;

public class TestSandwichPricing {
    private Order order;
    private Sandwich sandwich;
    private Bread bread;

    @BeforeEach
    void setUp() {
        order = new Order();
        sandwich = new Sandwich();
        bread = new Bread();
    }

    @Test
    void testBasicSandwichPrice() {
        bread.setBreadType(BreadType.WHITE);
        bread.setBreadSize(BreadSize.FOUR_INCH);
        sandwich.setBread(bread);
        order.addSandwich(sandwich);
        assertEquals(5.50, order.getTotalPrice(), 0.01);
    }

    @Test
    void testSandwichWithMeatPrice() {
        bread.setBreadType(BreadType.WHEAT);
        bread.setBreadSize(BreadSize.EIGHT_INCH);
        sandwich.setBread(bread);
        sandwich.addTopping(new Topping(Meat.STEAK, false));
        order.addSandwich(sandwich);
        assertEquals(9.00, order.getTotalPrice(), 0.01); // 7.00 bread + 2.00 steak
    }

    @Test
    void testExtraMeatPrice() {
        bread.setBreadType(BreadType.RYE);
        bread.setBreadSize(BreadSize.TWELVE_INCH);
        sandwich.setBread(bread);
        sandwich.addTopping(new Topping(Meat.CHICKEN, true));
        order.addSandwich(sandwich);
        assertEquals(11.50, order.getTotalPrice(), 0.01); // 8.50 bread + 3.00 extra chicken
    }

    @Test
    void testMultipleRegularToppings() {
        bread.setBreadType(BreadType.WRAP);
        bread.setBreadSize(BreadSize.FOUR_INCH);
        sandwich.setBread(bread);
        sandwich.addTopping(new Topping(Regular.LETTUCE));
        sandwich.addTopping(new Topping(Regular.TOMATOES));
        sandwich.addTopping(new Topping(Regular.ONIONS));
        order.addSandwich(sandwich);
        assertEquals(5.50, order.getTotalPrice(), 0.01); // Regular toppings are free
    }

    @Test
    void testComplexSandwich() {
        bread.setBreadType(BreadType.WHITE);
        bread.setBreadSize(BreadSize.EIGHT_INCH);
        sandwich.setBread(bread);
        
        // Add meats
        sandwich.addTopping(new Topping(Meat.STEAK, false));
        sandwich.addTopping(new Topping(Meat.BACON, true));
        
        // Add cheese
        sandwich.addTopping(new Topping(Cheese.AMERICAN, false));
        
        // Add regular toppings
        sandwich.addTopping(new Topping(Regular.LETTUCE));
        sandwich.addTopping(new Topping(Regular.TOMATOES));
        
        // Add sauce
        sandwich.addTopping(new Topping(Sauce.MAYO));
        
        order.addSandwich(sandwich);
        assertEquals(12.50, order.getTotalPrice(), 0.01); // 7.00 bread + 2.00 steak + 2.00 extra bacon + 1.50 cheese
    }

    @Test
    void testDrinksAndChips() {
        bread.setBreadType(BreadType.WHITE);
        bread.setBreadSize(BreadSize.FOUR_INCH);
        sandwich.setBread(bread);
        order.addSandwich(sandwich);
        order.addDrinks(1);
        order.addChips(1);
        assertEquals(9.50, order.getTotalPrice(), 0.01); // 5.50 sandwich + 2.50 drink + 1.50 chips
    }
}
