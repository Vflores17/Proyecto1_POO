package login;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import login.App;

public class ventanaPrincipalController {

    @FXML
    private Button botAccesorios;
    @FXML
    private Button botClientes;
    @FXML
    private Button botFacturacion;
    @FXML
    private Button botServicios;
    @FXML
    private Button botSalir;
    
    @FXML
    private void salir() throws IOException{
        Stage stage = (Stage) botAccesorios.getScene().getWindow();
        App.cambiarVentana(stage, "ventanalogin");
    }
}