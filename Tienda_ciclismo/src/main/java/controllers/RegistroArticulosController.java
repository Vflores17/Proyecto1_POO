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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import login.App;

/**
 * FXML Controller class
 *
 * @author Personal
 */
public class RegistroArticulosController implements Initializable {

    @FXML
    private ComboBox<?> tipoProducto;
    @FXML
    private TextField nombreArticulo;
    @FXML
    private ComboBox<?> tipoArticulo;
    @FXML
    private TextField marcaArticulo;
    @FXML
    private TextField precioArticulo;
    @FXML
    private TextField cantidadArticulo;
    @FXML
    private Button botAgregar;
    @FXML
    private Button botRegresar;
    @FXML
    private Text tamanno;
    @FXML
    private ComboBox<?> tamannoArticulo;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void crearNuevoArticulo(ActionEvent event) {
    }

    @FXML
    private void regresar(ActionEvent event) throws IOException {
        App.cambiarVista(getStage(), "agregarProducto");
    }
    private Stage getStage(){
        Stage stage = (Stage) botAgregar.getScene().getWindow();
        return stage;
    }
}
