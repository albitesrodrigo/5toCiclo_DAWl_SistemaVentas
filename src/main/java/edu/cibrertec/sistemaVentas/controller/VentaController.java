package edu.cibrertec.sistemaVentas.controller;

import edu.cibrertec.sistemaVentas.model.Cliente;
import edu.cibrertec.sistemaVentas.model.DetalleVenta;
import edu.cibrertec.sistemaVentas.model.Empleado;
import edu.cibrertec.sistemaVentas.model.Producto;
import edu.cibrertec.sistemaVentas.model.ProductoParaVender;
import edu.cibrertec.sistemaVentas.model.Venta;
import edu.cibrertec.sistemaVentas.service.ClienteServiceImpl;
import edu.cibrertec.sistemaVentas.service.ProductoServiceImpl;
import edu.cibrertec.sistemaVentas.service.VentaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;

@Controller
public class VentaController {

    @Autowired
    private VentaServiceImpl ventaService;

    @Autowired
    private ProductoServiceImpl productoService;

    @Autowired
    private ClienteServiceImpl clienteService;
    @GetMapping(value = "/venta/nueva")
    public String interfazVender(Model model, HttpServletRequest request) {
        model.addAttribute("producto", new Producto());
        model.addAttribute("cliente", this.obtenerClienteSesion(request));
        model.addAttribute("clienteNombre", this.obtenerClienteSesion(request).getNombres());
        float total = 0;
        ArrayList<ProductoParaVender> carrito = this.obtenerCarrito(request);
        for (ProductoParaVender p: carrito) total += p.getTotal();
        model.addAttribute("total", total);
        return "Venta/index.html";
    }

    private Cliente obtenerClienteSesion(HttpServletRequest request) {
        Cliente cliente = (Cliente) request.getSession().getAttribute("clienteVentaNueva");
        if (cliente == null) {
            cliente = new Cliente();
        }
        return cliente;
    }

    private Empleado obtenerEmpleadoSesion(HttpServletRequest request) {
        Empleado empleado = (Empleado) request.getSession().getAttribute("usuarioSesion");
        if (empleado == null) {
            empleado = new Empleado();
        }
        return empleado;
    }

    private ArrayList<ProductoParaVender> obtenerCarrito(HttpServletRequest request) {
        ArrayList<ProductoParaVender> carrito = (ArrayList<ProductoParaVender>) request.getSession().getAttribute("carrito");
        if (carrito == null) {
            carrito = new ArrayList<>();
        }
        return carrito;
    }

    private void guardarCarrito(ArrayList<ProductoParaVender> carrito, HttpServletRequest request, Cliente cliente) {
        request.getSession().setAttribute("carrito", carrito);
        request.getSession().setAttribute("clienteVentaNueva", cliente);

    }

    @PostMapping(value = "/vender/cliente/agregar")
    public String agregarClienteAlCarrito(@ModelAttribute Cliente cliente, HttpServletRequest request, RedirectAttributes redirectAttributes) {
        Cliente cliente1 = clienteService.consultaCLienteByDni(cliente.getDni());
        if (cliente1 == null) {
            redirectAttributes
                    .addFlashAttribute("mensaje", "El Cliente con el DNI " + cliente.getDni() + " no existe")
                    .addFlashAttribute("clase", "warning");
        } else {
            this.establecerCliente(cliente1, request);
        }
        return "redirect:/venta/nueva/";
    }
    //establecerCliente
    @PostMapping(value = "/vender/agregar")
    public String agregarAlCarrito(@ModelAttribute Producto producto, HttpServletRequest request, RedirectAttributes redirectAttrs) {
        ArrayList<ProductoParaVender> carrito = this.obtenerCarrito(request);
        Cliente clienteSession = this.obtenerClienteSesion(request);
        Producto productoBuscadoPorCodigo = productoService.obtenerById(producto.getIDProducto());
        if (productoBuscadoPorCodigo == null) {
            redirectAttrs
                    .addFlashAttribute("mensaje", "El producto con el código " + producto.getIDProducto() + " no existe")
                    .addFlashAttribute("clase", "warning");
            return "redirect:/venta/nueva/";
        }
        if (productoBuscadoPorCodigo.sinExistencia()) {
            redirectAttrs
                    .addFlashAttribute("mensaje", "El producto está agotado")
                    .addFlashAttribute("clase", "warning");
            return "redirect:/venta/nueva/";
        }
        boolean encontrado = false;
        for (ProductoParaVender productoParaVenderActual : carrito) {
            if (productoParaVenderActual.getIDProducto() == (productoBuscadoPorCodigo.getIDProducto())) {
                productoParaVenderActual.aumentarCantidad();
                encontrado = true;
                break;
            }
        }
        if (!encontrado) {
            carrito.add(new ProductoParaVender(
                    productoBuscadoPorCodigo.getNombres(),
                    productoBuscadoPorCodigo.getIDProducto(),
                    productoBuscadoPorCodigo.getPrecio(),
                    productoBuscadoPorCodigo.getStock(), 1d));
        }
        this.guardarCarrito(carrito, request, clienteSession);
        return "redirect:/venta/nueva/";
    }

