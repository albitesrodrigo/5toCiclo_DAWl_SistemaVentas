package edu.cibrertec.sistemaVentas.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "detalle_ventas")
public class DetalleVenta {

    @Id
    @Column(name = "iddetalleventas")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer IdDetalleVentas;

    @Column(name = "idventas")
    private Integer IdVenta;
    @Column(name = "idproducto")
    private Integer IdProducto;
    @Column(name = "cantidad")
    private Integer cantidad;
    @Column(name = "precioventa")
    private Double PrecioVenta;

    public DetalleVenta(Integer idVenta, Integer idProducto, Integer cantidad, Double precioVenta) {
        IdVenta = idVenta;
        IdProducto = idProducto;
        this.cantidad = cantidad;
        PrecioVenta = precioVenta;
    }

    public DetalleVenta() {

    }

    public Integer getIdDetalleVentas() {
        return IdDetalleVentas;
    }

    public void setIdDetalleVentas(Integer idDetalleVentas) {
        IdDetalleVentas = idDetalleVentas;
    }

    public Integer getIdVenta() {
        return IdVenta;
    }

    public void setIdVenta(Integer idVenta) {
        IdVenta = idVenta;
    }

    public Integer getIdProducto() {
        return IdProducto;
    }

    public void setIdProducto(Integer idProducto) {
        IdProducto = idProducto;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Double getPrecioVenta() {
        return PrecioVenta;
    }

    public void setPrecioVenta(Double precioVenta) {
        PrecioVenta = precioVenta;
    }
}
