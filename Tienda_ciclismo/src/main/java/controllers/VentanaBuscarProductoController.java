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
    private ListView<String> listViewElementos;
    @FXML
    private Button botRegresar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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
                System.out.println(codigoProducto);

                ArrayList elementosMostrar = filtrarPorCodigo(articulos, Integer.parseInt(codigoProducto));
                if (!elementosMostrar.isEmpty()) {
                    listViewElementos.getItems().clear();
                    listViewElementos.getItems().addAll(elementosMostrar);
                } else {
                    listViewElementos.getItems().clear();
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
                    listViewElementos.getItems().clear();
                    listViewElementos.getItems().addAll(elementosMostrar);
                } else {
                    listViewElementos.getItems().clear();
                    Alert alert = new Alert(Alert.AlertType.INFORMATION, "No se han encontrado productos con el valor ingresado.");
                    alert.show();
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Debes el nombre del producto a buscar.");
                alert.show();
            }
        });
    }

    private static ArrayList filtrarPorCodigo(ArrayList<articulo> articulos, int filtro) {
        ArrayList<String> elementos = new ArrayList<>();
        for (articulo producto : articulos) {
            if (producto.getCodigo() == filtro) {
                ArrayList listaInfo = producto.mostrarTodo();
                String infoAMostrar = darFormatoString(listaInfo);
                elementos.add(infoAMostrar);
            }
        }
        return elementos;

    }

    private static ArrayList filtrarPorNombre(ArrayList<articulo> articulos, String filtro) {
        ArrayList<String> elementos = new ArrayList<>();
        for (articulo articulo : articulos) {
            if (articulo.getNombreProducto().equals(filtro)) {
                ArrayList listaInfo = articulo.mostrarTodo();
                String infoAMostrar = darFormatoString(listaInfo);
                elementos.add(infoAMostrar);
            }
        }
        return elementos;

    }

    public static String darFormatoString(ArrayList lista) {
        String info = "Código artículo: " + lista.get(2) + " - Nombre: " + lista.get(3);
        info += " - Marca: " + lista.get(6)+" - Categoria: " + lista.get(1);
        info+= " - Precio: " + lista.get(7) + " - Cantidad: " + lista.get(8);
        info+= " - Nombre producto: "+ lista.get(1);
        System.out.println(lista.get(5));
        if (!lista.get(5).equals("0")){
            info+=" - Tamaño: "+ lista.get(5);
        }
        return info;
    }
}
