package edu.cibrertec.sistemaVentas.service;

import edu.cibrertec.sistemaVentas.model.Cliente;
import edu.cibrertec.sistemaVentas.repository.IClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteServiceImpl implements IClienteService{
    @Autowired
    private IClienteRepository clienteRepository;
    @Override
    public List<Cliente> obtenerTodos() {
        return clienteRepository.findAll();
    }

    @Override
    public Cliente obtenerById(Integer id) {
        return clienteRepository.getById(id);
    }

    @Override
    public Cliente registrarCliente(Cliente request) {
        return clienteRepository.save(request);
    }

    @Override
    public Cliente actualizarCliente(Cliente request) {
        return clienteRepository.save(request);
    }

    @Override
    public boolean eliminarCliente(Integer id) {
        clienteRepository.deleteById(id);
        return true;
    }

    @Override
    public Cliente consultaCLienteByDni(String Dni) {
        return clienteRepository.findFirstByDniIs(Dni);
    }
}
