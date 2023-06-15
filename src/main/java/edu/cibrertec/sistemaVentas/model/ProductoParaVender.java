package edu.cibrertec.sistemaVentas.model;

public class ProductoParaVender extends Producto{
    private Double cantidad;

    public ProductoParaVender(String nombres, Integer IDProducto,  double precio, Integer stock, Double cantidad) {
        super(IDProducto, nombres, precio, stock);
        this.cantidad = cantidad;
    }

    public void aumentarCantidad() {
        this.cantidad++;
    }

    public Double getCantidad() {
        return cantidad;
    }

    public Double getTotal() {
        return this.getPrecio() * this.cantidad;
    }
}
