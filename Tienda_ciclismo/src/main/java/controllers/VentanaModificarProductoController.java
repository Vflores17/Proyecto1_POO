/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controllers;

import clases.articulo;
import static controllers.RegistroArticulosController.esNumerico;
import static controllers.VentanaBuscarProductoController.filtrarPorCodigo;
import static controllers.VentanaBuscarProductoController.filtrarPorNombre;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextInputDialog;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import login.App;

/**
 * FXML Controller class
 *
 * @author Personal
 */
public class VentanaModificarProductoController implements Initializable {

    @FXML
    private Button botRegresar;
    @FXML
    private Text textTipoProducto;
    @FXML
    private Text textNombreArticulo;
    @FXML
    private Text textCategoria;
    @FXML
    private Text textTamanno;
    @FXML
    private Text textMarca;
    @FXML
    private Text textPrecio;
    @FXML
    private Text textCant;
    @FXML
    private Button mostrar;
    @FXML
    private Button botLimpiar;

    private articulo objetoModificar;
    @FXML
    private MenuItem botTipoProducto;
    @FXML
    private MenuItem botNombre;
    @FXML
    private MenuItem botCategoria;
    @FXML
    private MenuItem botTamanno;
    @FXML
    private MenuItem botMarca;
    @FXML
    private MenuItem botPrecio;
    @FXML
    private MenuItem botCantidad;

    private List<String> productosDisponibles;
    private String[] categorias = {"Bicicletas", "Suplementos", "Accesorios"};
    private String[] tamannos = {"12", "16", "22", "26", "27", "27.5", "29"};

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
    private void mostrar() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Mostrar información a modificar.");
        dialog.setHeaderText("Ingrese el artículo a modificar:");

        Optional<String> result = dialog.showAndWait();

        ArrayList<articulo> articulos = App.devolverArticulos();

