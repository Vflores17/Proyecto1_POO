/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.stage.Stage;
import login.App;

/**
 * FXML Controller class
 *
 * @author Personal
 */
public class menuServiciosController implements Initializable {

    @FXML
    private Button botonBuscar;
    @FXML
    private Button botonAgregar;
    @FXML
    private Button botonEliminar;
    @FXML
    private Button botonModificar;
    @FXML
    private Button botRegresar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void opcionBuscarServicio(ActionEvent event) throws IOException {
        App.cambiarVista(getStage(), "VentanaBuscarServicio");
    }

    @FXML
    private void opcionAgregarServicio(ActionEvent event) throws IOException {
        App.cambiarVista(getStage(), "ventanaAgregarServicio");
    }

    @FXML
    private void opcionEliminarServicio(ActionEvent event) {
    }

    @FXML
    private void opcionModificarServicio(ActionEvent event) throws IOException {
        App.cambiarVista(getStage(), "ventanaModificarServicio");
    }

    @FXML
    private void regresar(ActionEvent event) throws IOException {
        App.cambiarVista(getStage(), "ventanaPrincipal");
    }

    private Stage getStage() {
        Stage stage = (Stage) botonAgregar.getScene().getWindow();
        return stage;
    }

    
}
