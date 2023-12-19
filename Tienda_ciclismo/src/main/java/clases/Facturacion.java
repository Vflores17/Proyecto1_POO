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
public class Facturacion {
    private int numFactura;
    private int codCliente; 
    private int fecha;
    private String estado;
    private int subtotal;
    private int impuesto;
    private int total;
    

    public Facturacion(int numFactura, int codCliente, int subtotal, int impuesto, int total ) {
        this.numFactura = numFactura;
        this.codCliente = codCliente;
        this.subtotal = subtotal;
        this.impuesto = impuesto;
        this.total = total;
    }
       
    public int getnumFactura() {
        return numFactura;
    }

    public void setnumFactura(int numFactura) {
        this.numFactura = numFactura;
    }

    public int getcodCliente() {
        return codCliente;
    }

    public void setcodCliente(int codCliente){
        this.codCliente = codCliente;
    }
    
    public int getsubtotal() {
        return subtotal;
    }

    public void setsubtotal(int subtotal) {
        this.subtotal = subtotal;
    }

    public int getimpuesto() {
        return impuesto;
    }

    public void setimpuesto(int impuesto) {
        this.impuesto = impuesto;
    }
    
    public int gettotal() {
        return total;
    }

    public void settotal(int total) {
        this.total = total;
    }
    
    
    public ArrayList Factura(){
        ArrayList informacion = new ArrayList();
        informacion.add(this.numFactura);
        informacion.add(this.codCliente);
        informacion.add(this.subtotal);
        informacion.add(this.impuesto);
        informacion.add(this.total);
        return informacion;
    }
    
    
}
