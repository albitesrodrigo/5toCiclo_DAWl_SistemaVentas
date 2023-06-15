package edu.cibrertec.sistemaVentas.service;

import edu.cibrertec.sistemaVentas.model.Producto;
import edu.cibrertec.sistemaVentas.repository.IProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoServiceImpl implements IProductoService{
    @Autowired
    private IProductoRepository productoRepository;

    @Override
    public List<Producto> obtenerTodos() {
        return productoRepository.findAll();
    }

    @Override
    public Producto obtenerById(Integer id) {
        return productoRepository.getById(id);
    }

    @Override
    public Producto registrarProducto(Producto request) {
        return productoRepository.save(request);
    }

    @Override
    public Producto actualizarProducto(Producto request) {
        return productoRepository.save(request);
    }

    @Override
    public boolean eliminarProducto(Integer id) {
        productoRepository.deleteById(id);
        return true;
    }
}
