package com.mongodb.usuarios.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf().disable()  // Desactiva CSRF, útil para APIs RESTful
            .authorizeRequests()
            .requestMatchers("/api/usuarios/**").permitAll()  // Permitir acceso sin autenticación a /api/usuarios
            .requestMatchers("/api/libros/**").permitAll()  // Permitir acceso sin autenticación a /api/usuarios
            .anyRequest().authenticated()  // Los demás endpoints requieren autenticación
            .and()
            .formLogin().disable();  // Desactivar el formulario de login si no lo usas

        return http.build();
    }
}
