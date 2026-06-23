package com.uqai.backend.config;

import com.uqai.backend.entity.Usuario;
import com.uqai.backend.enums.Rol;
import com.uqai.backend.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
//AUTORIA DE JESUSS TOLENTINO
@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {

        if (!usuarioRepository.existsByEmail("admin@uqai.pe")) {

            Usuario admin = Usuario.builder()
                    .nombre("Administrador")
                    .apellidos("UQ AI")
                    .email("admin@uqai.pe")
                    .password(passwordEncoder.encode("Admin2026!"))
                    .rol(Rol.ADMIN)
                    .area("Sistemas")
                    .build();

            usuarioRepository.save(admin);
        }
    }
}