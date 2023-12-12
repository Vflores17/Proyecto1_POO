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
    private static ArrayList<articulo> infoArticulos = new ArrayList();

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
        
        articulo newarticulo = new articulo(1, "MTB","Bicicletas","27","Yamaha",12345,12,1,"Bicicletas");
        articulo newarticulo1 = new articulo(2, "Frenos de tambor","Bicicletas","22","Triciclo",159,5,1,"Bicicletas");
        articulo newarticulo2 = new articulo(3, "Esteroides","Suplementos","0","Anabolicos",1347,8,3,"Accesorios");
        infoArticulos.add(newarticulo);
        infoArticulos.add(newarticulo1);
        infoArticulos.add(newarticulo2);

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

    public static ArrayList<String> verProductos() {
        ArrayList<String> disponibles = new ArrayList<>();
        for (tipoProducto producto : infoProductos) {
            disponibles.add(producto.getNombreProducto());
        }
        System.out.println(disponibles);
        return disponibles;

    }
    
    public static int cantProductos(){
        return infoProductos.size();
    }
    
    public static int buscarCodigoProducto(String producto){
        for (tipoProducto elemento : infoProductos){
            if (elemento.getNombreProducto().equals(producto)){
                return elemento.getCodigo();
            }
        }
        return -1;
    }
    
    public static int cantArticulos(){
        return infoArticulos.size();
    }
}
