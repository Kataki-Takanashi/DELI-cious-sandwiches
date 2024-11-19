package com.pluralsight.gui;
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



public class Home {
    private Stage stage;

    @FXML
    private Button exitButton;

    @FXML
    private ImageView HomeScreenDoorGlow;

    @FXML
    private Button doorButton;

    @FXML
    private ImageView HomeScreen;
    @FXML
    private ImageView HomeScreenExitOut;
    @FXML
    private ImageView HomeScreenExitIn;
    @FXML
    private Button exitButton1;
    @FXML
    private Button exitButton2;
    @FXML
    private Button exitButton3;

    public void exitApp(ActionEvent event) throws IOException {
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
        System.exit(0); // Quits the whole program not just the gui, this is to escape the main loop
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    

    @FXML
    public void initialize() {
        doorButton.setOnMouseEntered(e -> createFadeTransition(HomeScreenDoorGlow, 0, 1, 0.2).play());
        doorButton.setOnMouseExited(e -> createFadeTransition(HomeScreenDoorGlow, 1, 0, 0.2).play());

        // Set up all effects for exit buttons
        for (Button button : Arrays.asList(exitButton1, exitButton2, exitButton3)) {
            button.setOnMouseEntered(e -> {
                HomeScreenExitOut.setOpacity(1);
                HomeScreenExitIn.setOpacity(0);
                HomeScreen.setOpacity(0);
            });
            
            button.setOnMouseExited(e -> {
                HomeScreenExitOut.setOpacity(0);
                HomeScreenExitIn.setOpacity(0);
                HomeScreen.setOpacity(1);
            });
            
            button.setOnMousePressed(e -> {
                HomeScreenExitOut.setOpacity(0);
                HomeScreenExitIn.setOpacity(1);
                HomeScreen.setOpacity(0);
            });
            
            button.setOnMouseReleased(e -> {
                HomeScreenExitOut.setOpacity(0);
                HomeScreenExitIn.setOpacity(0);
                HomeScreen.setOpacity(1);
            });
        }
    }

    // This is the fade transition maker from the Ledger App
    private FadeTransition createFadeTransition(Node node, double fromValue, double toValue) {
        FadeTransition fade = new FadeTransition(Duration.seconds(0.5), node);
        fade.setFromValue(fromValue);
        fade.setToValue(toValue);
        fade.setCycleCount(1);
        return fade;
    }

    private FadeTransition createFadeTransition(Node node, double fromValue, double toValue, double duration) {
        FadeTransition fade = new FadeTransition(Duration.seconds(duration), node);
        fade.setFromValue(fromValue);
        fade.setToValue(toValue);
        fade.setCycleCount(1);
        return fade;
    }

    @FXML
    public void goToShop(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/scenes/Shop.fxml"));
        Parent shopView = loader.load();
        
        Scene shopScene = new Scene(shopView);
        shopScene.setFill(Color.TRANSPARENT);
        
        // Get the CSS file
        String css = this.getClass().getResource("/styles/Shop.css").toExternalForm();
        shopScene.getStylesheets().add(css);
        
        // Get the stage from the event
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        // Set the new scene
        stage.setScene(shopScene);
    }
}
