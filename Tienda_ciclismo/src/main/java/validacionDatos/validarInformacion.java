/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package validacionDatos;

import java.util.Dictionary;
import java.util.HashMap;
import java.util.Hashtable;

/**
 *
 * @author Personal
 */
public class validarInformacion {
    
    public static boolean loginUsuario(String usuario, String contra, HashMap<String, String> credenciales) {
        // Verifica si el usuario existe en el HashMap
        if (credenciales.containsKey(usuario)) {
            // Verifica si la contraseña coincide
            if (credenciales.get(usuario).equals(contra)) {
                return true; // Credenciales correctas
            }
        } 
        return false;
    }
}
