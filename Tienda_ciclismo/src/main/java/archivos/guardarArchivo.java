package archivos;

//Módulo de importaciones
import clases.Cliente;
import java.io.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;

/**
 * Clase para guardar información en los archivos
 * 
 * @author Vidal Flores
 */
public class guardarArchivo {
    
    /***
     * Método para guardar la información de los clientes registrados en el programa.
     * 
     * @param clientes Lista de objetos Cliente para guardar en disco.
     */
    public static void guardarCliente(List<Cliente> clientes) {
        ObjectMapper objectMapper = new ObjectMapper();
        
        try {
            // Guarda la lista de clientes en un archivo JSON
            File file = new File("src/main/java/archivos/archivoscliente.json");
            objectMapper.writeValue(file, clientes);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
    
