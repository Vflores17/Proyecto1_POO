module login {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.base;
    requires javafx.graphics;
    requires java.base;
    
    opens login to javafx.fxml;
    exports login;

    opens controllers to javafx.fxml;
    exports controllers;
}

