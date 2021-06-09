package com.egg.spring.service;

import com.egg.spring.entity.Usuario;
import com.egg.spring.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Transactional
    public void crear(Long dni, String nombre, String apellido, Date fechaNacimiento, String correo) {
        Usuario usuario = new Usuario();
        usuario.setDni(dni);
        usuario.setNombre(nombre);
        usuario.setApellido(apellido);
        usuario.setFechaNacimiento(fechaNacimiento);
        usuario.setCorreo(correo);

        usuarioRepository.save(usuario);
    }

    @Transactional
    public void modificar(Long dni, String nombre, String apellido, Date fechaNacimiento, String correo) {
        usuarioRepository.modificar(dni, nombre, apellido, fechaNacimiento, correo);
    }

    @Transactional(readOnly = true)
    public List<Usuario> buscarTodos() {
        return usuarioRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Usuario buscarPorDni(Long dni) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(dni);
        return usuarioOptional.orElse(null);
    }

    @Transactional
    public void eliminar(Long dni) {
        usuarioRepository.deleteById(dni);
    }

}
