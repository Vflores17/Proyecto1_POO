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
public class facturacionController implements Initializable {


    @FXML
    private Button botBuscar;
    @FXML
    private Button botAgregar;
    @FXML
    private Button botAnular;
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
    private void opcionBuscarFactura(ActionEvent event) throws IOException{
        App.cambiarVista(getStage(), "buscarFactura");
    }

    @FXML
    private void opcionAgregarFactura(ActionEvent event) throws IOException {
        App.cambiarVista(getStage(), "agregarFactura");
    }

    @FXML
    private void opcionAnularFactura(ActionEvent event) throws IOException {
        App.cambiarVista(getStage(),"AnularFactura");
    }

    @FXML
    private void regresar(ActionEvent event) throws IOException {
        App.cambiarVista(getStage(), "ventanaPrincipal");
    }
    private Stage getStage(){
        Stage stage = (Stage) botBuscar.getScene().getWindow();
        return stage;
    }

}
