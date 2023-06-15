package edu.cibrertec.sistemaVentas.repository;

import edu.cibrertec.sistemaVentas.model.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IVentaRepository extends JpaRepository<Venta, Integer> {
}
