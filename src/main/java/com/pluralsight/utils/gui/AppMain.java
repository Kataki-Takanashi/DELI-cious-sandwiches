package com.pluralsight.utils.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class AppMain extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/scenes/Home.fxml"));
        Parent root = loader.load();

        Scene scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT); // Transparent background
        stage.initStyle(StageStyle.TRANSPARENT); // Makes the window bar transparent

        String css = this.getClass().getResource("/styles/Home.css").toExternalForm();
        scene.getStylesheets().add(css);

        Image icon = new Image("images/HomeScreen/icon.png");
        stage.getIcons().add(icon);
        stage.setTitle("Ali's Sandwich Shop");

        Home controller = loader.getController(); // sending the stage to Home
        controller.setStage(stage);

        stage.setScene(scene);
        stage.show();
    }
}
