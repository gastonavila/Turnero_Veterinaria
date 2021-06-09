package com.egg.spring.repository;

import com.egg.spring.entity.Especialista;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EspecialistaRepository extends JpaRepository<Especialista, Long>{
	@Modifying
    @Query("UPDATE Especialista e SET e.nombre = :nombre, e.apellido = :apellido, e.especialidad = :especialidad WHERE e.dni = :dni")
    void modificar(@Param("dni") Long dni, @Param("nombre") String nombre, @Param("apellido") String apellido, @Param("especialidad") String especialidad);
}
