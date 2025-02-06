package com.arka.producto.producto.services;

import com.arka.producto.producto.DTOs.ProductoCreateDTO;
import com.arka.producto.producto.DTOs.ProductoDto;
import com.arka.producto.producto.entities.Producto;
import com.arka.producto.producto.repository.ProductoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
public class ProductoServicio {
    @Autowired
    private ProductoRepositorio productoRepositorio;

    // Obtener todos los productos y transformarlos en DTOs
    public List<ProductoDto> obtenerTodosLosProductos(){
        return productoRepositorio.findAll()
                .stream()
                .map(this::convertirAProductoDTO)
                .collect(Collectors.toList());
    }
    public ProductoDto obtenerProductoPorId(Long id) {
        return productoRepositorio.findById(id)
                .map(this::convertirAProductoDTO)
                .orElse(null);
    }

    // Crear un nuevo producto a partir de un DTO de entrada
    public ProductoDto crearProducto(ProductoCreateDTO productoCreateDTO) {
        Producto producto = new Producto();
        producto.setNombre(productoCreateDTO.getNombre());
        producto.setDescripcion(productoCreateDTO.getDescripcion());
        producto.setPrecio(productoCreateDTO.getPrecio());

        Producto productoGuardado = productoRepositorio.save(producto);
        return convertirAProductoDTO(productoGuardado);
    }

    // Actualizar un producto existente a partir de un DTO de entrada
    public ProductoDto actualizarProducto(Long id, ProductoCreateDTO productoCreateDTO) {
        return productoRepositorio.findById(id).map(producto -> {
            producto.setNombre(productoCreateDTO.getNombre());
            producto.setDescripcion(productoCreateDTO.getDescripcion());
            producto.setPrecio(productoCreateDTO.getPrecio());

            Producto productoActualizado = productoRepositorio.save(producto);
            return convertirAProductoDTO(productoActualizado);
        }).orElse(null);
    }

    // Eliminar un producto por ID
    public boolean eliminarProducto(Long id) {
        if (productoRepositorio.existsById(id)) {
            productoRepositorio.deleteById(id);
            return true;
        }
        return false;
    }

    // Buscar productos por nombre o descripción
    public List<ProductoDto> buscarProductosPorTermino(String termino) {
        return productoRepositorio.findByNombreContainingIgnoreCaseOrDescripcionContainingIgnoreCase(termino, termino)
                .stream()
                .map(this::convertirAProductoDTO)
                .collect(Collectors.toList());
    }

    // Listar productos ordenados alfabéticamente
    public List<ProductoDto> listarProductosOrdenados() {
        return productoRepositorio.findAllByOrderByNombreAsc()
                .stream()
                .map(this::convertirAProductoDTO)
                .collect(Collectors.toList());
    }

    // Buscar productos en un rango de precios
    public List<ProductoDto> buscarProductosPorRangoDePrecios(Double min, Double max) {
        return productoRepositorio.findByPrecioBetween(min, max)
                .stream()
                .map(this::convertirAProductoDTO)
                .collect(Collectors.toList());
    }


    // Método privado para convertir un Producto en un ProductoDTO
    private ProductoDto convertirAProductoDTO(Producto producto) {
        return new ProductoDto(
                producto.getId(),
                producto.getNombre(),
                producto.getDescripcion(),
                producto.getPrecio()
        );
    }
}
