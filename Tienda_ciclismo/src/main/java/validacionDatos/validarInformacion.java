/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package validacionDatos;

import java.util.Dictionary;
import java.util.Hashtable;

/**
 *
 * @author Personal
 */
public class validarInformacion {
    
    public static boolean loginUsuario(String usuario, String contra, Dictionary credenciales){
        if (((Hashtable)credenciales).containsKey(usuario)) {
            if ((credenciales.get(usuario)).equals(contra)) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    
    }
}
