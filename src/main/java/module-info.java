module com.pluralsight {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    
    opens com.pluralsight to javafx.fxml;
    opens com.pluralsight.utils.gui to javafx.fxml;
    exports com.pluralsight.utils.gui;
    exports com.pluralsight;
} 