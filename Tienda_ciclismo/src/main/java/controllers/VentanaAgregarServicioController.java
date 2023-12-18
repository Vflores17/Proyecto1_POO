/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controllers;

import clases.Cliente;
import clases.servicio;
import static controllers.RegistroArticulosController.esNumerico;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import login.App;

/**
 * FXML Controller class
 *
 * @author Personal
 */
public class VentanaAgregarServicioController implements Initializable {

    @FXML
    private Button botAgregar;
    @FXML
    private Button botRegresar;
    @FXML
    private TextArea textDescripcion;
    @FXML
    private DatePicker dateRecibido;
    @FXML
    private TextField textMarca;
    @FXML
    private DatePicker dateEntrega;
    @FXML
    private TextField textPrecio;
    @FXML
    private Text textEstado;
    @FXML
    private TextArea textObservaciones;

    private ArrayList<String> Nombresclientes;
    private ArrayList<Cliente> clientes;
    @FXML
    private ComboBox<String> codClientes;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        textEstado.setText("Abierto");
        Nombresclientes = formatoClientes(App.devolverClientes());
        clientes = App.devolverClientes();
        codClientes.getItems().addAll(Nombresclientes);

    }

    @FXML
    private void regresar(ActionEvent event) throws IOException {
        App.cambiarVista(getStage(), "menuServicios");
    }

    private Stage getStage() {
        Stage stage = (Stage) botAgregar.getScene().getWindow();
        return stage;
    }

    private ArrayList formatoClientes(ArrayList<Cliente> lista) {
        ArrayList<String> info = new ArrayList();
        for (Cliente cliente : lista) {
            info.add(cliente.getNombre());
        }
        return info;

    }

    @FXML
    private void validarFecha(ActionEvent event) {
        try {
            LocalDate ingreso = dateRecibido.getValue();
            LocalDate entrega = dateEntrega.getValue();
            if (ingreso != null && entrega != null) {
                if (ingreso.isBefore(entrega)) {
                    System.out.println("Ok ok si se validaron las fechas");
                } else {
                    dateRecibido.setValue(null);
                    dateEntrega.setValue(null);
                    Alert alert = new Alert(Alert.AlertType.ERROR, "La fecha de entrega debe ser posterior a la fecha de recibido.");
                    alert.show();
                }
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }

    }

    @FXML
    private void agregar(ActionEvent event) {
        if (codClientes.getValue() != null) {
            int codigoCliente = buscarCliente(String.valueOf(codClientes.getValue()));
            String marca = textMarca.getText();
            String descripcion = textDescripcion.getText();
            String observaciones = textObservaciones.getText();
            LocalDate ingreso = dateRecibido.getValue();
            LocalDate entrega = dateEntrega.getValue();
            if (validarTexto(marca) && validarTexto(descripcion) && validarTexto(observaciones)) {
                if (validarPrecio(textPrecio.getText())) {
                    int precio = Integer.parseInt(textPrecio.getText());
                    int codigo = App.cantServicios() + 1;
                    servicio newServicio = new servicio(codigo, codigoCliente, marca, descripcion, precio, ingreso, entrega, observaciones, true);
                    App.guardarServicio(newServicio);
                    limpiar();
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "El servicio ha sido registrado satisfactoriamente.");
                    alert.show();
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Falta información de la bicicleta.");
                alert.show();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Debes seleccionar al cliente.");
            alert.show();
        }

    }

    private boolean validarPrecio(String precio) {
        if (!esNumerico(precio)) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Debes ingresar solamente números enteros para el precio.");
            alert.show();
            return false;
        }
        return true;
    }

    private boolean validarTexto(String texto) {
        if (texto != null) {
            return true;
        }
        return false;
    }

    private int buscarCliente(String nombre) {
        for (Cliente cliente : clientes) {
            if (cliente.getNombre().equals(nombre)) {
                return cliente.getCodigo();
            }
        }
        return -1;
    }

    private void limpiar() {
        textDescripcion.setText(null);
        dateRecibido.setValue(null);
        textMarca.setText(null);
        dateEntrega.setValue(null);
        textPrecio.setText(null);
        textEstado.setText(null);
        textObservaciones.setText(null);
    }
}
