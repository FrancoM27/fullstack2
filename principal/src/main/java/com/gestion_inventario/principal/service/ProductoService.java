package com.gestion_inventario.principal.service;

import java.util.ArrayList;
import java.util.List;

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
            System.out.println("Creando producto: " + prod);
            boolean estado = productorepository.existsByIdProducto(prod.getIdProducto());
            System.out.println("Estado: " + estado);
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
    public String eliminarProducto(int idProducto){
        try {
            boolean estado = productorepository.existsByIdProducto(idProducto);
            if (estado) {
                productorepository.deleteById(idProducto);
                return "Producto eliminado correctamente";
            }
            return "El producto no existe"; 
        } catch (Exception e) {
            return "Error al eliminar producto";
        }
    }
     
    public String actualizarProducto(Producto prod){
        try {
            boolean estado = productorepository.existsByIdProducto(prod.getIdProducto());
            if (estado) {
                ProductoEntity productoActualizar = productorepository.findByIdProducto(prod.getIdProducto());
                productoActualizar.setNombreProducto(prod.getNombreProducto());
                productoActualizar.setStock(prod.getStock());
                productoActualizar.setPrecio(prod.getPrecio());
                productorepository.save(productoActualizar);
                return "Producto actualizado correctamente";
            }
            return "El producto no existe"; 
        } catch (Exception e) {
            return "Error al actualizar producto";
        }
    }

    public List<Producto> listarProducto(){
        try {
            List<ProductoEntity> productosEntity = productorepository.findAll();
            List<Producto> productos = new ArrayList<>();
            for (ProductoEntity producto : productosEntity) {
                Producto prod = new Producto(
                    producto.getIdProducto(),
                    producto.getNombreProducto(),
                    producto.getStock(),
                    producto.getPrecio()
                );
                productos.add(prod);
            }
            return productos;
        } catch (Exception e) {
            return null; 
        }
    }
}
