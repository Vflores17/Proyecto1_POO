/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controllers;

import clases.articulo;
import clases.tipoProducto;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Stage;
import login.App;
import controllers.RegistroArticulosController;
import static controllers.RegistroArticulosController.esNumerico;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;

/**
 * FXML Controller class
 *
 * @author Personal
 */
public class VentanaBuscarProductoController implements Initializable {

    @FXML
    private MenuItem busqCodProducto;
    @FXML
    private MenuItem busqNombProducto;
    @FXML
    private Button botRegresar;
    @FXML
    private GridPane gridInformacion;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    private Stage getStage() {
        Stage stage = (Stage) botRegresar.getScene().getWindow();
        return stage;
    }

    @FXML
    private void regresar(ActionEvent event) throws IOException {
        App.cambiarVista(getStage(), "registroProductos");
    }

    @FXML
    public void busquedaCodigoProducto() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Búsqueda por Código de Artículos.");
        dialog.setHeaderText("Ingrese el código del artículo:");

        Optional<String> result = dialog.showAndWait();

        result.ifPresent(codigoProducto -> {
            if (esNumerico(codigoProducto)) {
                ArrayList articulos = App.devolverArticulos();
                System.out.println(articulos);

                ArrayList elementosMostrar = filtrarPorCodigo(articulos, Integer.parseInt(codigoProducto));
                gridInformacion.getChildren().clear();
                if (!elementosMostrar.isEmpty()) {
                    colocarLabels();
                    colocarInformacion(elementosMostrar);
                } else {
                    //listViewElementos.getItems().clear();
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
    public void busquedaNombreProducto() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Búsqueda por Nombre de Producto");
        dialog.setHeaderText("Ingrese el nombre del producto:");

        Optional<String> result = dialog.showAndWait();
        
        result.ifPresent(codigoProducto -> {
            if (!codigoProducto.isEmpty()) {
                ArrayList productos = App.devolverArticulos();
                System.out.println(codigoProducto);

                ArrayList elementosMostrar = filtrarPorNombre(productos, codigoProducto);
                if (!elementosMostrar.isEmpty()) {
                    colocarLabels();
                    colocarInformacion(elementosMostrar);

                } else {
                    //listViewElementos.getItems().clear();
                    Alert alert = new Alert(Alert.AlertType.INFORMATION, "No se han encontrado productos con el valor ingresado.");
                    alert.show();
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Debes el nombre del producto a buscar.");
                alert.show();
            }
        }
        );
    }

    public static ArrayList filtrarPorCodigo(ArrayList<articulo> articulos, int filtro) {
        ArrayList<String> elementos = new ArrayList<>();
        for (articulo producto : articulos) {
            if (producto.getCodigo() == filtro) {
                ArrayList<String> listaInfo = producto.mostrarTodo();
                System.out.println(listaInfo + "listainfo");
                ArrayList<String> subLista = new ArrayList<>(listaInfo.subList(2, listaInfo.size()));
                elementos.addAll(subLista);
                System.out.println(subLista + "sublista");
                //String infoAMostrar = ordenarLista(listaInfo);
                //elementos.add(infoAMostrar);
            }
        }
        return elementos;

    }

    public static ArrayList filtrarPorNombre(ArrayList<articulo> articulos, String filtro) {
        ArrayList<String> elementos = new ArrayList<>();
        for (articulo articulo : articulos) {
            if (articulo.getNombreProducto().equals(filtro)) {
                ArrayList<String> listaInfo = articulo.mostrarTodo();
                System.out.println(listaInfo + "listainfo");
                ArrayList<String> subLista = new ArrayList<>(listaInfo.subList(2, listaInfo.size()));
                elementos.addAll(subLista);
                System.out.println(subLista + "sublista");
            }
        }
        return elementos;

    }

    private void colocarLabels() {
        
         ArrayList etiquetas = new ArrayList();
         
        // Agregar títulos por defecto a las columnas del GridPane
        etiquetas.add("Código del artículo");
        etiquetas.add("Nombre del artículo");
        etiquetas.add("Categoría");
        etiquetas.add("Tamaño");
        etiquetas.add("Marca");
        etiquetas.add("Precio");
        etiquetas.add("Cantidad");
        // Agrega más títulos según sea necesario
        for (int i = 0; i < 7; i++) {
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
        for (Object elemento : elementos) {
            Label label = new Label(elemento.toString());
            if (columna == 7) {
                columna = 0;
                fila++;
            }
            GridPane.setHalignment(label, HPos.CENTER);
            GridPane.setValignment(label, VPos.CENTER);
            GridPane.setConstraints(label, columna, fila);
            gridInformacion.getChildren().add(label);
            columna++;
        }
    }
}
