package com.gestion_inventario.principal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestion_inventario.principal.model.Producto;
import com.gestion_inventario.principal.model.entity.ProductoEntity;
import com.gestion_inventario.principal.repository.ProductoRepository;

@Service

public class ProductoService {
    @Autowired
    private ProductoRepository productorepository; 

    public String crearProducto(Producto prod){

        try {
            boolean estado = productorepository.existsByIdProducto(prod.getIdProducto());
            if (!estado) {
                ProductoEntity productoNuevo = new ProductoEntity();
                productoNuevo.setIdProducto(prod.getIdProducto()); 
                productoNuevo.setNombreProducto(prod.getNombreProducto());
                productoNuevo.setStock(prod.getStock());
                productoNuevo.setPrecio(prod.getPrecio());
                productorepository.save(productoNuevo);
                return "Producto creado correctamente";
            }
            return "El producto ya existe"; 
        } catch (Exception e) {
            return "Error al crear producto";
        } 
    }
    public Producto obtenerProducto(int idProducto){
        try {
            ProductoEntity producto = productorepository.findByIdProducto(idProducto);
            if (producto != null) {
                Producto prod = new Producto(
                    producto.getIdProducto(),
                    producto.getNombreProducto(),
                    producto.getStock(),
                    producto.getPrecio()
                ); 
                return prod; 
            }
            return null; 
        } catch (Exception e) {
            return null; 
        }
    }

}
