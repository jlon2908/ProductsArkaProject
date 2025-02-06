package com.arka.producto.producto.DTOs;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class ProductoCreateDTO {

    @NotNull
    @Size(min = 2, max = 100)
    private String nombre;

    @NotNull
    private String descripcion;

    @NotNull
    private Double precio;

    //Contructor
    public ProductoCreateDTO(String nombre, String descripcion, Double precio) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
    }

    //Getter and Setters

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }
}
