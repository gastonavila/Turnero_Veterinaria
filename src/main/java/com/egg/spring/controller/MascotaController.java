package com.egg.spring.controller;

import com.egg.spring.entity.Mascota;
import com.egg.spring.service.MascotaService;
import com.egg.spring.service.UsuarioService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/mascotas")
public class MascotaController {

    @Autowired
    private MascotaService mascotaService;

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public ModelAndView mostrarTodas() {
        ModelAndView mav = new ModelAndView("mascotas");
        mav.addObject("mascotas", mascotaService.buscarTodas());
        return mav;
    }

    @GetMapping("/crear")
    public ModelAndView crearMascota() {
        ModelAndView mav = new ModelAndView("mascotas-formulario");
        mav.addObject("mascota", new Mascota());
        mav.addObject("usuarios", usuarioService.buscarTodos());
		mav.addObject("tipoMascota", mascotaService.tipos());
        mav.addObject("title", "Crear Mascota");
        mav.addObject("action", "guardar");
        return mav;
    }

    @GetMapping("/editar/{id}")
    public ModelAndView editarMascota(@PathVariable String id) {
        ModelAndView mav = new ModelAndView("mascotas-formulario");
        mav.addObject("mascota", mascotaService.buscarPorId(id));
        mav.addObject("usuarios", usuarioService.buscarTodos());
		mav.addObject("tipoMascota", mascotaService.tipos());
        mav.addObject("title", "Editar Mascota");
        mav.addObject("action", "modificar");
        return mav;
    }

    @PostMapping("/guardar")
    public RedirectView guardar(@RequestParam String nombre, @RequestParam("dueno") Long dniDueno, @RequestParam String tipoMascota) {
        mascotaService.crear(nombre, dniDueno, tipoMascota);
        return new RedirectView("/mascotas");
    }

    @PostMapping("/modificar")
    public RedirectView modificar(@RequestParam String id, @RequestParam String nombre, @RequestParam String tipoMascota) {
        mascotaService.modificar(id, nombre, tipoMascota);
        return new RedirectView("/mascotas");
    }

    @PostMapping("/eliminar/{id}")
    public RedirectView eliminar(@PathVariable String id) {
        mascotaService.eliminar(id);
        return new RedirectView("/mascotas");
    }

}
