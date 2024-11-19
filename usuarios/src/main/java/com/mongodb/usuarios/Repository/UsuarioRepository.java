package com.mongodb.usuarios.Repository;

import com.mongodb.usuarios.Models.Usuario;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends MongoRepository<Usuario, String> {
	boolean existsByNombreCompleto(String nombreCompleto);
    boolean existsByEmail(String email);
    Optional<Usuario> findByEmail(String email);
}
