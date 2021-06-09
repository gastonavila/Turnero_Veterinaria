package com.egg.spring.service;

import com.egg.spring.entity.Especialista;
import com.egg.spring.repository.EspecialistaRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EspecialistaService {
	
	@Autowired
	private EspecialistaRepository eR;
	
	@Transactional
	public void crear(Long dni, String nombre, String apellido, String especialidad){
		Especialista especialista = new Especialista();
		especialista.setDni(dni);
		especialista.setNombre(nombre);
		especialista.setApellido(apellido);
		especialista.setEspecialidad(especialidad);
		
		eR.save(especialista);
	}
	
	@Transactional
	public void modificar(Long dni, String nombre, String apellido, String especialidad){
		eR.modificar(dni, nombre, apellido, especialidad);
	}
	
	@Transactional(readOnly = true)
	public List<Especialista> buscarTodos(){
		return eR.findAll();
	}
	@Transactional(readOnly = true)
	public List<String> tipos(){
		List<String> tiposdeespecialista = new ArrayList<>();
		tiposdeespecialista.add("Cuidador");
		tiposdeespecialista.add("Veterinario");
		tiposdeespecialista.add("Peluquero");
		return tiposdeespecialista;
	}
	@Transactional(readOnly = true)
	public Especialista buscarPorDni(Long dni){
		Optional<Especialista> especialistaOptional = eR.findById(dni);
		return especialistaOptional.orElse(null);
	}
	
	@Transactional
	public void eliminar(Long dni){
		eR.deleteById(dni);
	}
}
