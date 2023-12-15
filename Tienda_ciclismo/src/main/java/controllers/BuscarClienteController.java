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
import javafx.scene.control.Alert;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import login.App;
/**
 * FXML Controller class
 *
 * @author Dilan
 */
public class BuscarClienteController implements Initializable {


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
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    private void regresar(ActionEvent event) throws IOException {
        App.cambiarVista(getStage(), "registroCliente");
    }

    @FXML
    private void mostrarBusqueda(ActionEvent event) {
        String dato = obtenerDato.getText();
        boolean existe = false;
        
        if(dato != null && !dato.isEmpty()){
            if(validar_espacios(dato)){
                List<Cliente> clientes = cargarArchivo.leerClientes();
                for(Cliente cliente : clientes){
                    try{
                        if(cliente.getCodigo() == Integer.parseInt(dato)){
                            nombre.setText("Nombre: "+cliente.getNombre());
                            apellido.setText("Apellido: "+cliente.getApellido());
                            telefono.setText("Telefono: "+cliente.getTelefono());
                            correo.setText("Correo electronico: "+cliente.getCorreo());
                            provincia.setText("Provincia: "+cliente.getProvincia());
                            canton.setText("Cantón: "+cliente.getCanton());
                            distrito.setText("Distrito: "+cliente.getDistrito());
                            fechaN.setText("Fecha de nacimiento: "+cliente.getFechaNacimiento());
                            existe = true;
                        }
                    }catch(NumberFormatException e){
                        if(cliente.getNombre().equals(dato)){
                            nombre.setText("Nombre: "+cliente.getNombre());
                            apellido.setText("Apellido: "+cliente.getApellido());
                            telefono.setText("Telefono: "+cliente.getTelefono());
                            correo.setText("Correo electronico: "+cliente.getCorreo());
                            provincia.setText("Provincia: "+cliente.getProvincia());
                            canton.setText("Cantón: "+cliente.getCanton());
                            distrito.setText("Distrito: "+cliente.getDistrito());
                            fechaN.setText("Fecha de nacimiento: "+cliente.getFechaNacimiento()); 
                            existe = true;
                        }    
                    }
                }
                if(!existe){
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Cliente no encontrado.");
                    alert.show();
                }
            }
        }
    }

    private Stage getStage() {
        Stage stage = (Stage) btBuscar.getScene().getWindow();
        return stage;
    }
    
    private boolean validar_espacios(String texto){
        if (texto.startsWith(" ")) {
            return false;
        }
        if (texto.endsWith(" ")) {
            return false;
        }
        return true;
    }
}
