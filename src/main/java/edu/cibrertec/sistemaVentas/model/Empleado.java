package edu.cibrertec.sistemaVentas.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name="empleado", uniqueConstraints = @UniqueConstraint(columnNames = "username"))
public class Empleado {

	@Id
	@Column(name = "idempleado")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer IdEmpleado;

	@Column
	private String Nombres;
	
	@Column
	private String Apellidos;
	
	@Column
	private String Dni;
	
	@Column
	private String Telefono;
	
	@Column
	private String Tipo;

	@Column
	private String username;
	
	@Column
	private String password;
	
	@Column
	private String Estado;

	public Empleado() {
		super();
	}

	public Empleado(String nombres, String apellidos, String dni, String telefono, String tipo,
			String username, String password, String estado) {
		super();
		
		this.Nombres = nombres;
		this.Apellidos = apellidos;
		this.Dni = dni;
		this.Telefono = telefono;
		this.Tipo = tipo;
		this.username = username;
		this.password = password;
		this.Estado = estado;
	}

	public Integer getIdEmpleado() {
		return IdEmpleado;
	}

	public void setIdEmpleado(Integer idEmpleado) {
		IdEmpleado = idEmpleado;
	}

	public String getNombres() {
		return Nombres;
	}

	public void setNombres(String nombres) {
		Nombres = nombres;
	}

	public String getApellidos() {
		return Apellidos;
	}

	public void setApellidos(String apellidos) {
		Apellidos = apellidos;
	}

	public String getDni() {
		return Dni;
	}

	public void setDni(String dni) {
		Dni = dni;
	}

	public String getTelefono() {
		return Telefono;
	}

	public void setTelefono(String telefono) {
		Telefono = telefono;
	}

	public String getTipo() {
		return Tipo;
	}

	public void setTipo(String tipo) {
		Tipo = tipo;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEstado() {
		return Estado;
	}

	public void setEstado(String estado) {
		Estado = estado;
	}

	@Override
	public String toString() {
		return "Empleado{" +
				"IdEmpleado=" + IdEmpleado +
				", Nombres='" + Nombres + '\'' +
				", Apellidos='" + Apellidos + '\'' +
				", Dni='" + Dni + '\'' +
				", Telefono='" + Telefono + '\'' +
				", Tipo='" + Tipo + '\'' +
				", username='" + username + '\'' +
				", password='" + password + '\'' +
				", Estado='" + Estado + '\'' +
				'}';
	}
}
