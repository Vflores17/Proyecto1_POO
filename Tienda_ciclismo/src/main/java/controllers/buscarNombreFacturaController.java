package controllers;

//Módulo de importaciones
import archivos.cargarArchivo;
import clases.Facturacion;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import login.App;

/**
 * Contradolar de la ventana donde se busca clientes.
 *
 * @author Dylan Meza
 */
public class buscarNombreFacturaController implements Initializable {

    //Definición de variables y elementos gráficos a utilizar
    @FXML
    
    private Button btBuscar;
    @FXML
    private TextField obtenerDato;

    /**
     * Inicizalidor del controlador.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    /**
     * *
     * Método para poder salir de la ventana actual.
     *
     * @param event Evento para ejecutar el método cuando se accione el botón.
     * @throws IOException Excepciones en el caso de que falle alguna llamada a
     * otros métodos
     */
    @FXML
    private void regresar(ActionEvent event) throws IOException {
        App.cambiarVista(getStage(), "buscarFactura");
    }

    /**
     * *
     * Método para mostrar la información del cliente seleccionado.
     *
    // * @param event Evento para ejecutar el método cuando se accione el botón.
     */
    @FXML
    private void mostrarBusqueda(ActionEvent event) {
        
    }

    
     
    private Stage getStage() {
        Stage stage = (Stage) btBuscar.getScene().getWindow();
        return stage;
    }
}