/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controllers;

import clases.articulo;
import clases.tipoProducto;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
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
    private ComboBox<String> tipoProducto;
    @FXML
    private TextField nombreArticulo;
    @FXML
    private ComboBox<String> tiposArticulo;
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
    private ComboBox<String> tamannoArticulo;
    
    private ArrayList<String> productosDisponibles;
    
    private String tamannNuevoArticulo;
    
    private String[] tipos = {"Bicicletas", "Suplementos", "Accesorios"};
    private String pTipoArticulo;
    private String pNombreArticulo;
    private String pCategoriaArticulo;
    private String pTamannoArticulo;
    private String pMarcaArticulo;
    private int pPrecioArticulo;
    private int pCantArticulo;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        productosDisponibles = App.verProductos();
        tipoProducto.getItems().addAll(productosDisponibles);
        tiposArticulo.getItems().addAll(tipos);
        
    }
    
    @FXML
    private void crearNuevoArticulo(ActionEvent event) {
        if (validarDatos()) {
            int codigo = App.buscarCodigoProducto(pTipoArticulo); 
            int codigoArticulo = App.cantArticulos()+1; 
            articulo newArticulo = new articulo(codigoArticulo, pNombreArticulo,pCategoriaArticulo,pTamannoArticulo,pMarcaArticulo,pPrecioArticulo,pCantArticulo,codigo,pTipoArticulo);
            borrarDatos();
            System.out.println(newArticulo.mostrarTodo());
        } else {
            System.out.println("Soy un mamapichas");
        }
    }
    
    @FXML
    private void regresar(ActionEvent event) throws IOException {
        App.cambiarVista(getStage(), "agregarProducto");
    }
    
    private Stage getStage() {
        Stage stage = (Stage) botAgregar.getScene().getWindow();
        return stage;
    }
    
    @FXML
    public void habilitarComboBox() {
        if ("Bicicletas".equals(tiposArticulo.getValue())) {
            String[] tamannos = {"12", "16", "22", "26", "27", "27.5", "29"};
            tamannoArticulo.getItems().addAll(tamannos);
            tamannoArticulo.setDisable(false);
        } else {
            tamannoArticulo.getItems().clear();
            tamannoArticulo.setDisable(true);
            tamannNuevoArticulo = "0";
        }
    }
    
    public boolean validarDatos() {
        if (tipoProducto.getValue() != null) {
            pTipoArticulo = tipoProducto.getValue();
            if (!nombreArticulo.getText().isEmpty() && !esNumerico(nombreArticulo.getText())) {
                pNombreArticulo = nombreArticulo.getText().strip();
                if (tiposArticulo.getValue() != null) {
                    pCategoriaArticulo = tiposArticulo.getValue();
                    if (tamannoArticulo.getValue() != null || tamannoArticulo.isDisable()) {
                        if (tamannoArticulo.isDisable()) {
                            pTamannoArticulo = "0";
                        } else {
                            pTamannoArticulo = tamannoArticulo.getValue();
                        }
                        if (!marcaArticulo.getText().isEmpty() && !esNumerico(marcaArticulo.getText())) {
                            pMarcaArticulo = marcaArticulo.getText().strip();
                            if (!precioArticulo.getText().isEmpty() && esNumerico(precioArticulo.getText())) {
                                pPrecioArticulo = Integer.parseInt(precioArticulo.getText().strip());
                                if (!cantidadArticulo.getText().isEmpty() && esNumerico(cantidadArticulo.getText())) {
                                    pCantArticulo = Integer.parseInt(cantidadArticulo.getText().strip());
                                    return true;
                                } else {
                                    Alert alert = new Alert(Alert.AlertType.ERROR, "Debe ingresar un número entero para la cantidad de articulos.");
                                    alert.show();
                                }
                            } else {
                                Alert alert = new Alert(Alert.AlertType.ERROR, "Debe ingresar un número entero para el precio del articulo.");
                                alert.show();
                            }
                        } else {
                            Alert alert = new Alert(Alert.AlertType.ERROR, "Debe ingresar la marca del articulo.");
                            alert.show();
                        }
                    } else {
                        Alert alert = new Alert(Alert.AlertType.ERROR, "Debe selecionar un tamaño.");
                        alert.show();
                    }
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Debes seleccionar la categoria del articulo.");
                    alert.show();
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Debes ingresar el nombre del nuevo articulo.");
                alert.show();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Debes seleccionar un tipo de producto para el nuevo articulo.");
            alert.show();
        }
        return false;
        
    }

    public void borrarDatos() {
        pTipoArticulo = null;
        pNombreArticulo = null;
        pCategoriaArticulo = null;
        pTamannoArticulo = null;
        pMarcaArticulo = null;
        pPrecioArticulo = 0;
        pCantArticulo = 0;
    }

    public boolean esNumerico(String cadena) {
        // Expresión regular que verifica si la cadena contiene solo dígitos
        String regex = "\\d+";
        return cadena.matches(regex);
    }
    
}
