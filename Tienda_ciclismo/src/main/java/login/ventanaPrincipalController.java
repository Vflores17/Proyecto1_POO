package login;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import login.App;

public class ventanaPrincipalController {

    @FXML
    private Button botClientes;
    @FXML
    private Button botFacturacion;
    @FXML
    private Button botServicios;
    @FXML
    private Button botSalir;
    @FXML
    private Button botProductos;
    
    @FXML
    private void salir() throws IOException{
        App.cambiarVentana(getStage(), "ventanalogin");
    }

    @FXML
    private void opcionRegistrarProductos(ActionEvent event) throws IOException {
        App.cambiarVentana(getStage(), "registroProductos");
    }

    @FXML

    private void opcionRegistrarClientes(ActionEvent event) throws IOException {
        App.cambiarVentana(getStage(), "registroCliente");

    }

    @FXML
    private void opcionRealizarFactura(ActionEvent event) {
    }

    @FXML
    private void opcionRegistrarServicios(ActionEvent event) {
    }
    
    private Stage getStage(){
        Stage stage = (Stage) botProductos.getScene().getWindow();
        return stage;
    }
}