package com.arka.producto.producto.controllers;

import com.arka.producto.producto.DTOs.ProductoCreateDTO;
import com.arka.producto.producto.DTOs.ProductoDto;
import com.arka.producto.producto.services.ProductoServicio;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.hamcrest.Matchers.containsString;


import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@WebMvcTest(ProductoControlador.class)
public class PruebaPeticiones {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductoServicio productoServicio;

    @Test
    void testObtenerTodosLosProductos() throws Exception {
        List<ProductoDto> productos = Arrays.asList(
                new ProductoDto(1L, "Teclado", "Teclado mecánico RGB", 79.99),
                new ProductoDto(2L, "Monitor", "Monitor 24 pulgadas", 199.99)
        );

        when(productoServicio.obtenerTodosLosProductos()).thenReturn(productos);

        mockMvc.perform(get("/productos"))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                    [
                        {"id":1,"nombre":"Teclado","descripcion":"Teclado mecánico RGB","precio":79.99},
                        {"id":2,"nombre":"Monitor","descripcion":"Monitor 24 pulgadas","precio":199.99}
                    ]
                """));
    }
//test GetByid
    @Test
    void testObtenerProductoPorId() throws Exception {
        ProductoDto producto = new ProductoDto(1L, "Teclado", "Teclado mecánico RGB", 79.99);

        when(productoServicio.obtenerProductoPorId(1L)).thenReturn(producto);

        mockMvc.perform(get("/productos/1"))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                    {"id":1,"nombre":"Teclado","descripcion":"Teclado mecánico RGB","precio":79.99}
                """));
    }

    //test crear prodcuto
    @Test
    void testCrearProducto() throws Exception {
        ProductoCreateDTO nuevoProducto = new ProductoCreateDTO("Laptop", "Laptop gamer", 1200.0);
        ProductoDto productoCreado = new ProductoDto(5L, "Laptop", "Laptop gamer", 1200.0);

        when(productoServicio.crearProducto(any(ProductoCreateDTO.class))).thenReturn(productoCreado);

        mockMvc.perform(post("/productos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                            {
                                "nombre": "Laptop",
                                "descripcion": "Laptop gamer",
                                "precio": 1200.0
                            }
                        """))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                    {"id":5,"nombre":"Laptop","descripcion":"Laptop gamer","precio":1200.0}
                """));
    }

    //pruebaPut
    @Test
    void testActualizarProducto() throws Exception {
        ProductoCreateDTO productoActualizado = new ProductoCreateDTO("Mouse", "Mouse inalámbrico", 39.99);
        ProductoDto productoDto = new ProductoDto(1L, "Mouse", "Mouse inalámbrico", 39.99);

        when(productoServicio.actualizarProducto(eq(1L), any(ProductoCreateDTO.class))).thenReturn(productoDto);

        mockMvc.perform(put("/productos/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                            {
                                "nombre": "Mouse",
                                "descripcion": "Mouse inalámbrico",
                                "precio": 39.99
                            }
                        """))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                    {"id":1,"nombre":"Mouse","descripcion":"Mouse inalámbrico","precio":39.99}
                """));
    }

    //pruebadelate
    @Test
    void testEliminarProducto() throws Exception {
        when(productoServicio.eliminarProducto(1L)).thenReturn(true);

        mockMvc.perform(delete("/productos/1"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Se ha eliminado el producto con Id: 1")));
    }
}
