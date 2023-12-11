package login;

import archivos.cargarArchivo;
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

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    private static ArrayList<tipoProducto> infoProductos = new ArrayList();
    private static ArrayList<tipoProducto> infoArticulos = new ArrayList();

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

        tipoProducto newObjeto = new tipoProducto(1, "Bicicletas");
        tipoProducto newObjeto1 = new tipoProducto(2, "Suplementos");
        tipoProducto newObjeto2 = new tipoProducto(3, "Accesorios");
        infoProductos.add(newObjeto);
        infoProductos.add(newObjeto1);
        infoProductos.add(newObjeto2);

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

    public static void verProductos() {

        for (tipoProducto producto : infoProductos) {
            System.out.println(producto.getNombreProducto());
        }

    }
    
    public static int cantProductos(){
        return infoProductos.size();
    }

}
