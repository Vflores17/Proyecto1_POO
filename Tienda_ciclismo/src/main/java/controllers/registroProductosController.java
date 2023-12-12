/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.Button;
import javafx.stage.Stage;
import login.App;

/**
 * FXML Controller class
 *
 * @author Personal
 */
public class registroProductosController implements Initializable {

    @FXML
    private Button botBuscar;
    @FXML
    private Button botAgregar;
    @FXML
    private Button botEliminar;
    @FXML
    private Button botModificar;
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
    private void opcionBuscarProducto(ActionEvent event) throws IOException {
        App.cambiarVista(getStage(), "ventanaBuscarProducto");
    }

    @FXML
    private void opcionAgregarProducto(ActionEvent event) throws IOException {
        App.cambiarVista(getStage(), "agregarProducto");
    }

    @FXML
    private void opcionEliminarProducto(ActionEvent event) {
    }

    @FXML
    private void opcionModificarProducto(ActionEvent event) {
    }

    @FXML
    private void regresar(ActionEvent event) throws IOException {
        App.cambiarVista(getStage(), "ventanaPrincipal");
    }

    private Stage getStage() {
        Stage stage = (Stage) botAgregar.getScene().getWindow();
        return stage;
    }

}
