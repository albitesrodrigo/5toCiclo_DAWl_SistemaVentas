package edu.cibrertec.sistemaVentas.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "ventas")
public class Venta {

    @Id
    @Column(name = "idventas")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer IdVentas;

    @Column(name = "idcliente")
    private Integer IdCliente;
    @Column(name = "idempleado")
    private Integer IdEmpleado;
    @Column(name = "numeroserie")
    private String NumeroSerie;
    @Column(name = "fechaventas")
    private Date FechaVentas;
    @Column(name = "monto")
    private Double Monto;
    @Column(name = "estado")
    private String Estado;

    public Venta() {
    }

    public Venta(Integer idCliente, Integer idEmpleado, String numeroSerie) {
        IdCliente = idCliente;
        IdEmpleado = idEmpleado;
        NumeroSerie = numeroSerie;
    }

    public Integer getIdVentas() {
        return IdVentas;
    }

    public void setIdVentas(Integer idVentas) {
        IdVentas = idVentas;
    }

    public Integer getIdCliente() {
        return IdCliente;
    }

    public void setIdCliente(Integer idCliente) {
        IdCliente = idCliente;
    }

    public Integer getIdEmpleado() {
        return IdEmpleado;
    }

    public void setIdEmpleado(Integer idEmpleado) {
        IdEmpleado = idEmpleado;
    }

    public String getNumeroSerie() {
        return NumeroSerie;
    }

    public void setNumeroSerie(String numeroSerie) {
        NumeroSerie = numeroSerie;
    }

    public Date getFechaVentas() {
        return FechaVentas;
    }

    public void setFechaVentas(Date fechaVentas) {
        FechaVentas = fechaVentas;
    }

    public Double getMonto() {
        return Monto;
    }

    public void setMonto(Double monto) {
        Monto = monto;
    }

    public String getEstado() {
        return Estado;
    }

    public void setEstado(String estado) {
        Estado = estado;
    }

    @Override
    public String toString() {
        return "Venta{" +
                "IdVentas=" + IdVentas +
                ", IdCliente=" + IdCliente +
                ", IdEmpleado=" + IdEmpleado +
                ", NumeroSerie='" + NumeroSerie + '\'' +
                ", FechaVentas=" + FechaVentas +
                ", Monto=" + Monto +
                ", Estado='" + Estado + '\'' +
                '}';
    }
}
