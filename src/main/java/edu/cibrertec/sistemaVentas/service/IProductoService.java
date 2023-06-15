package edu.cibrertec.sistemaVentas.service;

import edu.cibrertec.sistemaVentas.model.Producto;

import java.util.List;

public interface IProductoService {
    List<Producto> obtenerTodos();
    Producto obtenerById(Integer id);
    Producto registrarProducto(Producto request);
    Producto actualizarProducto(Producto request);
    boolean eliminarProducto(Integer id);
}
