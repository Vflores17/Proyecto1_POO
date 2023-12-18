/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controllers;

import clases.Cliente;
import clases.articulo;
import clases.servicio;
import static controllers.RegistroArticulosController.esNumerico;
import static controllers.VentanaBuscarServicioController.filtrarPorCodigo;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.DatePicker;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import login.App;

/**
 * FXML Controller class
 *
 * @author Personal
 */
public class VentanaModificarServicioController implements Initializable {

    @FXML
    private Button botRegresar;
    @FXML
    private MenuItem botMarca;
    @FXML
    private MenuItem botDescripcion;
    @FXML
    private MenuItem botPrecio;
    @FXML
    private MenuItem botRecibido;
    @FXML
    private MenuItem botEntrega;
    @FXML
    private MenuItem botObservaciones;
    @FXML
    private Text textCodigoServicio;
    @FXML
    private Text textMarca;
    @FXML
    private Text textPrecio;
    @FXML
    private Text textFechaRecibido;
    @FXML
    private Text textFechaEntrega;
    @FXML
    private Text textEstado;
    @FXML
    private Button botLimpiar;
    @FXML
    private Button mostrar;
    @FXML
    private TextArea textDescripcion;
    @FXML
    private TextArea textObservaciones;

    private servicio objetoModificar;

