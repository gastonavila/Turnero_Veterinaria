package com.egg.spring.service;

import com.egg.spring.entity.TurnoPeluqueria;
import com.egg.spring.repository.EspecialistaRepository;
import com.egg.spring.repository.TurnoPeluqueriaRepository;
import com.egg.spring.repository.UsuarioRepository;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TurnoPeluqueriaService {
	
	@Autowired
	private TurnoPeluqueriaRepository tpR;
	
	@Autowired
	private EspecialistaRepository eR;
	
	@Autowired
	private UsuarioRepository uR;
	
	@Transactional
	public void crear(Date fecha, Long idU, Long idE, String hora){
		TurnoPeluqueria turno = new TurnoPeluqueria();
		turno.setFecha(fecha);
		turno.setCliente(uR.findById(idU).orElse(null));
		turno.setEspecialista(eR.findById(idE).orElse(null));
		turno.setHora(hora);
		
		tpR.save(turno);
	}
	
	@Transactional
	public void modificar(String codigo, Date fecha, String hora){
		tpR.modificar(codigo, fecha, hora);
	}
	
	@Transactional(readOnly = true)
	public List<TurnoPeluqueria> buscarTodos(){
		return tpR.findAll();
	}
	
	@Transactional(readOnly = true)
	public List<String> horarios(){
		List<String> horarios = new ArrayList();
		horarios.add("08:00");
		horarios.add("09:00");
		horarios.add("10:00");
		horarios.add("11:00");
		horarios.add("12:00");
		horarios.add("13:00");
		horarios.add("14:00");
		horarios.add("15:00");
		horarios.add("16:00");
		horarios.add("17:00");
		horarios.add("18:00");
		horarios.add("19:00");
		return horarios;
	}
	
	@Transactional(readOnly = true)
	public TurnoPeluqueria buscarPorCodigo(String codigo){
		Optional<TurnoPeluqueria> turnoPeluqueriaOptional = tpR.findById(codigo);
		return turnoPeluqueriaOptional.orElse(null);
	}
	
	@Transactional
	public void eliminar(String codigo){
		tpR.deleteById(codigo);
	}
}
