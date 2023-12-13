/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clases;

import java.util.ArrayList;

/**
 *
 * @author Personal
 */
public class articulo extends tipoProducto {
    
    private String codigoArticulo;
    private String nombreArticulo;
    private String tipo;
    private String tamanno;
    private String marca;
    private int precio;
    private int cantidad;

    public articulo(String codigoArticulo, String nombreArticulo, String tipo, String tamanno, String marca, int precio, int cantidad, String codigo, String nombreProducto) {
        super(codigo, nombreProducto);
        this.codigoArticulo = codigoArticulo;
        this.nombreArticulo = nombreArticulo;
        this.tipo = tipo;
        this.tamanno = tamanno;
        this.marca = marca;
        this.precio = precio;
        this.cantidad = cantidad;
    }

    
    public String getCodigoArticulo() {
        return codigoArticulo;
    }

    public void setCodigoArticulo(String codigoArticulo) {
        this.codigoArticulo = codigoArticulo;
    }

    public String getNombreArticulo() {
        return nombreArticulo;
    }

    public void setNombreArticulo(String nombreArticulo) {
        this.nombreArticulo = nombreArticulo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getTamanno() {
        return tamanno;
    }

    public void setTamanno(String tamanno) {
        this.tamanno = tamanno;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    
    public ArrayList mostrarTodo(){
        
        ArrayList info = super.infoProducto();
        info.add(this.codigoArticulo);
        info.add(this.nombreArticulo);
        info.add(this.tipo);
        info.add(this.tamanno);
        info.add(this.marca);
        info.add(this.precio);
        info.add(this.cantidad);
        return info;
        
    }
}
