package edu.cibrertec.sistemaVentas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.cibrertec.sistemaVentas.model.Empleado;
import org.springframework.stereotype.Repository;

@Repository
public interface IEmpleadoRepository extends JpaRepository<Empleado, Integer>{
	Empleado findByUsername(String username);
}
