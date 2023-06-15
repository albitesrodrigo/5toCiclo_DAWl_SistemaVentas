package edu.cibrertec.sistemaVentas.repository;

import edu.cibrertec.sistemaVentas.model.DetalleVenta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IDetalleVentaRepository extends JpaRepository<DetalleVenta, Integer> {
}
