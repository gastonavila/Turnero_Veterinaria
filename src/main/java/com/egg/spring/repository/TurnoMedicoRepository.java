package com.egg.spring.repository;

import com.egg.spring.entity.TurnoMedico;
import java.util.Date;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TurnoMedicoRepository extends JpaRepository<TurnoMedico, String>{
	
	@Modifying
    @Query("UPDATE TurnoMedico tm SET tm.fecha = :fecha, tm.hora = :hora WHERE tm.codigo = :codigo")
    void modificar(@Param("codigo") String codigo, @Param("fecha") Date fecha, @Param("hora") String hora);
}
