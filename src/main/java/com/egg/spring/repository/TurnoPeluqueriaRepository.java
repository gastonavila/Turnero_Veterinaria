package com.egg.spring.repository;

import com.egg.spring.entity.TurnoPeluqueria;
import java.util.Date;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TurnoPeluqueriaRepository extends JpaRepository<TurnoPeluqueria, String>{
	
	@Modifying
    @Query("UPDATE TurnoPeluqueria tp SET tp.fecha = :fecha, tp.hora = :hora WHERE tp.codigo = :codigo")
    void modificar(@Param("codigo") String codigo, @Param("fecha") Date fecha, @Param("hora") String hora);
}
