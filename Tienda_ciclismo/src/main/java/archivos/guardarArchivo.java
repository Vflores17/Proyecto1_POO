/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package archivos;
import java.io.*;
import java.util.Hashtable;

/**
 *
 * @author Personal
 */
public class guardarArchivo {
    
    public static void guardarInformacion(Hashtable credenciales){
    
        File archivo = new File("usuarios.dat");
         
        try {
            // Para poder escribir utilizaremos un FileOutputStream pasandole
            // como referencia el archivo de tipo File.
            FileOutputStream fos = new FileOutputStream(archivo);
             
            // Y crearemos también una instancia del tipo ObjectOutputStream
            // al que le pasaremos por parámetro
            // el objeto de tipo FileOutputStream
            ObjectOutputStream escribir = new ObjectOutputStream(fos);
             
            // Escribimos los objetos en el archivo.
            escribir.writeObject(credenciales);
             
            // Cerramos los objetos para no consumir recursos.
            escribir.close();
            fos.close();
            
            System.out.println("guardado exitoso del archivo binario");
             
        } catch (Exception e) {
            System.out.println("Error al escribir en el archivo. "
                    + e.getMessage());   
        }
    }
    
}
