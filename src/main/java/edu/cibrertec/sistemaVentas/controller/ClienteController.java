package edu.cibrertec.sistemaVentas.controller;

import edu.cibrertec.sistemaVentas.model.Cliente;
import edu.cibrertec.sistemaVentas.service.ClienteServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class ClienteController {
    @Autowired
    private ClienteServiceImpl clienteService;

    @GetMapping({"/cliente"})
    public String listarClientes(Model modelo) {
        List<Cliente> lista = clienteService.obtenerTodos();
        modelo.addAttribute("clientes", lista);
        return "Cliente/index.html";
    }

    @GetMapping({"/cliente/registro"})
    public String registarCliente(Model modelo) {
        Cliente cliente = new Cliente();
        modelo.addAttribute("cliente", cliente);
        return "Cliente/new.html";
    }

    @PostMapping({"/cliente"})
    public String guardarCliente(@ModelAttribute("cliente") Cliente cliente) {
        cliente.setEstado("1");
        clienteService.registrarCliente(cliente);
        return "redirect:/cliente";
    }

    @GetMapping({"/cliente/edicion/{id}"})
    public String mostrarFormularioEdicion(@PathVariable Integer id, Model modelo) {
        Cliente cliente = clienteService.obtenerById(id);
        modelo.addAttribute("cliente", cliente);
        return "Cliente/edit.html";
    }

    @PostMapping({"/cliente/{id}"})
    public String actualizarCliente(@PathVariable Integer id, @ModelAttribute("cliente") Cliente cliente, Model modelo) {
        Cliente cliente1 = clienteService.obtenerById(id);
        cliente1.setNombres(cliente.getNombres());
        cliente1.setDireccion(cliente.getDireccion());
        cliente1.setDni(cliente.getDni());
        clienteService.actualizarCliente(cliente1);
        return "redirect:/cliente";
    }

    @GetMapping({"/cliente/borrar/{id}"})
    public String eliminar(@PathVariable Integer id) {
        clienteService.eliminarCliente(id);
        return "redirect:/cliente";
    }
}
