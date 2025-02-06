package com.arka.producto.producto.repository;

import com.arka.producto.producto.entities.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Locale;

@Repository
public interface ProductoRepositorio extends JpaRepository<Producto, Long> {
    //Buscar procuctos por nombre o descripcion
    List<Producto> findByNombreContainingIgnoreCaseOrDescripcionContainingIgnoreCase (String nombre, String descripcion);

    //Listar productor oredenados alfabeticamente
    List<Producto> findAllByOrderByNombreAsc();

    //Buscar productos por rando de precios
    List<Producto> findByPrecioBetween(Double min ,  Double max);
}
