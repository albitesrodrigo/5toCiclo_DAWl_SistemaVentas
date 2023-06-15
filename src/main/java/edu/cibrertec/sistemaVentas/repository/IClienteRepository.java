package edu.cibrertec.sistemaVentas.repository;

import edu.cibrertec.sistemaVentas.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IClienteRepository extends JpaRepository<Cliente, Integer> {
    Cliente findFirstByDniIs(String dni);
}
