/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import clases.Factura;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import login.App;

/**
 * FXML Controller class
 *
 * @author Dilan
 */
public class registroFacturaController implements Initializable {

    @FXML
    private Button botonBuscar;
    @FXML
    private Button botonAgregar;
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
    private void opcionBuscarFactura(ActionEvent event) throws IOException {
        List<Factura> factura = App.getFactura();
        for(Factura facturass : factura){
            System.out.println(facturass.toString());
        }
    }

    @FXML
    private void opcionAgregarFactura(ActionEvent event) throws IOException {
        App.cambiarVista(App.getStage(botRegresar), "agregarFactura");
    }

    @FXML
    private void regresar(ActionEvent event) throws IOException {
        App.cambiarVista(App.getStage(botRegresar), "ventanaPrincipal");
    }
    
}