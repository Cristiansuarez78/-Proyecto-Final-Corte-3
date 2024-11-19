package com.mongodb.usuarios.Repository;

import com.mongodb.usuarios.Models.Libro;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LibroRepository extends MongoRepository<Libro, String> {
    boolean existsByIsbn(String isbn);
}
