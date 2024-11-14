package com.pluralsight.utils.gui;
// Imports

import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import com.pluralsight.Bread;
import com.pluralsight.Order;
import com.pluralsight.Sandwich;
import com.pluralsight.SpecialsManager;
import com.pluralsight.Topping;
import com.pluralsight.enums.BreadSize;
import com.pluralsight.enums.BreadType;
import com.pluralsight.enums.Cheese;
import com.pluralsight.enums.DrinkSize;
import com.pluralsight.enums.Meat;
import com.pluralsight.enums.Regular;
import com.pluralsight.enums.Sauce;

public class Shop {
    
    @FXML
    private ImageView BreadsGlowLeftImage, BreadsGlowMiddleImage, BreadsGlowRightImage, BreadsGlowToastImage;
    @FXML
    private Button BreadsGlowLeft, BreadsGlowMiddle, BreadsGlowRight, BreadsGlowToast;
    @FXML
    private ImageView BreadsGlowWhiteImage, BreadsGlowWheatImage, BreadsGlowRyeImage, BreadsGlowWrapImage;
    @FXML
    private Button BreadsGlowWhite, BreadsGlowWheat, BreadsGlowRye, BreadsGlowWrap;

    @FXML
    private ImageView ChipsRed1Image, ChipsRed2Image, ChipsRed3Image, ChipsBlue1Image, ChipsBlue2Image, ChipsBlue3Image;
    @FXML
    private Button ChipsRed1, ChipsRed2, ChipsRed3, ChipsBlue1, ChipsBlue2, ChipsBlue3;

    @FXML
    private ImageView SaucesMustardImage, SaucesMayoImage, SaucesKetchupImage, SaucesRanchImage, SaucesThousandIslandImage, SaucesVinaigretteImage;
    @FXML
    private Button SaucesMustard, SaucesMayo, SaucesKetchup, SaucesRanch, SaucesThousandIsland, SaucesVinaigrette;

    @FXML
    private ImageView SodaSmallImage, SodaMediumImage, SodaLargeImage;
    @FXML
    private Button SodaSmall, SodaMedium, SodaLarge;

    @FXML
    private ImageView SpecialsBLTImage, SpecialsPhillyImage;
    @FXML
    private Button SpecialsBLT, SpecialsPhilly;

    @FXML
    private ImageView SteakImage, HamImage, SalamiImage, RoastBeefImage, ChickenImage, BaconImage, AmericanImage, ProvoloneImage, ChedderImage, SwissImage, LettuceImage, BellpepperImage, OnionImage, TomatoImage, JalapenoImage, CucumberImage, PickleImage, AvacadoImage, MushroomImage;
    @FXML
    private Button Steak, Ham, Salami, RoastBeef, Chicken, Bacon, American, Provolone, Chedder, Swiss, Lettuce, Bellpepper, Onion, Tomato, Jalapeno, Cucumber, Pickle, Avacado, Mushroom;

    @FXML
    private ImageView BellGlow;
    @FXML
    private Button Bell;

    @FXML
    private TextFlow Receipt;

    private Order order = new Order();
    private Bread bread = new Bread();
    private Sandwich sandwich = new Sandwich(bread, new HashSet<>(), false);

    @FXML
    private void selectBreadSmall(ActionEvent event) {
        bread.setBreadSize(BreadSize.FOUR_INCH);
        sandwich.getBread().setBreadSize(BreadSize.FOUR_INCH);
    }

    @FXML
    private void selectBreadMedium(ActionEvent event) {
        bread.setBreadSize(BreadSize.EIGHT_INCH);
        sandwich.getBread().setBreadSize(BreadSize.EIGHT_INCH);
    }

    @FXML
    private void selectBreadLarge(ActionEvent event) {
        bread.setBreadSize(BreadSize.TWELVE_INCH);
        sandwich.getBread().setBreadSize(BreadSize.TWELVE_INCH);
    }

    @FXML
    private void selectBreadToasted(ActionEvent event) {
        sandwich.setIsToasted(!sandwich.isToasted());
    }

