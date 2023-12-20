package controllers;

import clases.Cliente;
import clases.Factura;
import clases.articulo;
import clases.servicio;
import clases.tipoProducto;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.control.Alert;

import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import login.App;

/**
 * FXML Controller class
 *
 * @author Dilan
 */
public class AgregarFacturaController implements Initializable {

    @FXML
    private DatePicker fechaFactura;
    @FXML
    private ComboBox<String> comboCliente;
    @FXML
    private ComboBox<String> comboProducto;
    @FXML
    private TextField cantidadProducto;
    @FXML
    private ComboBox<String> comboServicio;
    @FXML
    private Label labelSubTotal;
    @FXML
    private Label IVA;
    @FXML
    private Label labelTotal;
    @FXML
    private GridPane gridPaneProductos;

    private List<Cliente> listClientes = App.getClientes();
    private ArrayList<tipoProducto> listProducto = App.devolverInfo();
    private ArrayList<articulo> listArticulo = App.devolverArticulos();
    private ArrayList<servicio> listServicio = App.getServicios();
    private ArrayList<Integer> listaCodigosArticulos = new ArrayList();
    private List<Integer> listaCodigosServicios = new ArrayList();
    private int j = 1;
    private int subtotal = 0;
    private int iva = 0;
    private int total = 0;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ArrayList etiquetas = new ArrayList();

        etiquetas.add("Descripción");
        etiquetas.add("Cantidad");
        etiquetas.add("Precio unitario");
        etiquetas.add("Precio total");
        for (int i = 0; i < 4; i++) {
            Label newLabel = new Label(etiquetas.get(i).toString());
            GridPane.setConstraints(newLabel, i, 0);
            GridPane.setHalignment(newLabel, HPos.CENTER);
            GridPane.setValignment(newLabel, VPos.CENTER);
            gridPaneProductos.getChildren().add(newLabel);

        }
        for (Cliente cliente : listClientes) {
            comboCliente.getItems().add("Código: " + cliente.getCodigo() + " : Cliente " + cliente.getNombre() + " " + cliente.getApellido());
        }

        for (articulo Articulo : listArticulo) {
            for (tipoProducto Producto : listProducto) {
                if (Producto.getNombreProducto().equals(Articulo.getNombreProducto())) {
                    comboProducto.getItems().add(Producto.getNombreProducto() + ": " + Articulo.getNombreArticulo());
                }
            }
        }

        for (servicio Servicio : listServicio) {
            for (Cliente cliente : listClientes) {
                if (cliente.getCodigo() == Servicio.getCodigoCliente()) {
                    comboServicio.getItems().add("Servicio de mantenimiento:" + Servicio.getMarcaBici() + ": De:" + cliente.getNombre() + " " + cliente.getApellido());
                }
            }
        }

        fechaFactura.setValue(LocalDate.now());

    }

    @FXML
    private void generarFactura(ActionEvent event) {
        if (comboCliente.getValue() != null && (!listaCodigosArticulos.isEmpty() || !listaCodigosServicios.isEmpty())) {
            for (Cliente cliente : listClientes) {
                if (cliente.getCodigo() == App.obtenerCodigoCliente(comboCliente.getValue())) {
                    for (servicio Servicio : listServicio) {
                        for (Integer codigos : listaCodigosServicios) {
                            if (Servicio.getCodigoServicio() == codigos) {
                                Servicio.setEstado(false);
                            }
                        }

                    }
                    Factura new_factura = new Factura(App.cantFactura(),cliente.getCodigo(),getFecha(), "Válida", total, listaCodigosArticulos,listaCodigosServicios);
                    App.guardarFactura(new_factura);
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Se ha generado la factura correctamente");
                    alert.show();
                }
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Elija un cliente.");
            alert.show();
        }
    }

    @FXML
    private void agregarArticulos(ActionEvent event) {
        if (comboProducto.getValue() != null && cantidadProducto.getText() != null && !cantidadProducto.getText().isEmpty()) {
            for (articulo Articulo : listArticulo) {
                if (App.obtenerNombre(comboProducto.getValue(), 1).equals(Articulo.getNombreArticulo())) {
                    ArrayList mostrar = new ArrayList();
                    mostrar.add(Articulo.getNombreProducto() + " " + Articulo.getNombreArticulo());
                    mostrar.add(cantidadProducto.getText());
                    mostrar.add(Integer.valueOf(Articulo.getPrecio()));
                    mostrar.add(Integer.parseInt(cantidadProducto.getText()) * Articulo.getPrecio());
                    subtotal += (int) Integer.parseInt(cantidadProducto.getText()) * Articulo.getPrecio();
                    iva = (int) ((subtotal * 13) / 100);
                    total = iva + subtotal;
                    labelSubTotal.setText("Subtotal:" + subtotal);
                    IVA.setText("IVA:" + iva);
                    labelTotal.setText("Total:" + total);
                    for (int i = 0; i < 4; i++) {
                        Label newLabel = new Label(mostrar.get(i).toString());
                        GridPane.setConstraints(newLabel, i, j);
                        GridPane.setHalignment(newLabel, HPos.CENTER);
                        GridPane.setValignment(newLabel, VPos.CENTER);
                        gridPaneProductos.getChildren().add(newLabel);
                    }
                    listaCodigosArticulos.add(Articulo.getCodigo());
                    j++;
                    break;
                }
            }
        }
    }

    @FXML
    private void agregarServicio(ActionEvent event) {
        if (comboServicio.getValue() != null) {
            for (servicio Servicio : listServicio) {
                for (Cliente cliente : listClientes) {
                    if (App.obtenerNombre(comboServicio.getValue(), 3).equals(cliente.getNombre() + " " + cliente.getApellido()) && App.obtenerNombre(comboServicio.getValue(), 1).equals(Servicio.getMarcaBici())) {
                        subtotal += Servicio.getPrecio();
                        iva = (int) (subtotal * 13) / 100;
                        total = iva + subtotal;
                        ArrayList mostrar = new ArrayList();
                        mostrar.add(comboServicio.getValue().toString());
                        mostrar.add(1);
                        mostrar.add(Servicio.getPrecio());
                        mostrar.add(Servicio.getPrecio());
                        labelSubTotal.setText("Subtotal:" + subtotal);
                        IVA.setText("IVA:" + iva);
                        labelTotal.setText("Total:" + total);
                        for (int i = 0; i < 4; i++) {
                            Label newLabel = new Label(mostrar.get(i).toString());
                            GridPane.setConstraints(newLabel, i, j);
                            GridPane.setHalignment(newLabel, HPos.CENTER);
                            GridPane.setValignment(newLabel, VPos.CENTER);
                            gridPaneProductos.getChildren().add(newLabel);
                        }
                        listaCodigosServicios.add(Servicio.getCodigoServicio());
                        j++;
                        break;
                    }
                }
            }
        }
    }

    @FXML
    private void regresar(ActionEvent event) throws IOException {
        App.cambiarVista(getStage(), "registroFactura");
    }

    /**
     * *
     * Método para obtener el stage actual de la ventana.
     *
     * @return stage de la ventana actual
     */
    private Stage getStage() {
        Stage stage = (Stage) cantidadProducto.getScene().getWindow();
        return stage;
    }
    
    private LocalDate getFecha() {
        LocalDate fecha = fechaFactura.getValue();
        return fecha;
    }

}