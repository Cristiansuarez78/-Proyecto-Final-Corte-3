package com.mongodb.usuarios.Service;

import com.mongodb.usuarios.Models.Usuario;
import com.mongodb.usuarios.Repository.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder; // Inyección de PasswordEncoder

    // Método para crear un nuevo usuario
    public Usuario crearUsuario(@Valid Usuario usuario) {
        // Codificar la contraseña antes de guardarla
        String passwordEncoded = passwordEncoder.encode(usuario.getPassword());
        usuario.setPassword(passwordEncoded); // Establecer la contraseña codificada

        // Verificar que el correo electrónico es único
        if (usuarioRepository.existsByEmail(usuario.getEmail())) {
            throw new IllegalArgumentException("El correo electrónico ya está registrado");
        }

        return usuarioRepository.save(usuario); // Guardamos el usuario en la base de datos
    }

    // Obtener todos los usuarios
    public List<Usuario> obtenerTodos() {
        return usuarioRepository.findAll();
    }

    // Obtener un usuario por su ID
    public Optional<Usuario> obtenerPorId(String id) {
        return usuarioRepository.findById(id);
    }

    // Actualizar un usuario
    public Usuario actualizarUsuario(String id, @Valid Usuario usuario) {
        Optional<Usuario> usuarioExistente = usuarioRepository.findById(id);
        if (usuarioExistente.isEmpty()) {
            throw new IllegalArgumentException("Usuario no encontrado");
        }

        // Verificar si el correo electrónico es único
        if (!usuarioExistente.get().getEmail().equals(usuario.getEmail()) && usuarioRepository.existsByEmail(usuario.getEmail())) {
            throw new IllegalArgumentException("El correo electrónico ya está registrado");
        }

        // Actualizar la contraseña si ha cambiado
        if (!usuario.getPassword().equals(usuarioExistente.get().getPassword())) {
            usuario.setPassword(passwordEncoder.encode(usuario.getPassword())); // Encriptamos la nueva contraseña
        }

        usuario.setId(id);
        return usuarioRepository.save(usuario);
    }

    // Eliminar un usuario
    public void eliminarUsuario(String id) {
        Optional<Usuario> usuarioExistente = usuarioRepository.findById(id);
        if (usuarioExistente.isEmpty()) {
            throw new IllegalArgumentException("Usuario no encontrado");
        }
        usuarioRepository.deleteById(id);
    }
}

