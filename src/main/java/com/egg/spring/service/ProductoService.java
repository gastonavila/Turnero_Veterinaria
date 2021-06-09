package com.egg.spring.service;

import com.egg.spring.entity.Producto;
import com.egg.spring.repository.MascotaRepository;
import com.egg.spring.repository.ProductoRepository;
import com.egg.spring.repository.UsuarioRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductoService {
	
	@Autowired
	private ProductoRepository pR;
	
	@Autowired
	private UsuarioRepository uR;
	
	@Autowired
	private MascotaRepository mR;
	
	@Transactional
	public void crear(String titulo, double precio, String marca, String descripcion, Integer cantidad, Integer stock, Long idU, String idM){
		Producto producto = new Producto();
		producto.setTitulo(titulo);
		producto.setPrecio(precio);
		producto.setMarca(marca);
		producto.setDescripcion(descripcion);
		producto.setCantidad(cantidad);
		producto.setStock(stock);
		producto.setCliente(uR.findById(idU).orElse(null));
		//producto.setTipoMascota(mR.findById(idM).orElse(null));
		
		pR.save(producto);
	}
	
	@Transactional
	public void modificar(String codigo, String titulo, double precio, String marca, String descripcion, Integer cantidad, Integer stock){
		pR.modificar(codigo, titulo, precio, marca, descripcion, cantidad, stock);
	}
	
	@Transactional(readOnly = true)
	public List<Producto> buscarTodos(){
		return pR.findAll();
	}
	
	@Transactional(readOnly = true)
	public Producto buscarPorCodigo(String codigo){
		Optional<Producto> productoOptional = pR.findById(codigo);
		return productoOptional.orElse(null);
	}
	
	@Transactional
	public void eliminar(String codigo){
		pR.deleteById(codigo);
	}
}
