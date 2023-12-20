package controllers;

import clases.Cliente;
import clases.Factura;
import clases.articulo;
import clases.servicio;
import clases.tipoProducto;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import login.App;

/**
 * FXML Controller class
 *
 * @author Dylan Meza
 */
public class BuscarFacturaCodigoNombreController implements Initializable {

    @FXML
    private Label nombre;
    @FXML
    private Button btBuscar;
    @FXML
    private TextField obtenerDato;
    @FXML
    private Button botLimpiar;
    @FXML
    private Button botAnular;
    @FXML
    private GridPane descripcionProducto;
    @FXML
    private Label total;

    private int j = 1;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ArrayList etiquetas = new ArrayList();

        etiquetas.add("Descripción");
        etiquetas.add("Cantidad");
        etiquetas.add("Precio");
        for (int i = 0; i < 3; i++) {
            Label newLabel = new Label(etiquetas.get(i).toString());
            GridPane.setConstraints(newLabel, i, 0);
            GridPane.setHalignment(newLabel, HPos.CENTER);
            GridPane.setValignment(newLabel, VPos.CENTER);
            descripcionProducto.getChildren().add(newLabel);

        }
    }

    /**
     * Metodo para regresar a la ventana anterior
     *
     * @param event
     * @throws IOException Excepciones en el caso de que falle alguna llamada a
     * otros métodos
     */
    @FXML
    private void regresar(ActionEvent event) throws IOException {
        App.cambiarVista(App.getStage(botAnular), "registroFactura");
    }

    /**
     * Metodo que se encarga de realizar la busqueda
     *
     * @param event
     */
    @FXML
    private void mostrarBusqueda(ActionEvent event) {
        String dato = obtenerDato.getText();
        boolean existe = false;
        if (dato != null && !dato.isEmpty()) {
            List<Cliente> clientes = App.getClientes();
            List<Factura> facturas = App.getFactura();
            ArrayList<servicio> Servicios = App.getServicios();
            ArrayList<articulo> Articulos = App.devolverArticulos();

            try {
                for(Factura factura : facturas) {
                    if(factura.getNumeroFactura() == Integer.parseInt(dato.strip())) {
                        for(Cliente cliente : clientes){
                            if (cliente.getCodigo() == factura.getCodigoCliente()) {
                                nombre.setText("Nombre de cliente: " + cliente.getNombre()+ " " + cliente.getApellido());
                                break;
                            }
                        }
                        existe = true;
                        List<Integer> codigosArticulos = factura.getCodigoArticulo();
                        List<Integer> codigosServicios = factura.getCodigoServicio();
                        for(Integer codigo : codigosArticulos) {
                            for(articulo elementoArticulo : Articulos) {
                                if(codigo.equals(elementoArticulo.getCodigoArticulo())) {
                                    System.out.println(elementoArticulo.getNombreProducto() + elementoArticulo.getNombreArticulo());
                                }
                            }
                        }
                        for(Integer codigo : codigosServicios) {
                            for(servicio elementoServicio : Servicios){
                                if(codigo.equals(elementoServicio.getCodigoServicio())){
                                    ArrayList mostrar = new ArrayList();
                                    mostrar.add("Servicio de mantenimiento:" + elementoServicio.getMarcaBici());
                                    mostrar.add(1);
                                    mostrar.add(elementoServicio.getPrecio());
                                    for (int i = 0; i < 3; i++) {
                                        Label newLabel = new Label(mostrar.get(i).toString());
                                        GridPane.setConstraints(newLabel, i, j);
                                        GridPane.setHalignment(newLabel, HPos.CENTER);
                                        GridPane.setValignment(newLabel, VPos.CENTER);
                                        descripcionProducto.getChildren().add(newLabel);
                                    }
                                    j++;
                                    break;
                                }
                            }
                        }
                    }

                }
            } catch (NumberFormatException e) {
                for(Cliente cliente : clientes){
                    if(cliente.getNombre().equals(dato.strip())) {
                        existe = true;
                        nombre.setText("Nombre de cliente: " + cliente.getNombre()+ " " + cliente.getApellido());    
                    }
                }
            }
            if(!existe) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Factura no encontrada.");
                alert.show();
            }

        }
    }

    /**
     * Metodo para limpiar la informacion en pantalla
     *
     * @param event
     */
    @FXML
    private void limpiar(ActionEvent event) {
    }

    /**
     * Metodo para anular una factura
     *
     * @param event
     */
    @FXML
    private void anularFactura(ActionEvent event) {
    }

}