    private ArrayList<String> serviciosString;
    private ArrayList<servicio> servicios;
    @FXML
    private DatePicker datePicker;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        serviciosString = formatoClientes(App.devolverClientes());
        servicios = App.getServicios();
    }

    @FXML
    private void regresar(ActionEvent event) throws IOException {
        App.cambiarVista(getStage(), "menuServicios");
    }

    @FXML
    private void cambiarMarca(ActionEvent event) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Cambio de marca del servicio.");
        dialog.setHeaderText("Ingrese la nueva marca del servicio:");

        Optional<String> result = dialog.showAndWait();

        result.ifPresent(input -> {
            if (input != null) {
                objetoModificar.setMarcaBici(input);
                mostrarLabel(objetoModificar);
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "La marca ha sido modificada exitosamene.");
                alert.show();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Debe ingresar una marca válida.");
                alert.show();
            }

        });
    }

    @FXML
    private void cambiarDescripcion(ActionEvent event) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Cambio de descripción de la bicicleta.");
        dialog.setHeaderText("Ingrese la nueva descripción de la bicicleta:");

        Optional<String> result = dialog.showAndWait();

        result.ifPresent(input -> {
            if (input != null) {
                objetoModificar.setDescripcion(input.strip());
                mostrarLabel(objetoModificar);
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "La descripción ha sido modificada exitosamene.");
                alert.show();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Debe ingresar una descripció válida.");
                alert.show();
            }

        });
    }

    @FXML
    private void cambiarPrecio(ActionEvent event) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Cambio de precio del servicio.");
        dialog.setHeaderText("Ingrese el nuevo precio del servicio:");

        Optional<String> result = dialog.showAndWait();

        result.ifPresent(input -> {
            if (input != null && esNumerico(input)) {
                objetoModificar.setPrecio(Integer.parseInt(input));
                mostrarLabel(objetoModificar);
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "El precio ha sido modificada exitosamene.");
                alert.show();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Debe ingresar un precio válido.");
                alert.show();
            }

        });
    }

    @FXML
    private void cambiarFechaRecibido(ActionEvent event) {
        // Crear un diálogo personalizado con un DatePicker
        Stage dialogStage = new Stage();
        dialogStage.initModality(Modality.WINDOW_MODAL);

        DatePicker datePicker = new DatePicker();
        datePicker.setEditable(false);

        Button aceptar = new Button("ACEPTAR");
        aceptar.setOnAction(e -> {
            LocalDate fechaNueva = datePicker.getValue();
            if (fechaNueva != null) {
                if (fechaNueva.isBefore(objetoModificar.getFechaEntrega())) {
                    objetoModificar.setFechaRecibido(fechaNueva);
                    mostrarLabel(objetoModificar);
                    dialogStage.close();
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Debes selecionar una fecha anterior a la fecha de entrega.");
                    alert.show();
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Debes selecionar la nueva fecha");
                alert.show();
            }
        });

        VBox dialogLayout = new VBox(10);
        dialogLayout.setPadding(new Insets(10));
        dialogLayout.setAlignment(Pos.CENTER);
        dialogLayout.getChildren().addAll(datePicker, aceptar);

        Scene dialogScene = new Scene(dialogLayout, 250, 150);
        dialogStage.setScene(dialogScene);
        dialogStage.show();
    }

    @FXML
    private void cambiarFechaEntrega(ActionEvent event) {
        Stage dialogStage = new Stage();
        dialogStage.initModality(Modality.WINDOW_MODAL);

        DatePicker datePicker = new DatePicker();
        datePicker.setEditable(false);

        Button aceptar = new Button("ACEPTAR");
        aceptar.setOnAction(e -> {
            LocalDate fechaNueva = datePicker.getValue();
            if (fechaNueva != null) {
                if (fechaNueva.isAfter(objetoModificar.getFechaRecibido())) {
                    objetoModificar.setFechaEntrega(fechaNueva);
                    mostrarLabel(objetoModificar);
                    dialogStage.close();
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Debes selecionar una fecha posterior a la fecha de recibido.");
                    alert.show();
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Debes selecionar la nueva fecha");
                alert.show();
            }
        });

        VBox dialogLayout = new VBox(10);
        dialogLayout.setPadding(new Insets(10));
        dialogLayout.setAlignment(Pos.CENTER);
        dialogLayout.getChildren().addAll(datePicker, aceptar);

        Scene dialogScene = new Scene(dialogLayout, 250, 150);
        dialogStage.setScene(dialogScene);
        dialogStage.show();
    }

    @FXML
    private void cambiarObservaciones(ActionEvent event) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Nuevas observaciones para del servicio.");
        dialog.setHeaderText("Ingrese la nueva descripción del servicio:");

        Optional<String> result = dialog.showAndWait();

        result.ifPresent(input -> {
            if (input != null) {
                objetoModificar.setObservaciones(input);
                mostrarLabel(objetoModificar);
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Las observaciones han sido modificadas exitosamene.");
                alert.show();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Debe unas observaciones válidas.");
                alert.show();
            }

        });
    }

    @FXML
    private void limpiar(ActionEvent event) {
        textCodigoServicio.setText(null);
        textMarca.setText(null);
        textPrecio.setText(null);
        textFechaRecibido.setText(null);
        textFechaEntrega.setText(null);
        textEstado.setText(null);
        textDescripcion.setText(null);
        textObservaciones.setText(null);
        objetoModificar = null;
    }

    @FXML
    private void mostrar(ActionEvent event) {
        ChoiceDialog<String> serviciosDisponibles = new ChoiceDialog<>("Servicios", serviciosString);
        serviciosDisponibles.setTitle("Servicios disponibles");
        serviciosDisponibles.setHeaderText("Seleccione el servicio a modificar:");

        // Muestra el diálogo y espera que el usuario haga una elección
        Optional<String> input = serviciosDisponibles.showAndWait();

        // Maneja la opción seleccionada (si el usuario hizo una elección)
        input.ifPresent(opcionUser -> {
            objetoModificar = obtenerServicioEspecifico(servicios, Integer.parseInt(opcionUser.substring(0, 1)));
            mostrarLabel(objetoModificar);
        });

    }

    private Stage getStage() {
        Stage stage = (Stage) botRegresar.getScene().getWindow();
        return stage;
    }

    private servicio obtenerServicioEspecifico(ArrayList<servicio> servicios, int filtro) {
        for (servicio servicio : servicios) {
            if (servicio.getCodigoServicio() == filtro) {
                return servicio;
            }
        }
        return null;
    }

    private ArrayList<String> formatoClientes(ArrayList<Cliente> clientes) {
        ArrayList<String> clientesComboBox = new ArrayList();
        for (Cliente cliente : clientes) {
            int codigo = cliente.getCodigo();
            String nombre = cliente.getNombre();
            String etiqueta = String.valueOf(codigo) + " - " + nombre;
            clientesComboBox.add(etiqueta);
        }
        return clientesComboBox;
    }

    private void mostrarLabel(servicio objetoModificar) {
        textCodigoServicio.setText(String.valueOf(objetoModificar.getCodigoServicio()));
        textMarca.setText(objetoModificar.getMarcaBici());
        textPrecio.setText(String.valueOf(objetoModificar.getPrecio()));
        textDescripcion.setText(objetoModificar.getDescripcion());
        textFechaRecibido.setText(objetoModificar.getFechaRecibido().format(DateTimeFormatter.ofPattern("EEEE dd MMMM yyyy")));
        textFechaEntrega.setText(objetoModificar.getFechaEntrega().format(DateTimeFormatter.ofPattern("EEEE dd MMMM yyyy")));
        if (objetoModificar.isEstado()) {
            textEstado.setText("Abierto");
        } else {
            textEstado.setText("Cerrado");
        }
        textObservaciones.setText(objetoModificar.getObservaciones());
    }
}
