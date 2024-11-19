package com.mongodb.usuarios.Models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.Date;

@Document(collection = "usuarios")
public class Usuario {

    @Id
    private String id;  // Generado automáticamente por MongoDB

    @NotEmpty(message = "El nombre completo es obligatorio")
    @Size(min = 3, max = 50, message = "El nombre completo debe tener entre 3 y 50 caracteres")
    private String nombreCompleto;  // Nombre completo del usuario

    @NotEmpty(message = "El correo electrónico es obligatorio")
    @Email(message = "El correo electrónico debe tener un formato válido")
    private String email;  // Correo electrónico del usuario

    @NotEmpty(message = "La contraseña es obligatoria")
    @Size(min = 8, max = 20, message = "La contraseña debe tener entre 8 y 20 caracteres")
    private String password;  // Contraseña del usuario

    private Date fechaRegistro;  // Fecha de registro, se asigna automáticamente

    @Min(value = 13, message = "La edad debe ser mayor de 13 años")
    private Integer edad;  // Edad, opcional

    private String telefono;  // Número de teléfono, opcional

    @NotEmpty(message = "El rol es obligatorio")
    private String rol;  // Rol del usuario, como "Administrador" o "Lector"

    // Constructor vacío para MongoDB
    public Usuario() {
        this.fechaRegistro = new Date();  // Asigna la fecha actual como la fecha de registro
    }

    // Constructor con parámetros
    public Usuario(String nombreCompleto, String email, String password, Integer edad, String telefono, String rol) {
        this.nombreCompleto = nombreCompleto;
        this.email = email;
        this.password = password;
        this.fechaRegistro = new Date();  // Se asigna la fecha de registro al crear el usuario
        this.edad = edad;
        this.telefono = telefono;
        this.rol = rol;
    }

    // Getters y Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
}
