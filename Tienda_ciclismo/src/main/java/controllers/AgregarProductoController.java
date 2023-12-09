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
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import login.App;

/**
 * FXML Controller class
 *
 * @author Personal
 */
public class AgregarProductoController implements Initializable {

    @FXML
    private Button botAgregar;
    @FXML
    private TextField textProducto;
    @FXML
    private Button botRegresar;
    @FXML
    private MenuBar menuArticulo;
    @FXML
    private Menu opcionNewArticulo;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }   
    
    @FXML
    private void mostrarVentanaAgregar() throws IOException{
        System.out.println("entra aqui");
        App.cambiarVista(getStage(), "registroArticulos");
        
    }
    private Stage getStage(){
        Stage stage = (Stage) botAgregar.getScene().getWindow();
        return stage;
    }

    @FXML
    private void regresar(ActionEvent event) throws IOException {
        App.cambiarVista(getStage(), "registroProductos");
    }
}