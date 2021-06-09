package com.egg.spring.repository;

import com.egg.spring.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, String>{
	
	@Modifying
    @Query("UPDATE Producto p SET p.titulo = :titulo, p.precio = :precio, p.marca = :marca"
			+ ", p.descripcion = :descripcion, p.cantidad = :cantidad, p.stock = :stock WHERE p.codigo = :codigo")
    void modificar(@Param("codigo") String codigo, @Param("titulo") String titulo, @Param("precio") double precio, 
			@Param("marca") String marca, @Param("descripcion") String descripcion, @Param("cantidad") Integer cantidad,
			@Param("stock") Integer stock);
}
