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
import javafx.geometry.Pos;
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
        // Agregar títulos por defecto a las columnas del GridPane
        Label art1 = new Label("Código del artículo");
        Label art2 = new Label("Nombre del artículo");
        Label art3 = new Label("Categoría");
        Label art4 = new Label("Tamaño");
        Label art5 = new Label("Marca");
        Label art6 = new Label("Precio");
        Label art7 = new Label("Cantidad");
        // Agrega más títulos según sea necesario

        // Configura las posiciones de las etiquetas en el GridPane
        GridPane.setConstraints(art1, 0, 0);
        GridPane.setConstraints(art2, 1, 0);
        GridPane.setConstraints(art3, 2, 0);
        GridPane.setConstraints(art4, 3, 0);
        GridPane.setConstraints(art5, 4, 0);
        GridPane.setConstraints(art6, 5, 0);
        GridPane.setConstraints(art7, 6, 0);
        // Configura más posiciones según sea necesario

        // Agrega las etiquetas al GridPane
        gridInformacion.getChildren().addAll(art1, art2, art3, art4, art5, art6, art7);
        // Agrega más etiquetas según sea necesario

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
            GridPane.setConstraints(label, columna, fila);
            gridInformacion.getChildren().add(label);
            columna++;
        }
    }
}
