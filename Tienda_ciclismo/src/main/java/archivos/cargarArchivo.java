/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package archivos;
import java.util.Dictionary;
import java.io.*;
import java.util.ArrayList;

/**
 *
 * @author Personal
 */
public class cargarArchivo {

    public static Dictionary cargarInformacion(String archivo){
        
    try {
            // Para poder leer utilizaremos un FileInputStream pasandole
            // como referencia el archivo de tipo File.
            FileInputStream fis = new FileInputStream(archivo);
             
            // Declaramos una variable objeto del tipo ObjectInputStream
            ObjectInputStream leer;
             
            // Creamos un bucle para leer la información
            // Mientras haya bytes en el archivo.
            while(fis.available()>0){
                leer= new ObjectInputStream(fis);
                 
                // En una variable objeto de tipo Persona almacenaremos
                // el objeto leido de tipo Object convertido en un objeto
                // de tipo persona
                Dictionary credenciales = (Dictionary) leer.readObject();
                 
                // Imprimimos el objeto leido en consola
                return credenciales;   
            }
             
        } catch (Exception e) {
            System.out.println("Error al leer el archivo. "
                    + e.getMessage());   
        }
        return null;
    }
    
    public static ArrayList cargarListas(String archivo){
        
    try {
            // Para poder leer utilizaremos un FileInputStream pasandole
            // como referencia el archivo de tipo File.
            FileInputStream fis = new FileInputStream(archivo);
             
            // Declaramos una variable objeto del tipo ObjectInputStream
            ObjectInputStream leer;
             
            // Creamos un bucle para leer la información
            // Mientras haya bytes en el archivo.
            while(fis.available()>0){
                leer= new ObjectInputStream(fis);
                 
                // En una variable objeto de tipo Persona almacenaremos
                // el objeto leido de tipo Object convertido en un objeto
                // de tipo persona
                ArrayList informacion = (ArrayList) leer.readObject();
                 
                // Imprimimos el objeto leido en consola
                return informacion;   
            }
             
        } catch (Exception e) {
            System.out.println("Error al leer el archivo. "
                    + e.getMessage());
            ArrayList informacion = new ArrayList();
            return informacion;
        }
        return null;
    }
        
    
}
