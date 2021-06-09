package com.egg.spring.repository;

import com.egg.spring.entity.TurnoGuarderia;
import java.util.Date;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TurnoGuarderiaRepository extends JpaRepository<TurnoGuarderia, String>{
	
	@Modifying
    @Query("UPDATE TurnoGuarderia tg SET tg.fecha = :fecha, tg.hora = :hora WHERE tg.codigo = :codigo")
    void modificar(@Param("codigo") String codigo, @Param("fecha") Date fecha, @Param("hora") String hora);
}
