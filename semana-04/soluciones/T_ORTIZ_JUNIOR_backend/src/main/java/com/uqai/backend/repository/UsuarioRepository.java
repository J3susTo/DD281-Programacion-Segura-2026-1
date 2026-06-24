package com.uqai.backend.repository;

import com.uqai.backend.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    // Este método buscará a un usuario por su correo exacto
    Optional<Usuario> findByEmail(String email);
}