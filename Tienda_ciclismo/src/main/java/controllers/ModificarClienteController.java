/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
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
import javafx.fxml.FXML;
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
public class ModificarClienteController implements Initializable {

    @FXML
    private Button btLimpiar;
    @FXML
    private Button btModificar;
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
    @FXML
    private ComboBox<String> elegirCliente;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        List<Cliente> clientes = cargarArchivo.leerClientes();
        for(Cliente cliente : clientes){
            elegirCliente.getItems().add("Código: "+cliente.getCodigo()+" : Cliente "+cliente.getNombre()+" "+cliente.getApellido());
        }
        comboProvincia.getItems().addAll("San José", "Alajuela", "Cartago", "Heredia", "Guanacaste", "Puntarenas", "Limón");
        btLimpiar.setDisable(true);
        btModificar.setDisable(true);
        entryNombre.setDisable(true);
        entryApellido.setDisable(true);
        entryTelefono.setDisable(true);
        entryCorreo.setDisable(true);
        entryDistrito.setDisable(true);
        entryCanton.setDisable(true);
        comboProvincia.setDisable(true);
        fechaNacimiento.setDisable(true);
    }    
    
    @FXML
    private void regresar(ActionEvent event) throws IOException {
        App.cambiarVista(getStage(), "registroCliente");
    }

    @FXML
    private void limpiarVentana(ActionEvent event) {
        entryNombre.setText("");
        entryApellido.setText("");
        entryTelefono.setText("");
        entryCorreo.setText("");
        entryCanton.setText("");
        entryDistrito.setText("");
        comboProvincia.setValue("");
        btLimpiar.setDisable(true);
        btModificar.setDisable(true);
        entryNombre.setDisable(true);
        entryApellido.setDisable(true);
        entryTelefono.setDisable(true);
        entryCorreo.setDisable(true);
        entryDistrito.setDisable(true);
        entryCanton.setDisable(true);
        comboProvincia.setDisable(true);
        fechaNacimiento.setDisable(true);
        elegirCliente.setDisable(false);
    }

    @FXML
    private void modificarCliente(ActionEvent event) {
        String nombre = entryNombre.getText();
        String telefono = entryTelefono.getText();
        String apellido = entryApellido.getText();
        String correo = entryCorreo.getText();
        String provincia = comboProvincia.getValue();
        String canton = entryCanton.getText();
        String distrito = entryDistrito.getText();
        
        
        if(nombre != null && !nombre.isEmpty() && apellido != null && !apellido.isEmpty() && telefono != null && !telefono.isEmpty() && correo != null && !correo.isEmpty() && distrito != null && !distrito.isEmpty() && canton != null && !canton.isEmpty()){
            if(validar_telefono(telefono)){
                if(validar_espacios(nombre) && validar_espacios(apellido) && validar_espacios(correo) && validar_espacios(canton) && validar_espacios(distrito)){
                    String codigo = elegirCliente.getValue();
                    List<Cliente> clientes = cargarArchivo.leerClientes();
                    for(Cliente cliente : clientes){
                        if(cliente.getCodigo() == obtenerCodigo(codigo)){
                            int indice = clientes.indexOf(cliente);
                            Cliente clienteModificado = new Cliente(cliente.getCodigo(),nombre,apellido,telefono,correo,provincia,canton,distrito,getFecha());
                            clientes.set(indice, clienteModificado);
                            guardarArchivo.guardarCliente(clientes);
                            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Se ha modificado el cliente correctamente");
                            alert.show();
                        }
                    }
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
    private void clienteElegido(ActionEvent event) {
        btLimpiar.setDisable(false);
        btModificar.setDisable(false);
        entryNombre.setDisable(false);
        entryApellido.setDisable(false);
        entryTelefono.setDisable(false);
        entryCorreo.setDisable(false);
        entryDistrito.setDisable(false);
        entryCanton.setDisable(false);
        comboProvincia.setDisable(false);
        fechaNacimiento.setDisable(false);
        
        String codigo = elegirCliente.getValue();
        List<Cliente> clientes = cargarArchivo.leerClientes();
        for(Cliente cliente : clientes){
            if(cliente.getCodigo() == obtenerCodigo(codigo)){
            elegirCliente.setDisable(true);
            entryNombre.setDisable(false);
            entryNombre.setText(cliente.getNombre());
            entryApellido.setDisable(false);
            entryApellido.setText(cliente.getApellido());
            entryTelefono.setDisable(false);
            entryTelefono.setText(Integer.toString(cliente.getTelefono()));
            entryCorreo.setDisable(false);
            entryCorreo.setText(cliente.getCorreo());
            entryDistrito.setDisable(false);
            entryDistrito.setText(cliente.getDistrito());
            entryCanton.setDisable(false);
            entryCanton.setText(cliente.getCanton());
            comboProvincia.setDisable(false);
            comboProvincia.setValue(cliente.getProvincia());
            fechaNacimiento.setDisable(false);
            fechaNacimiento.setValue(LocalDate.parse(cliente.getFechaNacimiento(),DateTimeFormatter.ofPattern("MMMM dd, yyyy")));
            elegirCliente.setDisable(true);
            }
        } 
    }
    
    private int obtenerCodigo(String cadenaCodigo){
        
        String[] partes = cadenaCodigo.split(":");
        
        String codigoCliente = partes[1].trim();

        int codigo = Integer.parseInt(codigoCliente);

        return codigo;
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
    
    private Stage getStage() {
        Stage stage = (Stage) btLimpiar.getScene().getWindow();
        return stage;
    }
}
