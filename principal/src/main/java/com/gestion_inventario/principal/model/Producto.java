package com.gestion_inventario.principal.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor 

public class Producto {
    private int idProducto;
    private String nombreProducto;
    private int stock;
    private int precio;
    
    

}
