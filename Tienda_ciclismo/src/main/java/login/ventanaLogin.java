package login;

import archivos.cargarArchivo;
import java.io.IOException;
import java.util.Dictionary;
import java.util.Hashtable;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import validacionDatos.validarInformacion;
import archivos.cargarArchivo;
import java.lang.String;
import javafx.stage.Stage;



public class ventanaLogin {
    
    String usuario;
    String contrasenna;
    
    private Dictionary credenciales = new Hashtable() ;

    @FXML
    private Button botLimpiar;
    @FXML
    private Button botIngresar;
    @FXML
    private Button botSalir;
    @FXML
    private TextField textUsuario;
    @FXML
    private PasswordField textContrasena;
    
    public ventanaLogin(){
        credenciales = infoVentanaLogin();
        System.out.println(credenciales);
    }
    private Dictionary infoVentanaLogin(){
        credenciales = cargarArchivo.cargarInformacion("usuarios.dat");
        return credenciales;
    }
    
    
    @FXML
    private void obtenerUsuario() throws IOException {
    if (textUsuario != null && !textUsuario.getText().isEmpty() && textContrasena != null && !textContrasena.getText().isEmpty()) {
        usuario = textUsuario.getText();
        contrasenna = textContrasena.getText();
        if (validarInformacion.loginUsuario(usuario, contrasenna,credenciales)){
            sesionIniciada();
        }
    } else {
        Alert alert = new Alert(AlertType.ERROR, "Falta información para poder ingresar al sistema.");
        alert.show();
    }
}
    @FXML
    private void borrarContenido(){
    textUsuario.clear();
    textContrasena.clear();
    }
   
    
    @FXML
    private void salir(){
        Stage stage = (Stage) botSalir.getScene().getWindow();
        stage.close();
    }
    
    private void sesionIniciada() throws IOException{
        Stage stage = (Stage) botIngresar.getScene().getWindow();
        App.cambiarVentana(stage,"ventanaPrincipal");
    }
}
