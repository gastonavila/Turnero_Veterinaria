package com.egg.spring.entity;

import java.util.ArrayList;
import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
public class Usuario {

    @Id
    private Long dni;

	@OneToMany(mappedBy = "cliente")
	private List<Producto> productos;
	
	@OneToMany(mappedBy = "cliente")
	private List<TurnoMedico> consulta;
	
	@OneToMany(mappedBy = "cliente")
	private List<TurnoPeluqueria> cortes;
	
	@OneToMany(mappedBy = "cliente")
	private List<TurnoGuarderia> cuidados;
	
	private String correo;
    private String nombre;
    private String apellido;

    @Temporal(TemporalType.DATE)
    private Date fechaNacimiento;

    @OneToMany(mappedBy = "dueno")
    private List<Mascota> mascotas;

    public Usuario() { // Constructor vacío
		this.productos = new ArrayList<>();
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

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
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
	
	public List<Producto> getProductos() {
		return productos;
	}

	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}

	
    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public List<Mascota> getMascotas() {
        return mascotas;
    }

    public void setMascotas(List<Mascota> mascotas) {
        this.mascotas = mascotas;
    }

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return dni.equals(usuario.dni);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dni);
    }
}