    @PostMapping(value = "/vender/quitar/{indice}")
    public String quitarDelCarrito(@PathVariable int indice, HttpServletRequest request) {
        ArrayList<ProductoParaVender> carrito = this.obtenerCarrito(request);
        if (carrito != null && carrito.size() > 0 && carrito.get(indice) != null) {
            carrito.remove(indice);
            this.guardarCarrito(carrito, request, this.obtenerClienteSesion(request));
        }
        return "redirect:/venta/nueva/";
    }

    private void limpiarCarrito(HttpServletRequest request) {
        this.guardarCarrito(new ArrayList<>(), request, null);
    }

    @GetMapping(value = "/vender/limpiar")
    public String cancelarVenta(HttpServletRequest request, RedirectAttributes redirectAttrs) {
        this.limpiarCarrito(request);
        redirectAttrs
                .addFlashAttribute("mensaje", "Venta cancelada")
                .addFlashAttribute("clase", "info");
        return "redirect:/venta/nueva/";
    }

    @PostMapping(value = "/vender/terminar")
    public String terminarVenta(HttpServletRequest request, RedirectAttributes redirectAttrs) {
        ArrayList<ProductoParaVender> carrito = this.obtenerCarrito(request);
        // Si no hay carrito o está vacío, regresamos inmediatamente
        if (carrito == null || carrito.size() <= 0) {
            return "redirect:/venta/nueva/";
        }

        Venta v = ventaService.registrarVenta(
                new Venta(
                        this.obtenerClienteSesion(request).getIdCliente(),
                        this.obtenerEmpleadoSesion(request).getIdEmpleado(),
                        "S-0001"
                        ));
        v.setFechaVentas(new Date());
        v.setEstado("1");
        Double total = 0d;
        for (ProductoParaVender p: carrito) total += p.getTotal();
        v.setMonto(total);
        // Recorrer el carrito
        for (ProductoParaVender productoParaVender : carrito) {
            // Obtener el producto fresco desde la base de datos
            Producto p = productoService.obtenerById(productoParaVender.getIDProducto());
            if (p == null) continue; // Si es nulo o no existe, ignoramos el siguiente código con continue
            // Le restamos existencia
            p.restarExistencia(productoParaVender.getCantidad().intValue());
            // Lo guardamos con la existencia ya restada
            productoService.actualizarProducto(p);
            // Creamos un nuevo producto que será el que se guarda junto con la venta
            DetalleVenta detalle = new DetalleVenta(v.getIdVentas(), productoParaVender.getIDProducto(), productoParaVender.getCantidad().intValue(), productoParaVender.getPrecio());
            // Y lo guardamos
            ventaService.registrarDetalle(detalle);
        }

        // Al final limpiamos el carrito
        this.limpiarCarrito(request);
        // e indicamos una venta exitosa
        redirectAttrs
                .addFlashAttribute("mensaje", "Venta realizada correctamente")
                .addFlashAttribute("clase", "success");
        return "redirect:/venta/nueva/";
    }

    private void establecerCliente(Cliente cliente, HttpServletRequest request) {
        request.getSession().setAttribute("clienteVentaNueva", cliente);
    }

    @GetMapping(value = "/ventas/todo")
    public String mostrarVentas(Model model) {
        model.addAttribute("ventas", ventaService.listarVentas());
        return "Venta/ver_ventas.html";
    }
}
