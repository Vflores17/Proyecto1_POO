package archivos;
import clases.Cliente;
import clases.Factura;
import java.io.*;
import java.util.Hashtable;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;

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
    
    public static void guardarCliente(List<Cliente> clientes) {
        ObjectMapper objectMapper = new ObjectMapper();
        // Especifica la ruta relativa del archivo (respecto al directorio de trabajo actual)

        try {
            // Guarda la lista de clientes en un archivo JSON
            File file = new File("src/main/java/archivos/archivoscliente.json");
            objectMapper.writeValue(file, clientes);

            System.out.println("Clientes guardados en archivoscliente.json");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /***
     * Método para guardar la información de las facturas registradas en el programa.
     * 
     * @param factura Lista de objetos Factura para guardar en disco.
     */
    public static void guardarFactura(List<Factura> factura) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        try {
            File file = new File("src/main/java/archivos/archivosfacturas.json");
            System.out.println(factura);
            objectMapper.writeValue(file, factura);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
    
