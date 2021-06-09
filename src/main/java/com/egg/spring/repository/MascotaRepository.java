package com.egg.spring.repository;

import com.egg.spring.entity.Mascota;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MascotaRepository extends JpaRepository<Mascota, String> {

    @Modifying
    @Query("UPDATE Mascota m SET m.nombre = :nombre, m.tipoMascota = :tipoMascota WHERE m.id = :id")
    void modificar(@Param("id") String id, @Param("nombre") String nombre, @Param("tipoMascota") String tipoMascota);

}
