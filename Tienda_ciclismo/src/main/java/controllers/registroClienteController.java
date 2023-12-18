package controllers;

//Módulo de importaciones
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
 * Defición del controlador de la ventana.
 *
 * @author Dylan Meza
 */
public class registroClienteController implements Initializable {

    //Definición de variables y elementos gráficos a utilizar.
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
     * Inializador del controlador.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    /**
     * Método para mostrar la ventana para buscar un cliente.
     *
     * @param event Evento para accionar el método cuando se presione el botón.
     * @throws IOException Excepciones en el caso de que falle alguna llamada a
     * otros métodos
     */
    @FXML
    private void opcionBuscarCliente(ActionEvent event) throws IOException {
        App.cambiarVista(getStage(), "buscarCliente");
    }

    /**
     * Método para mostrar la ventana para agregar un cliente.
     *
     * @param event Evento para accionar el método cuando se presione el botón.
     * @throws IOException Excepciones en el caso de que falle alguna llamada a
     * otros métodos
     */
    @FXML
    private void opcionAgregarCliente(ActionEvent event) throws IOException {
        App.cambiarVista(getStage(), "agregarCliente");
    }

    /**
     * Método para mostrar la ventana de eliminar un cliente.
     *
     * @param event Evento para accionar el método cuando se presione el botón.
     * @throws IOException Excepciones en el caso de que falle alguna llamada a
     * otros métodos
     */
    @FXML
    private void opcionEliminarCliente(ActionEvent event) throws IOException {
    }

    /**
     * Método para mostrar la ventana para modificar un cliente.
     *
     * @param event Evento para accionar el método cuando se presione el botón.
     * @throws IOException Excepciones en el caso de que falle alguna llamada a
     * otros métodos
     */
    @FXML
    private void opcionModificarCliente(ActionEvent event) throws IOException {
        App.cambiarVista(getStage(), "modificarCliente");
    }

    /**
     * Método para mostrar la ventana para regresar a la ventana anterior.
     *
     * @param event Evento para accionar el método cuando se presione el botón.
     * @throws IOException Excepciones en el caso de que falle alguna llamada a
     * otros métodos
     */
    @FXML
    private void regresar(ActionEvent event) throws IOException {
        App.cambiarVista(getStage(), "ventanaPrincipal");
    }

    /**
     * Método para obtener el stage de la ventana.
     *
     * @return stage actual de la ventana.
     */
    private Stage getStage() {
        Stage stage = (Stage) botonAgregar.getScene().getWindow();
        return stage;
    }

}
