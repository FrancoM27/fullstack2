package com.gestion_inventario.principal.model.entity;

import org.springframework.boot.autoconfigure.domain.EntityScan;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@EntityScan
@Data

public class ProductoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idProducto; 
    
    @Column(name = "nombreProducto")
    private String nombreProducto;

    private int stock;
    private int precio; 

}
