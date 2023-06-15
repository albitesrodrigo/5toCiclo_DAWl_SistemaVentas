package edu.cibrertec.sistemaVentas.service;

import edu.cibrertec.sistemaVentas.model.DetalleVenta;
import edu.cibrertec.sistemaVentas.model.Venta;

import java.util.List;

public interface IVentaService {

    Venta registrarVenta(Venta request);

    DetalleVenta registrarDetalle(DetalleVenta request);

    List<Venta> listarVentas();
}
