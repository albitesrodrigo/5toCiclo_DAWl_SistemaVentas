package edu.cibrertec.sistemaVentas.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import edu.cibrertec.sistemaVentas.model.Empleado;
import edu.cibrertec.sistemaVentas.repository.IEmpleadoRepository;

@Service
public class EmpleadoServiceImpl  implements EmpeladoService{

	@Autowired
	private IEmpleadoRepository respository;

	@Override
	public List<Empleado> listarEmpleados() {
		return respository.findAll();
	}

	@Override
	public Empleado guardarEmpleado(Empleado empleado) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		empleado.setPassword(passwordEncoder.encode(empleado.getPassword()));
		return respository.save(empleado);
	}

	@Override
	public Empleado actualizarEmpleado(Empleado empleado) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		empleado.setPassword(passwordEncoder.encode(empleado.getPassword()));
		return respository.save(empleado);
	}

	@Override
	public void eliminarEmpleado(Integer IdEmpelado) {
		respository.deleteById(IdEmpelado);
	}

	@Override
	public Empleado obtenerEmpelado(Integer IdEmpelado) {
		Empleado empleado = respository.findById(IdEmpelado).get();
		empleado.setPassword("");
		return empleado;
	}

}
