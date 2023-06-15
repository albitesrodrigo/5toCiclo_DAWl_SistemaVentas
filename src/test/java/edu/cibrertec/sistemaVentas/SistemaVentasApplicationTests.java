package edu.cibrertec.sistemaVentas;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import edu.cibrertec.sistemaVentas.model.Empleado;
import edu.cibrertec.sistemaVentas.repository.IEmpleadoRepository;

@SpringBootTest
class SistemaVentasApplicationTests {

	@Autowired
	private IEmpleadoRepository repository;
	
	@InjectMocks
	private BCryptPasswordEncoder encoder;
	
	@Test
	void contextLoads() {
		Empleado empleado = new Empleado();
		empleado.setNombres("Samme");
		empleado.setApellidos("Postillos Pedroso");
		empleado.setDni("45687256");
		empleado.setTelefono("965874152");
		empleado.setTipo("1");
		empleado.setUsername("Admin");
		empleado.setPassword(encoder.encode("master"));
		empleado.setEstado("1");
		
		Empleado newEmpelado=repository.save(empleado);
		
		assertTrue(newEmpelado.getPassword().equalsIgnoreCase(empleado.getPassword()));
		
	}

}
