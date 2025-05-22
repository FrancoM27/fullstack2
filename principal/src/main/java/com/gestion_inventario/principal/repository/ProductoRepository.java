package com.gestion_inventario.principal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gestion_inventario.principal.model.entity.ProductoEntity;



@Repository
public interface ProductoRepository extends JpaRepository<ProductoEntity, Integer> {

    ProductoEntity findByIdProducto(int idProducto);
    ProductoEntity findByNombreProducto(String nombreProducto);
    boolean existsByIdProducto(int idProducto); 
    boolean existsByNombreProducto(String nombreProducto); 
}