package com.uqai.backend;

import com.uqai.backend.entity.Rol;
import com.uqai.backend.entity.Usuario;
import com.uqai.backend.repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataSeeder {
    
    @Bean
    CommandLineRunner initDatabase(UsuarioRepository repo, PasswordEncoder encoder) {
        return args -> {
            // Verificamos si ya existe el admin para no duplicar
            if (repo.findByEmail("admin@uqai.com").isEmpty()) {
                Usuario admin = Usuario.builder()
                        .nombre("Administrador")
                        .apellidos("Sistema")
                        .email("admin@uqai.com")
                        .password(encoder.encode("admin123"))
                        .rol(Rol.ADMIN)
                        .area("SISTEMAS")
                        .build();
                
                repo.save(admin);
            }
        };
    }
}