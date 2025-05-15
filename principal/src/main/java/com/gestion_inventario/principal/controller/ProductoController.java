package com.gestion_inventario.principal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.gestion_inventario.principal.model.Producto;
import com.gestion_inventario.principal.service.ProductoService;


@RestController
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @PostMapping("/crearProducto")
        // ResponseEntity <-- responder segun accion o resultado
        // 404 --> no se encuentra el recurso
        // 200 --> ok
    public  ResponseEntity<String> crearProducto(@RequestBody Producto producto){
        return ResponseEntity.ok(productoService.crearProducto(producto));
    }
    
    @GetMapping("/obtenerProducto/{idProducto}")
    public ResponseEntity<Producto> obtenerProducto(@PathVariable int idProducto){
        Producto producto = productoService.obtenerProducto(idProducto);
        if (producto != null) {
            return ResponseEntity.ok(producto);
        }
        return ResponseEntity.notFound().build(); 
    }



}
