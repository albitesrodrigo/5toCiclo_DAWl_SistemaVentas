package edu.cibrertec.sistemaVentas.service;

import edu.cibrertec.sistemaVentas.model.DetalleVenta;
import edu.cibrertec.sistemaVentas.model.Venta;
import edu.cibrertec.sistemaVentas.repository.IDetalleVentaRepository;
import edu.cibrertec.sistemaVentas.repository.IVentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VentaServiceImpl implements IVentaService{

    @Autowired
    private IVentaRepository ventaRepository;

    @Autowired
    private IDetalleVentaRepository detalleVentaRepository;

    @Override
    public Venta registrarVenta(Venta request) {
        return ventaRepository.save(request);
    }

    @Override
    public DetalleVenta registrarDetalle(DetalleVenta request) {
        return detalleVentaRepository.save(request);
    }

    @Override
    public List<Venta> listarVentas() {
        return ventaRepository.findAll();
    }
}
