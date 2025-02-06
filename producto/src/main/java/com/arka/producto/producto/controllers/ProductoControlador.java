package com.arka.producto.producto.controllers;

import com.arka.producto.producto.DTOs.ProductoCreateDTO;
import com.arka.producto.producto.DTOs.ProductoDto;
import com.arka.producto.producto.entities.Producto;
import com.arka.producto.producto.services.ProductoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/productos")
public class ProductoControlador {
    @Autowired
    private ProductoServicio productoServicio;

    //Obtoner todos los productos
    @GetMapping
    public List<ProductoDto> obtenerTodosLosProductos(){
        return productoServicio.obtenerTodosLosProductos();
    }

    //obtener un producto por su ID
    @GetMapping("/{id}")
    public ProductoDto obtenerProductoPorId(@PathVariable Long id){
        return productoServicio.obtenerProductoPorId(id);
    }

    //Crear un nuevo producto
    @PostMapping
    public ProductoDto crearProducto(@RequestBody ProductoCreateDTO productoCreateDTO){
        return productoServicio.crearProducto(productoCreateDTO);
    }

    //Actualizar un producto por su ID
    @PutMapping("/{id}")
    public ProductoDto actualizarProducto(@PathVariable Long id,@RequestBody ProductoCreateDTO productoCreateDTO){
        return productoServicio.actualizarProducto(id, productoCreateDTO);
    }

    //Eliminar un producto por su ID
    @DeleteMapping("/{id}")
    public String eliminarProducto(@PathVariable Long id){
        boolean eliminado= productoServicio.eliminarProducto(id);
        if (eliminado){
            return "Se ha eliminado el producto con Id: " +id;
        }else {
            return "No se encontro el producto con ID: " +id;
        }
    }

    //Buscar productos por nombre o descripcion
    @GetMapping("/buscar")
    public List<ProductoDto> buscarProductosPorTermino(@RequestParam String termino){
        return productoServicio.buscarProductosPorTermino(termino);
    }

    //Listar producto ordenados alfabeticamente
    @GetMapping("/ordenados")
    public List<ProductoDto> listarProductosOrdenados(){
        return productoServicio.listarProductosOrdenados();
    }

    //Buscar productos por rango de precios
    @GetMapping("/rango")
    public List<ProductoDto> buscarProductosPorRangoDePrecios(@RequestParam Double min,@RequestParam Double max){
        return productoServicio.buscarProductosPorRangoDePrecios(min, max);
    }
}
