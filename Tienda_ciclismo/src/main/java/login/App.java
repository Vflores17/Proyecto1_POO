package login;

import archivos.cargarArchivo;
import archivos.guardarArchivo;
import clases.Cliente;
import clases.Factura;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import clases.articulo;

import java.io.*;
import java.io.IOException;
import java.util.ArrayList;
import clases.articulo;
import clases.tipoProducto;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    private static ArrayList<tipoProducto> infoProductos = new ArrayList();
    private static ArrayList<articulo> infoArticulos = new ArrayList();
    private static ArrayList<servicio> infoServicios = new ArrayList();
    private static List<Cliente> infoClientes = new ArrayList();
    private static List<Factura> infoFactura = new ArrayList();


    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("ventanalogin"), 590, 400);
        stage.setScene(scene);
        stage.show();

    }

    static void cambiarVentana(Stage stage, String fxml) throws IOException {
        Parent root = loadFXML(fxml);
        Scene newScene = new Scene(root);
        stage.setScene(newScene);
        stage.show();
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {

        infoClientes = cargarArchivo.leerClientes();
        infoFactura = cargarArchivo.leerFactura();
        
        tipoProducto newObjeto = new tipoProducto(1, "Zapatos");
        tipoProducto newObjeto1 = new tipoProducto(2, "Frenos");
        tipoProducto newObjeto2 = new tipoProducto(3, "Transmisiones");
        tipoProducto newObjeto3 = new tipoProducto(4, "Marcos");
        tipoProducto newObjeto4 = new tipoProducto(5, "Alimentos");
        infoProductos.add(newObjeto);
        infoProductos.add(newObjeto1);
        infoProductos.add(newObjeto2);
        infoProductos.add(newObjeto3);
        infoProductos.add(newObjeto4);

        articulo newarticulo = new articulo(1, "Upline", "Accesorios", "0", "Upline", 44000, 5, 1, "Zapatos");
        articulo newarticulo1 = new articulo(2, "FLR", "Accesorios", "0", "FLR mtb", 33300, 6, 1, "Zapatos");
        articulo newarticulo2 = new articulo(3, "Sponser Competition", "Suplementos", "0", "Sponser", 20100, 3, 5, "Alimentos");
        articulo newarticulo3 = new articulo(4, "XDS", "Bicicletas", "27", "LTW00 R9", 570000, 5, 4, "Marcos");
        articulo newarticulo4 = new articulo(5, "Pasador Deore", "Accesorios", "0", "Shimano", 37800, 4, 3, "Transmisiones");
        infoArticulos.add(newarticulo);
        infoArticulos.add(newarticulo1);
        infoArticulos.add(newarticulo2);
        infoArticulos.add(newarticulo3);
        infoArticulos.add(newarticulo4);

        launch();

    }

    public static void cambiarVista(Stage stage, String fxml) throws IOException {
        cambiarVentana(stage, fxml);
    }

    public static ArrayList devolverInfo() {
        return infoProductos;
    }

    public static void guardarProducto(tipoProducto objeto) {
        infoProductos.add(objeto);
    }

    public static void guardarArticulo(articulo objeto) {
        infoArticulos.add(objeto);
    }

    public static ArrayList<String> verProductos() {
        ArrayList<String> disponibles = new ArrayList<>();
        for (tipoProducto producto : infoProductos) {
            disponibles.add(producto.getNombreProducto());
        }
        System.out.println(disponibles);
        return disponibles;

    }


    /**
     * Método para devolver la cantidad de productos
     *
     * @return infoProductos el largo de la lista Productos
     */

    public static int cantProductos() {
        return infoProductos.size();
    }


    /**
     * Método para devolver la cantidad de productos
     *
     * @return infoFactura el largo de la lista de facturas
     */
    public static int cantFactura() {
        return infoFactura.size();
    }
    
    /**
     * Método para buscar el código de un producto.
     *
     * @param producto el nombre del producto
     * @return código del producto.
     */
    public static int buscarCodigoProducto(String producto) {
        for (tipoProducto elemento : infoProductos) {
            if (elemento.getNombreProducto().equals(producto)) {
                return elemento.getCodigo();
            }
        }
        return -1;
    }

    public static int cantArticulos() {
        return infoArticulos.size();
    }

    public static ArrayList devolverArticulos() {
        return infoArticulos;
    }

    /**
     * Método para devolver el ArrayList de los clientes.
     *
     * @return ArrayList con los objetos cliente.
     */
    public static ArrayList devolverClientes() {
        List<Cliente> clientes = cargarArchivo.leerClientes();
        return (ArrayList) clientes;
    }

    /**
     * Método para obtener el ArrayList con los servicios.
     *
     * @return ArrayList con los objetos servicio.
     */
    public static ArrayList getServicios() {
        return infoServicios;
    }

    /**
     * Método para devolver la cantidad de servicios.
     *
     * @return la cantidad de servicios.
     */
    public static int cantServicios() {
        return infoServicios.size();
    }

    /**
     * Método para guardar un servicio nuevo
     *
     * @param newServicio objeto servicio a guardar
     */
    public static void guardarServicio(servicio newServicio) {
        infoServicios.add(newServicio);
    }

    /**
     * Método para guardar un nuevo cliente.
     *
     * @param newCliente objeto cliente a guardar
     */
    public static void guardarCliente(Cliente newCliente) {
        infoClientes.add(newCliente);
        System.out.println(newCliente.toString());
        guardarArchivo.guardarCliente(infoClientes);
    }
    
    /**
     * Método para guardar una nueva factura.
     *
     * @param newFactura objeto Factura a guardar
     */
    public static void guardarFactura(Factura newFactura) {
        infoFactura.add(newFactura);
        System.out.println(newFactura.toString());
        guardarArchivo.guardarFactura(infoFactura);
    }

    /**
     * Método devolver el ArrayList de los clientes
     *
     * @return ArrayList con los objetos cliente
     */
    public static List<Cliente> getClientes() {
        return infoClientes;
    }

    /**
     * Método para modificar un cliente.
     *
     * @param indice posicion del objeto en el ArrayList.
     * @param newCliente nuevo objeto cliente a modificar.
     */
    public static void modificarCliente(int indice, Cliente newCliente) {
        infoClientes.set(indice, newCliente);
        archivos.guardarArchivo.guardarCliente(infoClientes);
    }
    
    /**
     * *
     * Método para extraer el código del cliente del string mostrado al usuario.
     *
     * @param cadenaCodigo String del comboBox mostrado al usuario.
     * @return El código del cliente seleccionado.
     */
    public static int obtenerCodigoCliente(String cadenaCodigo) {

        String[] partes = cadenaCodigo.split(":");
        String codigoCliente = partes[1].trim();
        int codigo = Integer.parseInt(codigoCliente);

        return codigo;
    }
    
    /**
     * *
     * Método para extraer el nombre de un producto o servicio del cliente del string mostrado al usuario.
     *
     * @param cadenaCodigo String del comboBox mostrado al usuario.
     * @param i entero con la posicion del nnombre
     * @return El nombre buscado.
     */
    public static String obtenerNombre(String cadenaCodigo, int i) {

        String[] partes = cadenaCodigo.split(":");
        String nombreBuscado = partes[i].trim();

        return nombreBuscado;
    }

}
