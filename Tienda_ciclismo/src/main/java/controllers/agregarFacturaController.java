/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controllers;

import clases.Facturacion;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import login.App;
import static login.App.devolverInfo;

/**
 * FXML Controller class
 *
 * @author Personal
 */
public class agregarFacturaController implements Initializable {

    @FXML
    private Button botAgregarFactura;
    @FXML
    private TextField textProducto;
    @FXML
    private Button botRegresarFactura;
    @FXML

    private ArrayList infoFacturas;

    /**
     * Initializes the controller class.
     */
    public void initialize(URL url, ResourceBundle rb) {
        }   
    
    
    private Stage getStage(){
        Stage stage = (Stage) botAgregarFactura.getScene().getWindow();
        return stage;
    }

    @FXML
    private void regresar(ActionEvent event) throws IOException {
        App.cambiarVista(getStage(), "facturacion");
    }
    
    
    @FXML
    private void nuevaFactura(){
        int numFactura = App.cantFacturas() + 1;
        int codCliente = App.cantFacturas();
        int subtotal = App.cantFacturas();
        int impuesto = subtotal/13;
        impuesto = App.cantFacturas();
        int total = subtotal + impuesto;
        total = App.cantFacturas();
        Facturacion newObjeto = new Facturacion(numFactura,codCliente,subtotal,impuesto,total);
        App.guardarFactura(newObjeto);
        App.verFacturas();
    
    }
}
