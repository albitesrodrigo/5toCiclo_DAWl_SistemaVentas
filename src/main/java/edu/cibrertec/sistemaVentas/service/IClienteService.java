package edu.cibrertec.sistemaVentas.service;

import edu.cibrertec.sistemaVentas.model.Cliente;

import java.util.List;

public interface IClienteService {
    List<Cliente> obtenerTodos();
    Cliente obtenerById(Integer id);
    Cliente registrarCliente(Cliente request);
    Cliente actualizarCliente(Cliente request);
    boolean eliminarCliente(Integer id);
    Cliente consultaCLienteByDni(String Dni);
}
