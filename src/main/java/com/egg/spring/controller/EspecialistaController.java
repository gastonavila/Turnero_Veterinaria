package com.egg.spring.controller;

import com.egg.spring.entity.Especialista;
import com.egg.spring.service.EspecialistaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
@RequestMapping("/especialistas")
public class EspecialistaController {
	
	@Autowired
    private EspecialistaService especialistaService;

    @GetMapping
    public ModelAndView mostrarTodos() {
        ModelAndView mav = new ModelAndView("especialistas");
        mav.addObject("especialistas", especialistaService.buscarTodos());
        return mav;
    }

    @GetMapping("/crear")
    public ModelAndView crearEspecialista() {
        ModelAndView mav = new ModelAndView("especialistas-formulario");
        mav.addObject("especialista", new Especialista());
		mav.addObject("tipo", especialistaService.tipos());
        mav.addObject("title", "Crear Especialista");
        mav.addObject("action", "guardar");
        return mav;
    }

    @GetMapping("/editar/{dni}")
    public ModelAndView editarEspecialista(@PathVariable Long dni) {
        ModelAndView mav = new ModelAndView("especialistas-formulario");
        mav.addObject("especialista", especialistaService.buscarPorDni(dni));
		mav.addObject("tipo", especialistaService.tipos());
        mav.addObject("title", "Editar Especialista");
        mav.addObject("action", "modificar");
        return mav;
    }

    @PostMapping("/guardar")
    public RedirectView guardar(@RequestParam Long dni, @RequestParam String nombre, @RequestParam String apellido, @RequestParam String especialidad) {
        especialistaService.crear(dni, nombre, apellido, especialidad);
        return new RedirectView("/especialistas");
    }

    @PostMapping("/modificar")
    public RedirectView modificar(@RequestParam Long dni, @RequestParam String nombre, @RequestParam String apellido, @RequestParam String especialidad) {
        especialistaService.modificar(dni, nombre, apellido, especialidad);
        return new RedirectView("/especialistas");
    }

    @PostMapping("/eliminar/{dni}")
    public RedirectView eliminar(@PathVariable Long dni) {
        especialistaService.eliminar(dni);
        return new RedirectView("/especialistas");
    }

}
