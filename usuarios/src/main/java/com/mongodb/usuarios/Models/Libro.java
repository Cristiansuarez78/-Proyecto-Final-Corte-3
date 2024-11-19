package com.mongodb.usuarios.Models;

import jakarta.validation.constraints.*;
import java.time.LocalDate;

public class Libro {

    private String id;

    @NotBlank(message = "El título es obligatorio")
    @Size(min = 2, max = 100, message = "El título debe tener entre 2 y 100 caracteres")
    private String titulo;

    @NotBlank(message = "El autor es obligatorio")
    @Size(min = 3, max = 50, message = "El autor debe tener entre 3 y 50 caracteres")
    private String autor;

    @NotBlank(message = "El ISBN es obligatorio")
    @Size(min = 13, max = 13, message = "El ISBN debe tener exactamente 13 dígitos")
    private String isbn;

    private String genero;

    private LocalDate fechaPublicacion;

    private String idioma;
    private String descripcion;

    @NotNull(message = "La cantidad en stock es obligatoria")
    @Min(value = 1, message = "La cantidad en stock debe ser mayor que 0")
    @Max(value = 500, message = "La cantidad en stock no puede ser mayor a 500")
    private Integer cantidadStock = 0;  // Valor predeterminado a 0

    @Min(value = 0, message = "El precio debe ser positivo")
    @Max(value = 1000, message = "El precio no puede ser mayor a 1000")
    private Double precio;

    // Métodos getter y setter

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public LocalDate getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(LocalDate fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getCantidadStock() {
        return cantidadStock;
    }

    public void setCantidadStock(Integer cantidadStock) {
        this.cantidadStock = cantidadStock;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }
}


