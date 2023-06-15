package edu.cibrertec.sistemaVentas.controller;

import edu.cibrertec.sistemaVentas.model.Rol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import edu.cibrertec.sistemaVentas.model.Empleado;
import edu.cibrertec.sistemaVentas.service.EmpeladoService;

import java.util.ArrayList;
import java.util.List;

@Controller

public class EmpleadoController {

	@Autowired
	private EmpeladoService service;

	@GetMapping({ "/", "/index" })
	public String index() {
		return "login";
	}

	@GetMapping("/login")
	public String login() {
		return "login";
	}

	@GetMapping("/menu")
	public String menu() {
		return "menu";
	}

	@GetMapping("/user")
	public String user() {
		return "user";
	}

	@GetMapping("/admin")
	public String admin() {
		return "admin";
	}

	@GetMapping("/accessDenied")
	public String accessDenied() {
		return "accessDenied";
	}


	@GetMapping("/empleado")
	public String listarEmpleado(Model modelo) {
		List<Empleado> list = service.listarEmpleados();
		modelo.addAttribute("empleados", list);
		return "Empleado/index.html";
	}

	@GetMapping("/empleado/registro")
	public String crearEmpleado(Model modelo) {
		Empleado empleado = new Empleado();
		List<Rol> option = new ArrayList<>();
		option.add(new Rol(0,"USER"));
		option.add(new Rol(1,"ADMIN"));
		modelo.addAttribute("empleado", empleado);
		modelo.addAttribute("roles", option);
		return "Empleado/new.html";
	}

	@PostMapping("/empleado")
	public String guardarEmpleado(@ModelAttribute("empleado") Empleado empleado) {
		empleado.setEstado("1");
		service.guardarEmpleado(empleado);
		return "redirect:/empleado";

	}

	@GetMapping("/empleado/edicion/{id}")
	public String mostrarFormEditar(@PathVariable Integer id, Model modelo) {
		List<Rol> option = new ArrayList<>();
		option.add(new Rol(0,"USER"));
		option.add(new Rol(1,"ADMIN"));
		Empleado empleado = service.obtenerEmpelado(id);
		modelo.addAttribute("empleado", empleado);
		modelo.addAttribute("roles", option);
		return "Empleado/edit.html";
	}

	@PostMapping("/empleado/{id}")
	public String actualizarEmpleado(@PathVariable Integer id, Empleado empleado) {
		Empleado empleadoExistente = service.obtenerEmpelado(id);
		empleadoExistente.setNombres(empleado.getNombres());
		empleadoExistente.setApellidos(empleado.getApellidos());
		empleadoExistente.setDni(empleado.getDni());
		empleadoExistente.setTelefono(empleado.getTelefono());
		empleadoExistente.setTipo(empleado.getTipo());
		empleadoExistente.setUsername(empleado.getUsername());
		empleadoExistente.setEstado("1");
		service.actualizarEmpleado(empleadoExistente);
		return "redirect:/empleado";
	}

	@GetMapping("/empleado/borrar/{id}")
	public String eliminar(@PathVariable Integer id) {
		service.eliminarEmpleado(id);
		return "redirect:/empleado";
	}

}
