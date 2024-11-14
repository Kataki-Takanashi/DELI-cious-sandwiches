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
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.Arrays;

public class Shop {
    @FXML
    private ImageView BreadsGlowLeftImage, BreadsGlowMiddleImage, BreadsGlowRightImage, BreadsGlowToastImage;
    @FXML
    private Button BreadsGlowLeft, BreadsGlowMiddle, BreadsGlowRight, BreadsGlowToast;

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

    private void setupGlowEffect(Button button, ImageView glowImage, ImageView... otherImages) {
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
    public void initialize() {
        setupGlowEffect(BreadsGlowLeft, BreadsGlowLeftImage, BreadsGlowMiddleImage, BreadsGlowRightImage, BreadsGlowToastImage);
        setupGlowEffect(BreadsGlowMiddle, BreadsGlowMiddleImage, BreadsGlowLeftImage, BreadsGlowRightImage, BreadsGlowToastImage);
        setupGlowEffect(BreadsGlowRight, BreadsGlowRightImage, BreadsGlowLeftImage, BreadsGlowMiddleImage, BreadsGlowToastImage);
        setupGlowEffect(BreadsGlowToast, BreadsGlowToastImage, BreadsGlowLeftImage, BreadsGlowMiddleImage, BreadsGlowRightImage);

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
    }
}
