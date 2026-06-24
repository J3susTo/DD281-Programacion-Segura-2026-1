package com.uqai.backend.service;

import com.uqai.backend.dto.AuthResponse;
import com.uqai.backend.dto.LoginRequest;
import com.uqai.backend.dto.RegisterRequest;
import com.uqai.backend.dto.UsuarioResponse;
import com.uqai.backend.entity.Rol;
import com.uqai.backend.entity.Usuario;
import com.uqai.backend.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public UsuarioResponse register(RegisterRequest request) {
        // PROGRAMACIÓN SEGURA: Encriptamos la contraseña con Bcrypt antes de guardarla
        Usuario usuario = Usuario.builder()
                .nombre(request.getNombre())
                .apellidos(request.getApellidos())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword())) // Aquí se aplica el hash
                .rol(Rol.USER) // Por defecto, todos los que se registran por la web son USER
                .area(request.getArea())
                .build();

        Usuario savedUsuario = usuarioRepository.save(usuario);
        return UsuarioResponse.from(savedUsuario);
    }

    public AuthResponse login(LoginRequest request) {
        // Spring Security verifica si el email y la contraseña coinciden
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );

        // Si llegamos a esta línea, es porque la contraseña era correcta. Buscamos al usuario en la BD.
        Usuario usuario = usuarioRepository.findByEmail(request.getEmail())
                .orElseThrow(); 

        // Generamos la "pulsera VIP" (JWT)
        String token = jwtService.generateToken(usuario.getEmail());

        return AuthResponse.builder()
                .token(token)
                .rol(usuario.getRol().name())
                .mensaje("OK")
                .build();
    }
}