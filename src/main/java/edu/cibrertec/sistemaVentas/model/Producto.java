package edu.cibrertec.sistemaVentas.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "producto")
public class Producto {

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int IDProducto;
	@Column(name = "Nombres")
	private String Nombres;
	@Column(name = "Precio")
	private double Precio;
	@Column(name = "Stock")
	private Integer Stock;
	@Column(name = "Estado")
	private String Estado;
	
	public Producto() {
		super();
	}

	public Producto( String nombres, double precio, Integer stock, String estado) {
		super();
		
		this.Nombres = nombres;
		this.Precio = precio;
		this.Stock = stock;
		this.Estado = estado;
	}

	public Producto(int IDProducto, String nombres, double precio, Integer stock) {
		this.IDProducto = IDProducto;
		Nombres = nombres;
		Precio = precio;
		Stock = stock;
	}

	public int getIDProducto() {
		return IDProducto;
	}

	public void setIDProducto(int iDProducto) {
		IDProducto = iDProducto;
	}

	public String getNombres() {
		return Nombres;
	}

	public void setNombres(String nombres) {
		Nombres = nombres;
	}

	public double getPrecio() {
		return Precio;
	}

	public void setPrecio(double precio) {
		Precio = precio;
	}

	public Integer getStock() {
		return Stock;
	}

	public void setStock(Integer stock) {
		Stock = stock;
	}

	public String getEstado() {
		return Estado;
	}

	public void setEstado(String estado) {
		Estado = estado;
	}

	@Override
	public String toString() {
		return "Producto [IDProducto=" + IDProducto + ", Nombres=" + Nombres + ", Precio=" + Precio + ", Stock=" + Stock
				+ ", Estado=" + Estado + "]";
	}

	public boolean sinExistencia() {
		return this.Stock <= 0;
	}

	public void restarExistencia(Integer existencia) {
		this.Stock -= existencia;
	}

}
