package com.pluralsight;

import com.pluralsight.gui.Home;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
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
        scene.setFill(Color.TRANSPARENT);
        stage.initStyle(StageStyle.TRANSPARENT);

        String css = this.getClass().getResource("/styles/Home.css").toExternalForm();
        scene.getStylesheets().add(css);

        Image icon = new Image(getClass().getResource("/images/HomeScreen/icon.png").toExternalForm());
        stage.getIcons().add(icon);
        stage.setTitle("Ali's Sandwich Shop");

        stage.setScene(scene);
        
        // Force the scene to calculate its size
        root.applyCss();
        root.layout();
        
        // Get screen dimensions
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        
        // Calculate center position
        double centerX = (screenBounds.getWidth() - root.prefWidth(-1)) / 2;
        double centerY = (screenBounds.getHeight() - root.prefHeight(-1)) / 2;
        
        // Set position
        stage.setX(centerX);
        stage.setY(centerY);

        // Show and force focus
        stage.show();
        stage.toFront();
        stage.requestFocus();
        
        Home controller = loader.getController();
        controller.setStage(stage);
    }
}
