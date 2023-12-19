package clases;

import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author Dilan
 */
public class Factura {
    private int numeroFactura;
    private int codigoCliente;
    private LocalDate fechaFactura;
    private String estado; //Valido Anulado
    private int total;
    private List<Integer> codigoArticulo;

    public Factura() {
    }

    public Factura(int numeroFactura, int codigoCliente, LocalDate fechaFactura, String estado, int total, List<Integer> codigoArticulo) {
        this.numeroFactura = numeroFactura;
        this.codigoCliente = codigoCliente;
        this.fechaFactura = fechaFactura;
        this.estado = estado;
        this.total = total;
        this.codigoArticulo = (List<Integer>) codigoArticulo;
    }

    public int getNumeroFactura() {
        return numeroFactura;
    }

    public void setNumeroFactura(int numeroFactura) {
        this.numeroFactura = numeroFactura;
    }

    public int getCodigoCliente() {
        return codigoCliente;
    }

    public void setCodigoCliente(int codigoCliente) {
        this.codigoCliente = codigoCliente;
    }

    public LocalDate getFechaFactura() {
        return fechaFactura;
    }

    public void setFechaFactura(LocalDate fechaFactura) {
        this.fechaFactura = fechaFactura;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<Integer> getCodigoArticulo() {
        return codigoArticulo;
    }

    public void setCodigoArticulo(List<Integer> codigoArticulo) {
        this.codigoArticulo = codigoArticulo;
    }

    @Override
    public String toString() {
        return "Factura{" + "numeroFactura=" + numeroFactura + ", codigoCliente=" + codigoCliente + ", fechaFactura=" + fechaFactura + ", estado=" + estado + ", total=" + total + ", codigoArticulo=" + codigoArticulo + '}';
    }
    
}