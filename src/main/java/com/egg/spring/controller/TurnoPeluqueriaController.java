package com.egg.spring.controller;

import com.egg.spring.entity.TurnoGuarderia;
import com.egg.spring.service.EspecialistaService;
import com.egg.spring.service.TurnoPeluqueriaService;
import com.egg.spring.service.UsuarioService;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/turnos-peluqueria")
public class TurnoPeluqueriaController {
	
	@Autowired
	private TurnoPeluqueriaService tpService;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private EspecialistaService especialistaService;
	
	@GetMapping
	public ModelAndView mostrarTodos(){
		ModelAndView mav = new ModelAndView("turnos-peluqueria");
		mav.addObject("turnos", tpService.buscarTodos());
		return mav;
	}
	
	@GetMapping("/crear")
	public ModelAndView crearTurno(){
		ModelAndView mav = new ModelAndView("turnos-peluqueria-formulario");
		mav.addObject("turno", new TurnoGuarderia());
		mav.addObject("clientes", usuarioService.buscarTodos());
		mav.addObject("especialistas", especialistaService.buscarTodos());
		mav.addObject("horarios", tpService.horarios());
		mav.addObject("title", "Generar Turno");
		mav.addObject("action", "guardar");
		return mav;
	}
	
	@GetMapping("/editar/{codigo}")
	public ModelAndView editarTurno(@PathVariable String codigo){
		ModelAndView mav = new ModelAndView("turnos-peluqueria-formulario");
		mav.addObject("turno", tpService.buscarPorCodigo(codigo));
		mav.addObject("clientes", usuarioService.buscarTodos());
		mav.addObject("especialistas", especialistaService.buscarTodos());
		mav.addObject("horarios", tpService.horarios());
		mav.addObject("title", "Editar turno");
		mav.addObject("action", "modificar");
		return mav;
	}
	
	@PostMapping("/guardar")
	public RedirectView guardar(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date fecha, @RequestParam("cliente") Long dniU, @RequestParam("especialista") Long dniE, @RequestParam String hora){
		tpService.crear(fecha, dniU, dniE, hora);
		return new RedirectView("/turnos-peluqueria");
	}
	
	@PostMapping("/modificar")
	public RedirectView modificar(@RequestParam String codigo, @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date fecha, @RequestParam String hora){
		tpService.modificar(codigo, fecha, hora);
		return new RedirectView("/turnos-peluqueria");
	}
	
	@PostMapping("/eliminar/{codigo}")
	public RedirectView eliminar(@PathVariable String codigo){
		tpService.eliminar(codigo);
		return new RedirectView("/turnos-peluqueria");
	}

}
