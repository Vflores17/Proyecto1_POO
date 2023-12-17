package controllers;

import archivos.cargarArchivo;
import archivos.guardarArchivo;
import clases.Cliente;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import login.App;

/**
 * FXML Controller class
 *
 * @author Dilan
 */
public class AgregarClienteController implements Initializable {

    @FXML
    private Button btAgregar;
    @FXML
    private TextField entryNombre;
    @FXML
    private TextField entryApellido;
    @FXML
    private TextField entryTelefono;
    @FXML
    private TextField entryCorreo;
    @FXML
    private TextField entryDistrito;    
    @FXML
    private TextField entryCanton;
    @FXML
    private ComboBox<String> comboProvincia;
    @FXML
    private DatePicker fechaNacimiento;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        comboProvincia.getItems().addAll("San José", "Alajuela", "Cartago", "Heredia", "Guanacaste", "Puntarenas", "Limón");
        comboProvincia.setValue("San José");
        fechaNacimiento.setValue(LocalDate.now());

    }

    @FXML
    private void limpiarVentana(ActionEvent event){
        entryNombre.clear();
        entryApellido.clear();
        entryTelefono.clear();
        entryCorreo.clear();
        entryDistrito.clear();
        entryCanton.clear();
        comboProvincia.setValue("San José");
        fechaNacimiento.setValue(LocalDate.now());
    }

    @FXML
    private void agregarCliente(ActionEvent event) throws IOException{
        
        String nombre = entryNombre.getText();
        String telefono = entryTelefono.getText();
        String apellido = entryApellido.getText();
        String correo = entryCorreo.getText();
        String provincia = comboProvincia.getValue();
        String canton = entryCanton.getText();
        String distrito = entryDistrito.getText();
        
        List<Cliente> clientes = cargarArchivo.leerClientes();
        int contar = clientes.size();
        
        if(nombre != null && !nombre.isEmpty() && apellido != null && !apellido.isEmpty() && telefono != null && !telefono.isEmpty() && correo != null && !correo.isEmpty() && distrito != null && !distrito.isEmpty() && canton != null && !canton.isEmpty()){
            if(validar_telefono(telefono)){
                if(validar_espacios(nombre) && validar_espacios(apellido) && validar_espacios(correo) && validar_espacios(canton) && validar_espacios(distrito)){
                    Cliente cliente = new Cliente(contar+1,nombre,apellido,telefono,correo,provincia,canton,distrito,getFecha());
                    clientes.add(cliente);
                    guardarArchivo.guardarCliente(clientes);
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Se ha registrado correctamente el cliente");
                    alert.show();
                    
                }else{
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Hay espacios al inicio o al final de los textos de entrada, porfavor eliminelos.");
                    alert.show();
                }   
            }else{
                Alert alert = new Alert(Alert.AlertType.ERROR, "El número ingresado no es invalido.");
                alert.show();
            }
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR, "Falta información para poder ingresar al sistema.");
            alert.show();
        }
    }
    
    @FXML
    private void regresar(ActionEvent event) throws IOException {
        App.cambiarVista(getStage(), "registroCliente");
    }
    
    private Stage getStage(){
        Stage stage = (Stage) btAgregar.getScene().getWindow();
        return stage;
    }
    
    private boolean validar_telefono(String telefono){
        if(telefono.length() == 8 && telefono.matches("[2|4|6|8]\\d+")){
            return true;
        }
        return false;
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
    
    private String getFecha(){
        LocalDate localDate = fechaNacimiento.getValue();
        String formato = localDate.format(DateTimeFormatter.ofPattern("MMMM dd, yyyy"));
        return formato;
    }
}
