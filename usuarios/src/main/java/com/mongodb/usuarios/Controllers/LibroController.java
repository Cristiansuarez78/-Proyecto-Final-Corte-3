package com.mongodb.usuarios.Controllers;

import com.mongodb.usuarios.Models.Libro;
import com.mongodb.usuarios.Service.LibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/libros")
public class LibroController {
    @Autowired
    private LibroService libroService;

    // Obtener todos los libros
    @GetMapping
    public List<Libro> obtenerLibros() {
        return libroService.obtenerTodos();
    }

    // Obtener un libro por ID
    @GetMapping("/{id}")
    public ResponseEntity<Libro> obtenerLibroPorId(@PathVariable String id) {
        Optional<Libro> libro = libroService.obtenerPorId(id);
        return libro.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }



    // Crear un nuevo libro
    @PostMapping
    public ResponseEntity<Libro> crearLibro(@RequestBody Libro libro) {
        try {
            // Si el campo cantidadStock no se ha proporcionado o es nulo, asignamos un valor por defecto o rechazamos la solicitud
            if (libro.getCantidadStock() == null) {
                return ResponseEntity.badRequest().body(null);
            }
            Libro nuevoLibro = libroService.crearLibro(libro);
            return ResponseEntity.ok(nuevoLibro);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }


    // Actualizar un libro existente
    @PutMapping("/{id}")
    public ResponseEntity<Libro> actualizarLibro(@PathVariable String id, @RequestBody Libro libro) {
        try {
            Libro libroActualizado = libroService.actualizarLibro(id, libro);
            return ResponseEntity.ok(libroActualizado);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    // Eliminar un libro
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarLibro(@PathVariable String id) {
        try {
            libroService.eliminarLibro(id);  // Llama al servicio para eliminar el libro
            return ResponseEntity.noContent().build();  // 204 No Content si se elimina correctamente
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();  // 404 Not Found si no se encuentra el libro
        }
    }
}


