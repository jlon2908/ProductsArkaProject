package com.arka.producto.producto.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class PruebaControlador {
    @Autowired
    private ProductoControlador productoControlador;

    @Test
    void testControladorInyectado() {
        assertThat(productoControlador).isNotNull();
    }

}
