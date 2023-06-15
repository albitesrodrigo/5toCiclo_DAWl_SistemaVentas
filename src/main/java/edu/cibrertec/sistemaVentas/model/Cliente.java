package edu.cibrertec.sistemaVentas.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Cliente")
public class Cliente {
    @Id
    @Column(name = "idcliente")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer IdCliente;

    @Column(name = "Dni")
    private String dni;
    @Column(name = "Nombres")
    private String nombres;
    @Column(name = "Direccion")
    private String direccion;
    @Column(name = "Estado")
    private String estado;

    public Cliente() {
        super();
    }

    public Cliente(String dni, String nombres, String direccion, String estado) {
        super();
        this.dni = dni;
        this.nombres = nombres;
        this.direccion = direccion;
        this.estado = estado;
    }

    public Integer getIdCliente() {
        return IdCliente;
    }

    public void setIdCliente(Integer id) {
        this.IdCliente = id;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "id=" + IdCliente +
                ", dni='" + dni + '\'' +
                ", nombres='" + nombres + '\'' +
                ", direccion='" + direccion + '\'' +
                ", estado='" + estado + '\'' +
                '}';
    }
}
