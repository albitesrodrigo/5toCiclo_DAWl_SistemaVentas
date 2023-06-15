package edu.cibrertec.sistemaVentas.service;

import java.util.List;


import edu.cibrertec.sistemaVentas.model.Empleado;

public interface EmpeladoService {
	
	List<Empleado>listarEmpleados();
	public Empleado guardarEmpleado(Empleado empleado);
	public Empleado obtenerEmpelado(Integer IdEmpelado);
	public Empleado actualizarEmpleado(Empleado empleado);
	public void  eliminarEmpleado(Integer IdEmpelado);
}
