/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clases;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Personal
 */
public class tipoProducto {
    
    private String codigo;
    private String nombreProducto;

    public tipoProducto(String codigo, String nombreProducto) {
        this.codigo = codigo;
        this.nombreProducto = nombreProducto;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }
    
    
    public ArrayList infoProducto(){
        ArrayList informacion = new ArrayList();
        informacion.add(this.codigo);
        informacion.add(this.nombreProducto);
        return informacion;
    }
    
    
}
