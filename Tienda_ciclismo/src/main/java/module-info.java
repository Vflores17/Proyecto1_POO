module login {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.base;
    requires javafx.graphics;
    requires java.base;
    
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.annotation;
    requires com.fasterxml.jackson.datatype.jsr310;
    
    opens clases to com.fasterxml.jackson.databind;
    
    opens login to javafx.fxml;
    exports login;

    opens controllers to javafx.fxml;
    exports controllers;
}

