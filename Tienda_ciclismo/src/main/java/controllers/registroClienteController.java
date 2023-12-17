/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controllers;

import archivos.cargarArchivo;
import clases.Cliente;
import java.io.IOException;
import java.net.URL;
import java.util.List;
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
public class registroClienteController implements Initializable {


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
    private void opcionBuscarCliente(ActionEvent event) throws IOException{
        App.cambiarVista(getStage(), "buscarCliente");
    }

    @FXML
    private void opcionAgregarCliente(ActionEvent event) throws IOException{
        App.cambiarVista(getStage(), "agregarCliente");
    }

    @FXML
    private void opcionEliminarCliente(ActionEvent event) throws IOException{
    }

    @FXML
    private void opcionModificarCliente(ActionEvent event) throws IOException{
        App.cambiarVista(getStage(), "modificarCliente");
    }

    @FXML
    private void regresar(ActionEvent event) throws IOException{
        App.cambiarVista(getStage(), "ventanaPrincipal");
    }
    
    private Stage getStage(){
        Stage stage = (Stage) botonAgregar.getScene().getWindow();
        return stage;
    }

}
