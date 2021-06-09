package com.egg.spring.controller;

import com.egg.spring.entity.Producto;
import com.egg.spring.service.MascotaService;
import com.egg.spring.service.ProductoService;
import com.egg.spring.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/productos")
public class ProductoController {
	
	@Autowired
    private ProductoService productoService;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private MascotaService mascotaService;

    @GetMapping
    public ModelAndView mostrarTodos() {
        ModelAndView mav = new ModelAndView("productos");
        mav.addObject("productos", productoService.buscarTodos());
        return mav;
    }

    @GetMapping("/crear")
    public ModelAndView crearProducto(@PathVariable Long dni, @PathVariable String id) {
        ModelAndView mav = new ModelAndView("productos-formulario");
        mav.addObject("producto", new Producto());
		mav.addObject("cliente", usuarioService.buscarPorDni(dni));
		mav.addObject("tipo de mascota", mascotaService.buscarPorId(id).getTipoMascota());
        mav.addObject("title", "Crear Producto");
        mav.addObject("action", "guardar");
        return mav;
    }

    @GetMapping("/editar/{codigo}")
    public ModelAndView editarProducto(@PathVariable String codigo) {
        ModelAndView mav = new ModelAndView("especialista-formulario");
        mav.addObject("usuario", productoService.buscarPorCodigo(codigo));
        mav.addObject("title", "Editar Producto");
        mav.addObject("action", "modificar");
        return mav;
    }

    @PostMapping("/guardar")
    public RedirectView guardar(@RequestParam String titulo, @RequestParam double precio, @RequestParam String marca, @RequestParam String descripcion, @RequestParam Integer cantidad, @RequestParam Integer stock, @RequestParam("usuario") Long idU, @RequestParam("mascota") String idM) {
        productoService.crear(titulo, precio, marca, descripcion, cantidad, stock, idU, idM);
        return new RedirectView("/productos");
    }

    @PostMapping("/modificar")
    public RedirectView modificar(@RequestParam String codigo, @RequestParam String titulo, @RequestParam double precio, @RequestParam String marca, @RequestParam String descripcion, @RequestParam Integer cantidad, @RequestParam Integer stock) {
        productoService.modificar(codigo, titulo, precio, marca, descripcion, cantidad, stock);
        return new RedirectView("/productos");
    }

    @PostMapping("/eliminar/{codigo}")
    public RedirectView eliminar(@PathVariable String codigo) {
        productoService.eliminar(codigo);
        return new RedirectView("/productos");
    }
}