        result.ifPresent(codigoProducto -> {
            articulo infoMostrar = obtenerObjetoEspecifico(articulos, codigoProducto);
            if (infoMostrar != null) {
                //objetoModificar = infoMostrar;
                objetoModificar = infoMostrar;
                completarLabels(infoMostrar);
            } else {
                objetoModificar = null;
                Alert alert = new Alert(Alert.AlertType.ERROR, "El articulo ingresado no existe.");
                alert.show();
            }

        });

    }

    private void completarLabels(articulo articulo) {
        textTipoProducto.setText(articulo.getNombreProducto());
        textNombreArticulo.setText(articulo.getNombreArticulo());
        textCategoria.setText(articulo.getTipo());
        textTamanno.setText(articulo.getTamanno());
        textMarca.setText(articulo.getMarca());
        textPrecio.setText(String.valueOf(articulo.getPrecio()));
        textCant.setText(String.valueOf(articulo.getCantidad()));

    }

    private articulo obtenerObjetoEspecifico(ArrayList<articulo> articulos, String filtro) {
        System.out.println(filtro);
        for (articulo articulo : articulos) {
            if (filtro.equals(String.valueOf(articulo.getCodigoArticulo())) || filtro.equals(articulo.getNombreArticulo())) {
                return articulo;
            }
        }
        Alert alert = new Alert(Alert.AlertType.ERROR, "El articulo ingresado no existe.");
        alert.show();
        return null;
    }

    @FXML
    private void limpiar() {
        textTipoProducto.setText(" ");
        textNombreArticulo.setText(" ");
        textCategoria.setText(" ");
        textTamanno.setText(" ");
        textMarca.setText(" ");
        textPrecio.setText(String.valueOf(" "));
        textCant.setText(String.valueOf(" "));
        objetoModificar = null;

    }

    @FXML
    private void cambiarTipoProducto(ActionEvent event) {
        productosDisponibles = App.verProductos();
        ChoiceDialog<String> dialog = new ChoiceDialog<>("Productos disponibles", productosDisponibles);
        dialog.setTitle("Nuevo tipo de producto");
        dialog.setHeaderText("Seleccione el nuevo tipo de producto:");

        // Muestra el diálogo y espera que el usuario haga una elección
        Optional<String> result = dialog.showAndWait();

        // Maneja la opción seleccionada (si el usuario hizo una elección)
        result.ifPresent(opcion -> {
            System.out.println(objetoModificar);
            if (objetoModificar != null) {
                objetoModificar.setNombreProducto(opcion);
                completarLabels(objetoModificar);
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Se cambió exitosamente el tipo de producto.");
                alert.show();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Debes seleccionar algun artículo primero.");
                alert.show();
            }
        });
    }

    @FXML
    private void cambiarNombre(ActionEvent event) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Nuevo nombre para el artículo..");
        dialog.setHeaderText("Ingrese el nuevo nombre del artículo:");

        Optional<String> result = dialog.showAndWait();

        ArrayList<articulo> articulos = App.devolverArticulos();

        result.ifPresent(input -> {
            articulo infoMostrar = obtenerObjetoEspecifico(articulos, input);
            if (infoMostrar != null) {
                objetoModificar.setNombreArticulo(input);
                completarLabels(infoMostrar);
            } else {
                objetoModificar = null;
                Alert alert = new Alert(Alert.AlertType.ERROR, "El articulo ingresado no existe.");
                alert.show();
            }

        });
    }

    @FXML
    private void cambiarCategoria(ActionEvent event) {
        ChoiceDialog<String> dialog = new ChoiceDialog<>("Categorias disponibles", categorias);
        dialog.setTitle("Nueva categoria para el artículo");
        dialog.setHeaderText("Seleccione la nueva categoria:");

        // Muestra el diálogo y espera que el usuario haga una elección
        Optional<String> result = dialog.showAndWait();

        // Maneja la opción seleccionada (si el usuario hizo una elección)
        result.ifPresent(opcion -> {
            System.out.println(objetoModificar);
            if (objetoModificar != null) {
                System.out.println(objetoModificar.getTipo());
                System.out.println(opcion);
                System.out.println(objetoModificar.getTipo().equals("Bicicletas"));
                System.out.println(opcion.equals("Bicicletas"));
                if (opcion.equals("Bicicletas") && objetoModificar.getTipo().equals("Bicicletas")) {

                    Alert alert = new Alert(Alert.AlertType.ERROR, "El artículo ya tiene esa categoria asignada.");
                    alert.show();
                } else if (opcion.equals("Bicicletas") && !objetoModificar.getTipo().equals("Bicicletas")) {

                    objetoModificar.setTipo(opcion);
                    ChoiceDialog<String> tamanno = new ChoiceDialog<>("Tamaños", tamannos);
                    tamanno.setTitle("Tamaños disponibles");
                    tamanno.setHeaderText("Seleccione el tamaño de la categoria:");

                    // Muestra el diálogo y espera que el usuario haga una elección
                    Optional<String> input = tamanno.showAndWait();

                    // Maneja la opción seleccionada (si el usuario hizo una elección)
                    input.ifPresent(opcionUser -> {
                        objetoModificar.setTamanno(opcionUser);
                        Alert alert = new Alert(Alert.AlertType.INFORMATION, "La categoria y el tamaño han sido modificados exitosamente.");
                        alert.show();

                    });
                } else {

                    objetoModificar.setTipo(opcion);
                    objetoModificar.setTamanno("0");
                    Alert alert = new Alert(Alert.AlertType.INFORMATION, "La categoria ha sido modificada exitosamente.");
                    alert.show();
                }
                completarLabels(objetoModificar);
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Debes seleccionar algun artículo primero.");
                alert.show();
            }
        });
    }

    @FXML
    private void cambiarTamanno(ActionEvent event) {
        if (objetoModificar.getTipo().equals("Bicicletas")) {
            ChoiceDialog<String> tamanno = new ChoiceDialog<>("Tamaños", tamannos);
            tamanno.setTitle("Tamaños disponibles");
            tamanno.setHeaderText("Seleccione el tamaño de la categoria:");

            // Muestra el diálogo y espera que el usuario haga una elección
            Optional<String> input = tamanno.showAndWait();

            // Maneja la opción seleccionada (si el usuario hizo una elección)
            input.ifPresent(opcionUser -> {
                objetoModificar.setTamanno(opcionUser);
                completarLabels(objetoModificar);
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "El tamaño del artículo ha sido modificado exitosamente.");
                alert.show();
            });
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING, "El artículo seleccionado no cuenta con esta caracteristica disponible.");
            alert.show();
        }
    }

    @FXML
    private void cambiarMarca(ActionEvent event) {
    }

    @FXML
    private void cambiarPrecio(ActionEvent event) {
    }

    @FXML
    private void cambiarCantidad(ActionEvent event) {
    }

}