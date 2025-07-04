package com.gestion_inventario.principal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.gestion_inventario.principal.model.Producto;
import com.gestion_inventario.principal.model.entity.ProductoEntity;
import com.gestion_inventario.principal.repository.ProductoRepository;
import com.gestion_inventario.principal.service.ProductoService;

@SpringBootTest 
public class ProductoTest {
     
    @Mock
    private ProductoRepository productoRepository;

    @InjectMocks
    private ProductoService productoService;

    private Producto producto;
    private ProductoEntity productoEntity;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        producto = new Producto(1, "pasta de dientes", 1, 1000);
        productoEntity = new ProductoEntity();
        productoEntity.setIdProducto(1);
        productoEntity.setNombreProducto("pasta de dientes");
        productoEntity.setStock(1); 
        productoEntity.setPrecio(1000);
        
        
    }

    @Test
    public void testCrearProducto() {
        // Simular el comportamiento del repositorio
        when(productoRepository.existsByIdProducto(producto.getIdProducto())).thenReturn(false);
        when(productoRepository.save(any(ProductoEntity.class))).thenReturn(productoEntity);

        String result = productoService.crearProducto(producto);
        assertEquals("Producto creado correctamente", result); 
    }    
    
    @Test
    public void testActualizarProducto() {
        // Simular el comportamiento del repositorio
        when(productoRepository.findByIdProducto(1)).thenReturn(productoEntity);
        when(productoRepository.save(any(ProductoEntity.class))).thenReturn(productoEntity);

        Producto nuevo = new Producto(1, "pasta de dientes", 1, 1000);
        String result = productoService.actualizarProducto(nuevo);
        
        assertEquals("El producto no existe", result); 
    }

    @Test
    public void testObtenerProducto() {
        
        when(productoRepository.findByIdProducto(1)).thenReturn(productoEntity);
        Producto result = productoService.obtenerProducto(1);
        assertNotNull(result);
        assertEquals(producto.getIdProducto(), result.getIdProducto());           

    }

    @Test
    public void testObtenerProductoNoExistente() {
        when(productoRepository.findByIdProducto(2)).thenReturn(null);
        Producto result = productoService.obtenerProducto(2);
        assertNull(result);
    }

    @Test
    public void testEliminarProducto(){
        when(productoRepository.existsByIdProducto(1)).thenReturn(true);
        doNothing().when(productoRepository).deleteByIdProducto(1);
        String result = productoService.eliminarProducto(1);

        assertEquals("Producto eliminado correctamente", result); 

    } 

    @Test
    public void testCrearProductoExistente() {
        when(productoRepository.existsByIdProducto(producto.getIdProducto())).thenReturn(true);

        String result = productoService.crearProducto(producto);
        assertEquals("El producto ya existe", result);

    }
}
