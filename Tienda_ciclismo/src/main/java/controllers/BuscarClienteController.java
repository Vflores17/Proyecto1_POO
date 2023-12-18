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
public class BuscarClienteController implements Initializable {

    //Definición de variables y elementos gráficos a utilizar
    @FXML
    private Label nombre;
    @FXML
    private Label apellido;
    @FXML
    private Label telefono;
    @FXML
    private Label correo;
    @FXML
    private Label provincia;
    @FXML
    private Label canton;
    @FXML
    private Label distrito;
    @FXML
    private Label fechaN;
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
        App.cambiarVista(getStage(), "registroCliente");
    }

    /**
     * *
     * Método para mostrar la información del cliente seleccionado.
     *
     * @param event Evento para ejecutar el método cuando se accione el botón.
     */
    @FXML
    private void mostrarBusqueda(ActionEvent event) {
        String dato = obtenerDato.getText();
        boolean existe = false;

        if (dato != null && !dato.isEmpty()) {
            List<Cliente> clientes = App.getClientes();
            for (Cliente cliente : clientes) {
                try {
                    if (cliente.getCodigo() == Integer.parseInt(dato.strip())) {
                        nombre.setText("Nombre: " + cliente.getNombre());
                        apellido.setText("Apellido: " + cliente.getApellido());
                        telefono.setText("Telefono: " + cliente.getTelefono());
                        correo.setText("Correo electronico: " + cliente.getCorreo());
                        provincia.setText("Provincia: " + cliente.getProvincia());
                        canton.setText("Cantón: " + cliente.getCanton());
                        distrito.setText("Distrito: " + cliente.getDistrito());
                        fechaN.setText("Fecha de nacimiento: " + cliente.getFechaNacimiento());
                        existe = true;
                    }
                } catch (NumberFormatException e) {
                    if (cliente.getNombre().equals(dato.strip())) {
                        nombre.setText("Nombre: " + cliente.getNombre());
                        apellido.setText("Apellido: " + cliente.getApellido());
                        telefono.setText("Telefono: " + cliente.getTelefono());
                        correo.setText("Correo electronico: " + cliente.getCorreo());
                        provincia.setText("Provincia: " + cliente.getProvincia());
                        canton.setText("Cantón: " + cliente.getCanton());
                        distrito.setText("Distrito: " + cliente.getDistrito());
                        fechaN.setText("Fecha de nacimiento: " + cliente.getFechaNacimiento());
                        existe = true;
                    }
                }
            }
            if (!existe) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Cliente no encontrado.");
                alert.show();
            }

        }
    }

    /**
     * *
     * Método para obtener el stage de la ventana actual.
     *
     * @return Stage actual.
     */
    private Stage getStage() {
        Stage stage = (Stage) btBuscar.getScene().getWindow();
        return stage;
    }
}