    @FXML
    private void selectBreadWhite(ActionEvent event) {
        if (sandwich.getBread() != null) {
            bread.setBreadType(BreadType.WHITE);
            sandwich.getBread().setBreadType(BreadType.WHITE);
        }
    }

    @FXML
    private void selectBreadWheat(ActionEvent event) {
        if (sandwich.getBread() != null) {
            bread.setBreadType(BreadType.WHEAT);
            sandwich.getBread().setBreadType(BreadType.WHEAT);
        }
    }

    @FXML
    private void selectBreadRye(ActionEvent event) {
        if (sandwich.getBread() != null) {
            bread.setBreadType(BreadType.RYE);
            sandwich.getBread().setBreadType(BreadType.RYE);
        }
    }

    @FXML
    private void selectBreadWrap(ActionEvent event) {
        if (sandwich.getBread() != null) {
            bread.setBreadType(BreadType.WRAP);
            sandwich.getBread().setBreadType(BreadType.WRAP);
        }
    }

    @FXML
    private void addDrinkSmall(ActionEvent event) {
        order.addDrink(DrinkSize.SMALL);
        updateReceiptDisplay();
    }

    @FXML
    private void addDrinkMedium(ActionEvent event) {
        order.addDrink(DrinkSize.MEDIUM);
        updateReceiptDisplay();
    }

    @FXML
    private void addDrinkLarge(ActionEvent event) {
        order.addDrink(DrinkSize.LARGE);
        updateReceiptDisplay();
    }

    @FXML
    private void addChips(ActionEvent event) {
        order.addChips(1);
        updateReceiptDisplay();
    }

    @FXML
    private void addSaucesMustard(ActionEvent event) {
        if (sandwich.getBread() != null) {
            sandwich.addTopping(new Topping(Sauce.MUSTARD));
        }
    }

    @FXML
    private void addSaucesMayo(ActionEvent event) {
        if (sandwich.getBread() != null) {
            sandwich.addTopping(new Topping(Sauce.MAYO));
        }
    }

    @FXML
    private void addSaucesKetchup(ActionEvent event) {
        if (sandwich.getBread() != null) {
            sandwich.addTopping(new Topping(Sauce.KETCHUP));
        }
    }

    @FXML
    private void addSaucesRanch(ActionEvent event) {
        if (sandwich.getBread() != null) {
            sandwich.addTopping(new Topping(Sauce.RANCH));
        }
    }

    @FXML
    private void addSaucesThousandIsland(ActionEvent event) {
        if (sandwich.getBread() != null) {
            sandwich.addTopping(new Topping(Sauce.THOUSAND_ISLANDS));
        }
    }

    @FXML
    private void addSaucesVinaigrette(ActionEvent event) {
        if (sandwich.getBread() != null) {
            sandwich.addTopping(new Topping(Sauce.VINAIGRETTE));
        }
    }

    @FXML
    private void addSteak(ActionEvent event) {
        if (sandwich.getBread() != null) {
            sandwich.addTopping(new Topping(Meat.STEAK, false));
        }
    }

    @FXML
    private void addHam(ActionEvent event) {
        if (sandwich.getBread() != null) {
            sandwich.addTopping(new Topping(Meat.HAM, false));
        }
    }

    @FXML
    private void addSalami(ActionEvent event) {
        if (sandwich.getBread() != null) {
            sandwich.addTopping(new Topping(Meat.SALAMI, false));
        }
    }

    @FXML
    private void addRoastBeef(ActionEvent event) {
        if (sandwich.getBread() != null) {
            sandwich.addTopping(new Topping(Meat.ROAST_BEEF, false));
        }
    }

    @FXML
    private void addChicken(ActionEvent event) {
        if (sandwich.getBread() != null) {
            sandwich.addTopping(new Topping(Meat.CHICKEN, false));
        }
    }

    @FXML
    private void addBacon(ActionEvent event) {
        if (sandwich.getBread() != null) {
            sandwich.addTopping(new Topping(Meat.BACON, false));
        }
    }

    @FXML
    private void addAmerican(ActionEvent event) {
        if (sandwich.getBread() != null) {
            sandwich.addTopping(new Topping(Cheese.AMERICAN, false));
        }
    }

