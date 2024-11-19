module com.pluralsight {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    
    opens com.pluralsight to javafx.fxml;
    opens com.pluralsight.gui to javafx.fxml;
    exports com.pluralsight.gui;
    exports com.pluralsight;
    exports com.pluralsight.model;
    opens com.pluralsight.model to javafx.fxml;
    exports com.pluralsight.service;
    opens com.pluralsight.service to javafx.fxml;
    exports com.pluralsight.ui;
    opens com.pluralsight.ui to javafx.fxml;
} 