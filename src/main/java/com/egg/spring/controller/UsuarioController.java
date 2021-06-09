package com.egg.spring.controller;

import com.egg.spring.entity.Usuario;
import com.egg.spring.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Date;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public ModelAndView mostrarTodos() {
        ModelAndView mav = new ModelAndView("usuarios");
        mav.addObject("usuarios", usuarioService.buscarTodos());
        return mav;
    }

    @GetMapping("/crear")
    public ModelAndView crearUsuario() {
        ModelAndView mav = new ModelAndView("usuarios-formulario");
        mav.addObject("usuario", new Usuario());
        mav.addObject("title", "Crear Usuario");
        mav.addObject("action", "guardar");
        return mav;
    }

    @GetMapping("/editar/{dni}")
    public ModelAndView editarUsuario(@PathVariable Long dni) {
        ModelAndView mav = new ModelAndView("usuarios-formulario");
        mav.addObject("usuario", usuarioService.buscarPorDni(dni));
        mav.addObject("title", "Editar Usuario");
        mav.addObject("action", "modificar");
        return mav;
    }

    @PostMapping("/guardar")
    public RedirectView guardar(@RequestParam Long dni, @RequestParam String nombre, @RequestParam String apellido, @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaNacimiento, String correo) {
        usuarioService.crear(dni, nombre, apellido, fechaNacimiento, correo);
        return new RedirectView("/usuarios");
    }

    @PostMapping("/modificar")
    public RedirectView modificar(@RequestParam Long dni, @RequestParam String nombre, @RequestParam String apellido, @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaNacimiento, String correo) {
        usuarioService.modificar(dni, nombre, apellido, fechaNacimiento, correo);
        return new RedirectView("/usuarios");
    }

    @PostMapping("/eliminar/{dni}")
    public RedirectView eliminar(@PathVariable Long dni) {
        usuarioService.eliminar(dni);
        return new RedirectView("/usuarios");
    }

}
