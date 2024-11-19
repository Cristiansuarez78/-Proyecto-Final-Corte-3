package com.mongodb.usuarios.Service;

import com.mongodb.libros.Utils.Constantes;
import com.mongodb.usuarios.Models.Libro;
import com.mongodb.usuarios.Repository.LibroRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class LibroService {

    @Autowired
    private LibroRepository libroRepository;

    // Obtener todos los libros
    public List<Libro> obtenerTodos() {
        return libroRepository.findAll();
    }

    // Obtener un libro por ID
    public Optional<Libro> obtenerPorId(String id) {
        return libroRepository.findById(id);
    }

    // Crear un nuevo libro
    public Libro crearLibro(Libro libro) {
        // Validar que la cantidad en stock no sea nula y que sea válida
        if (libro.getCantidadStock() == null || libro.getCantidadStock() <= 0 || libro.getCantidadStock() > 500) {
            throw new IllegalArgumentException("La cantidad en stock debe ser un número positivo y no mayor a 500");
        }

        // Validar ISBN (debe ser único y tener 13 dígitos)
        if (libroRepository.existsByIsbn(libro.getIsbn())) {
            throw new IllegalArgumentException("El ISBN ya está registrado");
        }

        // Validar que la fecha de publicación no sea futura
        if (libro.getFechaPublicacion() != null && libro.getFechaPublicacion().isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("La fecha de publicación no puede ser en el futuro");
        }

        // Validar que el género esté en la lista de géneros válidos
        if (!Constantes.GENEROS_VALIDOS.contains(libro.getGenero())) {
            throw new IllegalArgumentException("El género no es válido");
        }

        // Validar que el idioma esté en la lista de idiomas válidos
        if (!Constantes.IDIOMAS_VALIDOS.contains(libro.getIdioma())) {
            throw new IllegalArgumentException("El idioma no es válido");
        }

        // Guardar el libro en la base de datos
        return libroRepository.save(libro);
    }

    // Actualizar un libro existente
    public Libro actualizarLibro(String id, Libro libro) {
        // Verificar que el libro exista
        Optional<Libro> libroExistente = libroRepository.findById(id);
        if (libroExistente.isEmpty()) {
            throw new IllegalArgumentException("El libro no existe");
        }

        // Validar que la cantidad en stock no sea nula y que sea válida
        if (libro.getCantidadStock() == null || libro.getCantidadStock() <= 0 || libro.getCantidadStock() > 500) {
            throw new IllegalArgumentException("La cantidad en stock debe ser un número positivo y no mayor a 500");
        }

        // Validar ISBN (debe ser único y tener 13 dígitos)
        if (libroRepository.existsByIsbn(libro.getIsbn()) && !libro.getIsbn().equals(libroExistente.get().getIsbn())) {
            throw new IllegalArgumentException("El ISBN ya está registrado");
        }

        // Validar que la fecha de publicación no sea futura
        if (libro.getFechaPublicacion() != null && libro.getFechaPublicacion().isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("La fecha de publicación no puede ser en el futuro");
        }

        // Validar que el género esté en la lista de géneros válidos
        if (!Constantes.GENEROS_VALIDOS.contains(libro.getGenero())) {
            throw new IllegalArgumentException("El género no es válido");
        }

        // Validar que el idioma esté en la lista de idiomas válidos
        if (!Constantes.IDIOMAS_VALIDOS.contains(libro.getIdioma())) {
            throw new IllegalArgumentException("El idioma no es válido");
        }

        // Actualizar los datos del libro
        libro.setId(id);  // Asegurarse de que se conserva el ID
        return libroRepository.save(libro);
    }

    // Eliminar un libro
    public void eliminarLibro(String id) {
        Optional<Libro> libro = libroRepository.findById(id);
        if (libro.isEmpty()) {
            throw new IllegalArgumentException("El libro no existe");
        }
        libroRepository.deleteById(id);
    }
}

