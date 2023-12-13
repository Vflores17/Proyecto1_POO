package archivos;

import clases.Cliente;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Dictionary;
import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Personal
 */
public class cargarArchivo {

    public static Map<String, String> cargarInformacion(String archivo) {
        Map<String, String> credenciales = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;

            // Lee cada línea del archivo CSV
            while ((linea = br.readLine()) != null) {
                // Divide la línea en campos utilizando una coma como delimitador
                String[] campos = linea.split(",");

                // Suponiendo que el archivo CSV tiene dos columnas (usuario y contraseña)
                if (campos.length == 2) {
                    String usuario = campos[0].trim();
                    String contrasena = campos[1].trim();
                    credenciales.put(usuario, contrasena);
                }
            }

        } catch (Exception e) {
            System.out.println("Error al leer el archivo. " + e.getMessage());
        }

        return credenciales;
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

    public static List<Cliente> leerClientes() {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            // Lee el JSON desde el archivo y convierte a lista de clientes
            File file = new File("src/main/java/archivos/archivoscliente.json");

            if (!file.exists()) {
                // Si el archivo no existe, devuelve una lista vacía
                System.out.println("El archivo no existe. Devolviendo una lista vacía.");
                return new ArrayList<>();
            }

            List<Cliente> clientes = objectMapper.readValue(file, new TypeReference<List<Cliente>>() {});
            System.out.println("Clientes leídos desde archivoscliente.json");
            return clientes;
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}
