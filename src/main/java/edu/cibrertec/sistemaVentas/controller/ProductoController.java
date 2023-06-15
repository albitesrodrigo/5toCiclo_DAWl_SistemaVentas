package edu.cibrertec.sistemaVentas.controller;

import edu.cibrertec.sistemaVentas.model.Producto;
import edu.cibrertec.sistemaVentas.service.ProductoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class ProductoController {

    @Autowired
    private ProductoServiceImpl productoService;

    @GetMapping({"/producto"})
    public String listarProductos(Model modelo) {
        List<Producto> lista = productoService.obtenerTodos();
        modelo.addAttribute("productos", lista);
        return "Producto/index.html";
    }

    @GetMapping({"/producto/registro"})
    public String registrarProducto(Model modelo) {
        Producto producto = new Producto();
        modelo.addAttribute("producto", producto);
        return "Producto/new.html";
    }

    @PostMapping({"/producto"})
    public String guardarProducto(@ModelAttribute("producto") Producto producto) {
        producto.setEstado("1");
        productoService.registrarProducto(producto);
        return "redirect:/producto";
    }

    @GetMapping({"/producto/edicion/{id}"})
    public String mostrarFormularioEdicion(@PathVariable Integer id, Model modelo) {
        modelo.addAttribute("producto", productoService.obtenerById(id));
        return "Producto/edit.html";
    }

    @PostMapping({"/producto/{id}"})
    public String actualizarProducto(@PathVariable Integer id, @ModelAttribute("producto") Producto producto, Model modelo) {
        Producto producto1 = productoService.obtenerById(id);
        producto1.setNombres(producto.getNombres());
        producto1.setPrecio(producto.getPrecio());
        producto1.setStock(producto.getStock());
        productoService.actualizarProducto(producto1);
        return "redirect:/producto";
    }

    @GetMapping({"/producto/borrar/{id}"})
    public String eliminarProducto(@PathVariable Integer id) {
        productoService.eliminarProducto(id);
        return "redirect:/producto";
    }
}
