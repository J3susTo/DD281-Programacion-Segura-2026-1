package com.uqai.backend.service;

import com.uqai.backend.dto.request.LoginRequest;
import com.uqai.backend.dto.request.RegisterRequest;
import com.uqai.backend.dto.response.AuthResponse;
import com.uqai.backend.dto.response.UsuarioResponse;
import com.uqai.backend.entity.Usuario;
import com.uqai.backend.enums.Rol;
import com.uqai.backend.repository.UsuarioRepository;
import com.uqai.backend.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.BadCredentialsException;
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

        if (usuarioRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("El email ya está registrado");
        }

        Usuario usuario = Usuario.builder()
                .nombre(request.getNombre())
                .apellidos(request.getApellidos())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .rol(Rol.USER)
                .area(request.getArea())
                .build();

        Usuario usuarioGuardado = usuarioRepository.save(usuario);

        return UsuarioResponse.from(usuarioGuardado);
    }

    public AuthResponse login(LoginRequest request) {

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(),
                            request.getPassword()
                    )
            );

            Usuario usuario = usuarioRepository.findByEmail(request.getEmail())
                    .orElseThrow(() -> new BadCredentialsException("Credenciales incorrectas"));

            String token = jwtService.generateToken(usuario.getEmail());

            return new AuthResponse(
                    token,
                    usuario.getRol().name(),
                    "Login exitoso"
            );

        } catch (BadCredentialsException e) {
            return new AuthResponse(
                    null,
                    null,
                    "Credenciales incorrectas"
            );
        }
    }
}