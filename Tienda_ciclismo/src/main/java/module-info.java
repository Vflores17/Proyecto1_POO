module login {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens login to javafx.fxml;
    exports login;

    opens controllers to javafx.fxml;
    exports controllers;
    
    
}