/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controllers;

import clases.Cliente;
import clases.articulo;
import clases.servicio;
import static controllers.RegistroArticulosController.esNumerico;
import static controllers.VentanaBuscarProductoController.filtrarPorCodigo;
import static controllers.VentanaBuscarProductoController.filtrarPorNombre;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;
import login.App;

/**
 * FXML Controller class
 *
 * @author Personal
 */
public class VentanaBuscarServicioController implements Initializable {

    @FXML
    private Button botRegresar;
    @FXML
    private GridPane gridInformacion;

    private ArrayList clientes;
    @FXML
    private MenuItem busqCodCliente;
    @FXML
    private MenuItem busqNombCliente;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        clientes = App.devolverClientes();
    }

    @FXML
    private void busquedaCodigoCliente(ActionEvent event) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Búsqueda de servicios por código de cliente.");
        dialog.setHeaderText("Ingrese el código del cliente:");

        Optional<String> result = dialog.showAndWait();

        result.ifPresent(codigoCliente -> {
            if (esNumerico(codigoCliente)) {
                ArrayList servicios = App.getServicios();

                ArrayList elementosMostrar = filtrarPorCodigo(servicios, Integer.parseInt(codigoCliente));
                gridInformacion.getChildren().clear();
                if (!elementosMostrar.isEmpty()) {
                    colocarLabels();
                    colocarInformacion(elementosMostrar);
                } else {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION, "No se han encontrado productos con el valor ingresado.");
                    alert.show();
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Debes ingresar solamente números enteros.");
                alert.show();
            }
        });
    }

    @FXML
    private void busquedaNombreCliente(ActionEvent event) {

        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Búsqueda por nombre del cliente.");
        dialog.setHeaderText("Ingrese el nombre del cliente:");

        Optional<String> result = dialog.showAndWait();

        result.ifPresent(nombreCliente -> {
            if (!nombreCliente.isEmpty()) {
                ArrayList servicios = App.getServicios();

                ArrayList elementosMostrar = filtrarPorNombre(servicios, nombreCliente, clientes);
                if (!elementosMostrar.isEmpty()) {
                    colocarLabels();
                    colocarInformacion(elementosMostrar);

                } else {
                    //listViewElementos.getItems().clear();
                    Alert alert = new Alert(Alert.AlertType.INFORMATION, "No se han encontrado servicios asociados al cliente ingresado.");
                    alert.show();
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Debes el nombre del producto a buscar.");
                alert.show();
            }
        }
        );
    }

    @FXML
    private void regresar(ActionEvent event) throws IOException {
        App.cambiarVista(getStage(), "menuServicios");
    }

    private Stage getStage() {
        Stage stage = (Stage) botRegresar.getScene().getWindow();
        return stage;
    }

    private void colocarLabels() {
        ArrayList etiquetas = new ArrayList();
        etiquetas.add("Código");
        // Agregar títulos por defecto a las columnas del GridPane
        Label art1 = new Label("Código");
        etiquetas.add("Marca de bicicleta");
        etiquetas.add("Descripción de bicicleta");
        etiquetas.add("Precio");
        etiquetas.add("Fecha de recibido");
        etiquetas.add("Fecha de entrega");
        etiquetas.add("Observaciones");
        etiquetas.add("Estado");

        // Configura las RowConstraints para la fila de títulos
        RowConstraints rowConstraints = new RowConstraints();
        gridInformacion.getRowConstraints().add(0, rowConstraints);

        for (int i = 0; i < 8; i++) {
            Label newLabel = new Label(etiquetas.get(i).toString());
            GridPane.setConstraints(newLabel, i, 0);
            GridPane.setHalignment(newLabel, HPos.CENTER);
            GridPane.setValignment(newLabel, VPos.CENTER);
            gridInformacion.getChildren().add(newLabel);
        }
    }

    private void colocarInformacion(ArrayList elementos) {
        int fila = 1;  // Empieza en la fila siguiente a los títulos
        int columna = 0;  // Empieza en la fila siguiente a los títulos
        int contador = 0;

        // Configura las RowConstraints para cada fila
        //int cantFilas=Math.round(elementos.size());

        for (int i = 0; i < elementos.size(); i++) {
            if (columna == 8) {
                columna = 0;
                contador = 0;
                fila++;
            }
            Node elementoNode;
            if (contador == 2 || contador == 6) {
                // Utiliza un TextArea para los elementos en las posiciones 2 y 6
                TextArea textArea = new TextArea(elementos.get(i).toString());
                textArea.setWrapText(true); // Permite que el texto se envuelva en varias líneas
                textArea.setEditable(false); // Hace que el TextArea sea de solo lectura
                elementoNode = textArea;
            } else if (contador == 7) {
                // Cambia el texto del último elemento booleano
                if (elementos.get(i).equals("true")) {
                    elementoNode = new Label("Abierto");
                } else {
                    elementoNode = new Label("Cerrado");
                }
            } else {
                elementoNode = new Label(elementos.get(i).toString());
            }

            GridPane.setHalignment(elementoNode, HPos.CENTER);
            GridPane.setValignment(elementoNode, VPos.CENTER);
            GridPane.setRowIndex(elementoNode, fila); // Establece la posición de la fila directamente
            GridPane.setColumnIndex(elementoNode, columna);

            

            gridInformacion.getChildren().add(elementoNode); // Mueve esta línea aquí
            contador++;
            columna++;
        }

    }

    public static ArrayList filtrarPorCodigo(ArrayList<servicio> servicios, int filtro) {
        ArrayList<String> elementos = new ArrayList<>();
        for (servicio servicio : servicios) {
            if (servicio.getCodigoCliente() == filtro) {
                ArrayList<String> listaInfo = servicio.mostrarTodo();
                ArrayList<String> subLista = new ArrayList<>(listaInfo.subList(1, listaInfo.size()));
                elementos.addAll(subLista);
            }
        }
        return elementos;

    }

    public static ArrayList filtrarPorNombre(ArrayList<servicio> servicios, String filtro, ArrayList<Cliente> clientes) {
        ArrayList<String> elementos = new ArrayList<>();
        int codigo = buscarCodigoCliente(clientes, filtro);
        return filtrarPorCodigo(servicios, codigo);

    }

    private static int buscarCodigoCliente(ArrayList<Cliente> clientes, String filtro) {
        for (Cliente cliente : clientes) {
            if (cliente.getNombre().equals(filtro)) {
                return cliente.getCodigo();
            }
        }
        return -1;
    }
}
