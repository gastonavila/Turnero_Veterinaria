package com.egg.spring.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Especialista {
	
	@Id
	private Long dni;
	
	private String nombre;
	private String apellido;
	private String especialidad;
	/*private List<String> listaEspecialidad;*/
	
	@OneToMany(mappedBy = "especialista")
	private List<TurnoMedico> consulta;
	
	@OneToMany(mappedBy = "especialista")
	private List<TurnoPeluqueria> cortes;
	
	@OneToMany(mappedBy = "especialista")
	private List<TurnoGuarderia> cuidados;

	public Especialista() {
		/*this.listaEspecialidad = new ArrayList<>();
		listaEspecialidad.add("Veterinario");
		listaEspecialidad.add("Cuidador");
		listaEspecialidad.add("Peluquero");*/
		this.consulta = new ArrayList<>();
		this.cortes = new ArrayList<>();
		this.cuidados = new ArrayList<>();
		
	}

	public Long getDni() {
		return dni;
	}

	public void setDni(Long dni) {
		this.dni = dni;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getEspecialidad() {
		return especialidad;
	}

	public void setEspecialidad(String especialidad) {
		this.especialidad = especialidad;
	}
	/*
	public List<String> getListaEspecialidad() {
		return listaEspecialidad;
	}

	public void setListaEspecialidad(List<String> listaEspecialidad) {
		this.listaEspecialidad = listaEspecialidad;
	}
	*/

	public List<TurnoMedico> getConsulta() {
		return consulta;
	}

	public void setConsulta(List<TurnoMedico> consulta) {
		this.consulta = consulta;
	}

	public List<TurnoPeluqueria> getCortes() {
		return cortes;
	}

	public void setCortes(List<TurnoPeluqueria> cortes) {
		this.cortes = cortes;
	}

	public List<TurnoGuarderia> getCuidados() {
		return cuidados;
	}

	public void setCuidados(List<TurnoGuarderia> cuidados) {
		this.cuidados = cuidados;
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 53 * hash + Objects.hashCode(this.dni);
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final Especialista other = (Especialista) obj;
		if (!Objects.equals(this.dni, other.dni)) {
			return false;
		}
		return true;
	}
	
	
}