    @FXML
    private void addProvolone(ActionEvent event) {
        if (sandwich.getBread() != null) {
            sandwich.addTopping(new Topping(Cheese.PROVOLONE, false));
        }
    }

    @FXML
    private void addChedder(ActionEvent event) {
        if (sandwich.getBread() != null) {
            sandwich.addTopping(new Topping(Cheese.CHEDDAR, false));
        }
    }

    @FXML
    private void addSwiss(ActionEvent event) {
        if (sandwich.getBread() != null) {
            sandwich.addTopping(new Topping(Cheese.SWISS, false));
        }
    }

    @FXML
    private void addLettuce(ActionEvent event) {
        if (sandwich.getBread() != null) {
            sandwich.addTopping(new Topping(Regular.LETTUCE));
        }
    }

    @FXML
    private void addBellpepper(ActionEvent event) {
        if (sandwich.getBread() != null) {
            sandwich.addTopping(new Topping(Regular.PEPPERS));
        }
    }

    @FXML
    private void addOnion(ActionEvent event) {
        if (sandwich.getBread() != null) {
            sandwich.addTopping(new Topping(Regular.ONIONS));
        }
    }

    @FXML
    private void addTomato(ActionEvent event) {
        if (sandwich.getBread() != null) {
            sandwich.addTopping(new Topping(Regular.TOMATOES));
        }
    }

    @FXML
    private void addJalapeno(ActionEvent event) {
        if (sandwich.getBread() != null) {
            sandwich.addTopping(new Topping(Regular.JALAPENOS));
        }
    }

    @FXML
    private void addCucumber(ActionEvent event) {
        if (sandwich.getBread() != null) {
            sandwich.addTopping(new Topping(Regular.CUCUMBERS));
        }
    }

    @FXML
    private void addPickle(ActionEvent event) {
        if (sandwich.getBread() != null) {
            sandwich.addTopping(new Topping(Regular.PICKLES));
        }
    }

    @FXML
    private void addAvacado(ActionEvent event) {
        if (sandwich.getBread() != null) {
            sandwich.addTopping(new Topping(Regular.GUACAMOLE));
        }
    }

    @FXML
    private void addMushroom(ActionEvent event) {
        if (sandwich.getBread() != null) {
            sandwich.addTopping(new Topping(Regular.MUSHROOMS));
        }
    }

    @FXML
    private void addSpecialBLT(ActionEvent event) {
        order.addSandwich(SpecialsManager.createBLT());
        updateReceiptDisplay();
    }

    @FXML
    private void addSpecialPhilly(ActionEvent event) {
        order.addSandwich(SpecialsManager.createPhillyCheesesteak());
        updateReceiptDisplay();
    }

    private void setupGlowEffect(Button button, ImageView glowImage, ImageView... otherImages) { // This is how you can have an unknown number of arguments in a method
        button.setOnMouseEntered(e -> {
            glowImage.setOpacity(1);
            for (ImageView image : otherImages) {
                image.setOpacity(0);
            }
        });

        button.setOnMouseExited(e -> {
            glowImage.setOpacity(0);
            for (ImageView image : otherImages) {
                image.setOpacity(0);
            }
        });
    }

    
    @FXML
    private void handleBellClick(ActionEvent event) {
        if (sandwich.getBread() != null) {
            order.addSandwich(sandwich);
            updateReceiptDisplay();
            sandwich = new Sandwich();
            return;
        }
        updateReceiptDisplay();
    }

    @FXML
    private void checkout(ActionEvent event) {
        if (order.getTotalPrice() > 0) {
            order.checkoutGUI();
            // Reset for next Order
            order = new Order();
            sandwich = new Sandwich();
            bread = new Bread();
            updateReceiptDisplay();
        }
    }

    private void updateReceiptDisplay() {
        if (Receipt != null) {
            Receipt.getChildren().clear();
            Text receiptText = new Text(order.generateGUIOrderSummary());
            receiptText.setFill(Color.BLACK);
            Receipt.getChildren().add(receiptText);
        }
        else {
            Receipt.getChildren().clear();
        }
    }

