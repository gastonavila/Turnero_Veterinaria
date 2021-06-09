package com.egg.spring.repository;

import com.egg.spring.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    @Modifying
    @Query("UPDATE Usuario u SET u.nombre = :nombre, u.apellido = :apellido, u.fechaNacimiento = :fechaNacimiento, u.correo = :correo WHERE u.dni = :dni")
    void modificar(@Param("dni") Long dni, @Param("nombre") String nombre, @Param("apellido") String apellido, 
			@Param("fechaNacimiento") Date fechaNacimiento, @Param("correo") String correo);

}
