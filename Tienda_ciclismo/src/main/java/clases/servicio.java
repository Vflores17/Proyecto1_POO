/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clases;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author Personal
 */
public class servicio {

    private int codigoServicio;
    private int codigoCliente;
    private String marcaBici;
    private String descripcion;
    private int precio;
    private LocalDate fechaRecibido;
    private LocalDate fechaEntrega;
    private String observaciones;
    private boolean estado;

    public servicio(int codigoServicio, int codigoCliente, String marcaBici, String descripcion, int precio, LocalDate fechaRecibido, LocalDate fechaEntrega, String observaciones, boolean estado) {
        this.codigoServicio = codigoServicio;
        this.codigoCliente = codigoCliente;
        this.marcaBici = marcaBici;
        this.descripcion = descripcion;
        this.precio = precio;
        this.fechaRecibido = fechaRecibido;
        this.fechaEntrega = fechaEntrega;
        this.observaciones = observaciones;
        this.estado = estado;
    }

    public int getCodigoServicio() {
        return codigoServicio;
    }

    public void setCodigoServicio(int codigoServicio) {
        this.codigoServicio = codigoServicio;
    }

    public int getCodigoCliente() {
        return codigoCliente;
    }

    public void setCodigoCliente(int codigoCliente) {
        this.codigoCliente = codigoCliente;
    }

    public String getMarcaBici() {
        return marcaBici;
    }

    public void setMarcaBici(String marcaBici) {
        this.marcaBici = marcaBici;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public LocalDate getFechaRecibido() {
        return fechaRecibido;
    }

    public void setFechaRecibido(LocalDate fechaRecibido) {
        this.fechaRecibido = fechaRecibido;
    }

    public LocalDate getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(LocalDate fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public ArrayList mostrarTodo() {
        ArrayList<String> info = new ArrayList<>();
        info.add(String.valueOf(this.codigoCliente));
        info.add(String.valueOf(this.codigoServicio));
        info.add(this.marcaBici);
        info.add(this.descripcion);
        info.add(String.valueOf(this.precio));
        info.add(this.fechaRecibido.toString());
        info.add(this.fechaEntrega.toString());
        info.add(this.observaciones);
        info.add(String.valueOf(this.estado));
        return info;
    }

}