    @FXML
    public void initialize() {
        setupGlowEffect(BreadsGlowLeft, BreadsGlowLeftImage, BreadsGlowMiddleImage, BreadsGlowRightImage, BreadsGlowToastImage);
        setupGlowEffect(BreadsGlowMiddle, BreadsGlowMiddleImage, BreadsGlowLeftImage, BreadsGlowRightImage, BreadsGlowToastImage);
        setupGlowEffect(BreadsGlowRight, BreadsGlowRightImage, BreadsGlowLeftImage, BreadsGlowMiddleImage, BreadsGlowToastImage);
        setupGlowEffect(BreadsGlowToast, BreadsGlowToastImage, BreadsGlowLeftImage, BreadsGlowMiddleImage, BreadsGlowRightImage);
        setupGlowEffect(BreadsGlowWhite, BreadsGlowWhiteImage, BreadsGlowWheatImage, BreadsGlowRyeImage, BreadsGlowWrapImage, BreadsGlowLeftImage, BreadsGlowMiddleImage, BreadsGlowRightImage, BreadsGlowToastImage);
        setupGlowEffect(BreadsGlowWheat, BreadsGlowWheatImage, BreadsGlowWhiteImage, BreadsGlowRyeImage, BreadsGlowWrapImage, BreadsGlowLeftImage, BreadsGlowMiddleImage, BreadsGlowRightImage, BreadsGlowToastImage);
        setupGlowEffect(BreadsGlowRye, BreadsGlowRyeImage, BreadsGlowWhiteImage, BreadsGlowWheatImage, BreadsGlowWrapImage, BreadsGlowLeftImage, BreadsGlowMiddleImage, BreadsGlowRightImage, BreadsGlowToastImage);
        setupGlowEffect(BreadsGlowWrap, BreadsGlowWrapImage, BreadsGlowWhiteImage, BreadsGlowWheatImage, BreadsGlowRyeImage, BreadsGlowLeftImage, BreadsGlowMiddleImage, BreadsGlowRightImage, BreadsGlowToastImage);

        setupGlowEffect(ChipsRed1, ChipsRed1Image, ChipsRed2Image, ChipsRed3Image, ChipsBlue1Image, ChipsBlue2Image, ChipsBlue3Image);
        setupGlowEffect(ChipsRed2, ChipsRed2Image, ChipsRed1Image, ChipsRed3Image, ChipsBlue1Image, ChipsBlue2Image, ChipsBlue3Image);
        setupGlowEffect(ChipsRed3, ChipsRed3Image, ChipsRed1Image, ChipsRed2Image, ChipsBlue1Image, ChipsBlue2Image, ChipsBlue3Image);
        setupGlowEffect(ChipsBlue1, ChipsBlue1Image, ChipsRed1Image, ChipsRed2Image, ChipsRed3Image, ChipsBlue2Image, ChipsBlue3Image);
        setupGlowEffect(ChipsBlue2, ChipsBlue2Image, ChipsRed1Image, ChipsRed2Image, ChipsRed3Image, ChipsBlue1Image, ChipsBlue3Image);
        setupGlowEffect(ChipsBlue3, ChipsBlue3Image, ChipsRed1Image, ChipsRed2Image, ChipsRed3Image, ChipsBlue1Image, ChipsBlue2Image);

        setupGlowEffect(SaucesMustard, SaucesMustardImage, SaucesMayoImage, SaucesKetchupImage, SaucesRanchImage, SaucesThousandIslandImage, SaucesVinaigretteImage);
        setupGlowEffect(SaucesMayo, SaucesMayoImage, SaucesMustardImage, SaucesKetchupImage, SaucesRanchImage, SaucesThousandIslandImage, SaucesVinaigretteImage);
        setupGlowEffect(SaucesKetchup, SaucesKetchupImage, SaucesMustardImage, SaucesMayoImage, SaucesRanchImage, SaucesThousandIslandImage, SaucesVinaigretteImage);
        setupGlowEffect(SaucesRanch, SaucesRanchImage, SaucesMustardImage, SaucesMayoImage, SaucesKetchupImage, SaucesThousandIslandImage, SaucesVinaigretteImage);
        setupGlowEffect(SaucesThousandIsland, SaucesThousandIslandImage, SaucesMustardImage, SaucesMayoImage, SaucesKetchupImage, SaucesRanchImage, SaucesVinaigretteImage);
        setupGlowEffect(SaucesVinaigrette, SaucesVinaigretteImage, SaucesMustardImage, SaucesMayoImage, SaucesKetchupImage, SaucesRanchImage, SaucesThousandIslandImage);

        setupGlowEffect(SodaSmall, SodaSmallImage, SodaMediumImage, SodaLargeImage);
        setupGlowEffect(SodaMedium, SodaMediumImage, SodaSmallImage, SodaLargeImage);
        setupGlowEffect(SodaLarge, SodaLargeImage, SodaSmallImage, SodaMediumImage);

        setupGlowEffect(SpecialsBLT, SpecialsBLTImage, SpecialsPhillyImage);
        setupGlowEffect(SpecialsPhilly, SpecialsPhillyImage, SpecialsBLTImage);

        setupGlowEffect(Steak, SteakImage, HamImage, SalamiImage, RoastBeefImage, ChickenImage, BaconImage, AmericanImage, ProvoloneImage, ChedderImage, SwissImage, LettuceImage, BellpepperImage, OnionImage, TomatoImage, JalapenoImage, CucumberImage, PickleImage, AvacadoImage, MushroomImage);
        setupGlowEffect(Ham, HamImage, SteakImage, SalamiImage, RoastBeefImage, ChickenImage, BaconImage, AmericanImage, ProvoloneImage, ChedderImage, SwissImage, LettuceImage, BellpepperImage, OnionImage, TomatoImage, JalapenoImage, CucumberImage, PickleImage, AvacadoImage, MushroomImage);
        setupGlowEffect(Salami, SalamiImage, SteakImage, HamImage, RoastBeefImage, ChickenImage, BaconImage, AmericanImage, ProvoloneImage, ChedderImage, SwissImage, LettuceImage, BellpepperImage, OnionImage, TomatoImage, JalapenoImage, CucumberImage, PickleImage, AvacadoImage, MushroomImage);
        setupGlowEffect(RoastBeef, RoastBeefImage, SteakImage, HamImage, SalamiImage, ChickenImage, BaconImage, AmericanImage, ProvoloneImage, ChedderImage, SwissImage, LettuceImage, BellpepperImage, OnionImage, TomatoImage, JalapenoImage, CucumberImage, PickleImage, AvacadoImage, MushroomImage);
        setupGlowEffect(Chicken, ChickenImage, SteakImage, HamImage, SalamiImage, RoastBeefImage, BaconImage, AmericanImage, ProvoloneImage, ChedderImage, SwissImage, LettuceImage, BellpepperImage, OnionImage, TomatoImage, JalapenoImage, CucumberImage, PickleImage, AvacadoImage, MushroomImage);
        setupGlowEffect(Bacon, BaconImage, SteakImage, HamImage, SalamiImage, RoastBeefImage, ChickenImage, AmericanImage, ProvoloneImage, ChedderImage, SwissImage, LettuceImage, BellpepperImage, OnionImage, TomatoImage, JalapenoImage, CucumberImage, PickleImage, AvacadoImage, MushroomImage);
        setupGlowEffect(American, AmericanImage, SteakImage, HamImage, SalamiImage, RoastBeefImage, ChickenImage, BaconImage, ProvoloneImage, ChedderImage, SwissImage, LettuceImage, BellpepperImage, OnionImage, TomatoImage, JalapenoImage, CucumberImage, PickleImage, AvacadoImage, MushroomImage);
        setupGlowEffect(Provolone, ProvoloneImage, SteakImage, HamImage, SalamiImage, RoastBeefImage, ChickenImage, BaconImage, AmericanImage, ChedderImage, SwissImage, LettuceImage, BellpepperImage, OnionImage, TomatoImage, JalapenoImage, CucumberImage, PickleImage, AvacadoImage, MushroomImage);
        setupGlowEffect(Chedder, ChedderImage, SteakImage, HamImage, SalamiImage, RoastBeefImage, ChickenImage, BaconImage, AmericanImage, ProvoloneImage, SwissImage, LettuceImage, BellpepperImage, OnionImage, TomatoImage, JalapenoImage, CucumberImage, PickleImage, AvacadoImage, MushroomImage);
        setupGlowEffect(Swiss, SwissImage, SteakImage, HamImage, SalamiImage, RoastBeefImage, ChickenImage, BaconImage, AmericanImage, ProvoloneImage, ChedderImage, LettuceImage, BellpepperImage, OnionImage, TomatoImage, JalapenoImage, CucumberImage, PickleImage, AvacadoImage, MushroomImage);
        setupGlowEffect(Lettuce, LettuceImage, SteakImage, HamImage, SalamiImage, RoastBeefImage, ChickenImage, BaconImage, AmericanImage, ProvoloneImage, ChedderImage, SwissImage, BellpepperImage, OnionImage, TomatoImage, JalapenoImage, CucumberImage, PickleImage, AvacadoImage, MushroomImage);
        setupGlowEffect(Bellpepper, BellpepperImage, SteakImage, HamImage, SalamiImage, RoastBeefImage, ChickenImage, BaconImage, AmericanImage, ProvoloneImage, ChedderImage, SwissImage, LettuceImage, OnionImage, TomatoImage, JalapenoImage, CucumberImage, PickleImage, AvacadoImage, MushroomImage);
        setupGlowEffect(Onion, OnionImage, SteakImage, HamImage, SalamiImage, RoastBeefImage, ChickenImage, BaconImage, AmericanImage, ProvoloneImage, ChedderImage, SwissImage, LettuceImage, BellpepperImage, TomatoImage, JalapenoImage, CucumberImage, PickleImage, AvacadoImage, MushroomImage);
        setupGlowEffect(Tomato, TomatoImage, SteakImage, HamImage, SalamiImage, RoastBeefImage, ChickenImage, BaconImage, AmericanImage, ProvoloneImage, ChedderImage, SwissImage, LettuceImage, BellpepperImage, OnionImage, JalapenoImage, CucumberImage, PickleImage, AvacadoImage, MushroomImage);
        setupGlowEffect(Jalapeno, JalapenoImage, SteakImage, HamImage, SalamiImage, RoastBeefImage, ChickenImage, BaconImage, AmericanImage, ProvoloneImage, ChedderImage, SwissImage, LettuceImage, BellpepperImage, OnionImage, TomatoImage, CucumberImage, PickleImage, AvacadoImage, MushroomImage);
        setupGlowEffect(Cucumber, CucumberImage, SteakImage, HamImage, SalamiImage, RoastBeefImage, ChickenImage, BaconImage, AmericanImage, ProvoloneImage, ChedderImage, SwissImage, LettuceImage, BellpepperImage, OnionImage, TomatoImage, JalapenoImage, PickleImage, AvacadoImage, MushroomImage);
        setupGlowEffect(Pickle, PickleImage, SteakImage, HamImage, SalamiImage, RoastBeefImage, ChickenImage, BaconImage, AmericanImage, ProvoloneImage, ChedderImage, SwissImage, LettuceImage, BellpepperImage, OnionImage, TomatoImage, JalapenoImage, CucumberImage, AvacadoImage, MushroomImage);
        setupGlowEffect(Avacado, AvacadoImage, SteakImage, HamImage, SalamiImage, RoastBeefImage, ChickenImage, BaconImage, AmericanImage, ProvoloneImage, ChedderImage, SwissImage, LettuceImage, BellpepperImage, OnionImage, TomatoImage, JalapenoImage, CucumberImage, PickleImage, MushroomImage);
        setupGlowEffect(Mushroom, MushroomImage, SteakImage, HamImage, SalamiImage, RoastBeefImage, ChickenImage, BaconImage, AmericanImage, ProvoloneImage, ChedderImage, SwissImage, LettuceImage, BellpepperImage, OnionImage, TomatoImage, JalapenoImage, CucumberImage, PickleImage, AvacadoImage);

        setupGlowEffect(Bell, BellGlow);
    }
}
