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
public class buscarFacturaController implements Initializable {
    

    @FXML
    private Button botBuscarNumeroFactura;
    @FXML
    private Button botBuscarFechaFactura;
    @FXML
    private Button botBuscarNombreCliente;
    @FXML
    private Button botRegresar;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        }    
    
    
    @FXML
    private void opcionBuscarNumeroFactura(ActionEvent event) throws IOException{
        App.cambiarVista(getStage(), "buscarNumeroFactura");
    }

    @FXML
    private void opcionBuscarFechaFactura(ActionEvent event) throws IOException {
        App.cambiarVista(getStage(), "buscarFechaFactura");
    }

    @FXML
    private void opcionBuscarNombreCliente(ActionEvent event)throws IOException {
        App.cambiarVista(getStage(),"buscarNombreFactura");
    }


    @FXML
    private void regresar(ActionEvent event) throws IOException {
        App.cambiarVista(getStage(), "facturacion");
    }
    private Stage getStage(){
        Stage stage = (Stage) botBuscarFechaFactura.getScene().getWindow();
        return stage;
    }
    
}
