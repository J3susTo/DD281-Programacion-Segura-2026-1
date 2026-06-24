package com.uqai.backend.service;

import com.uqai.backend.dto.request.LoginRequest;
import com.uqai.backend.dto.request.RegisterRequest;
import com.uqai.backend.dto.response.AuthResponse;
import com.uqai.backend.dto.response.UsuarioResponse;
import com.uqai.backend.entity.Usuario;
import com.uqai.backend.enums.Rol;
import com.uqai.backend.repository.UsuarioRepository;
import com.uqai.backend.security.JwtService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;


import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private JwtService jwtService;

    @Mock
    private AuthenticationManager authenticationManager;

    @InjectMocks
    private AuthService authService;

    @Test
    void register_debeRegistrarUsuarioCorrectamente() {
        RegisterRequest request = new RegisterRequest();
        request.setNombre("Juan");
        request.setApellidos("Perez");
        request.setEmail("juan@uqai.pe");
        request.setPassword("User2026!");
        request.setArea("Academy");

        Usuario usuarioGuardado = Usuario.builder()
                .id(Long.valueOf(1L))
                .nombre(request.getNombre())
                .apellidos(request.getApellidos())
                .email(request.getEmail())
                .password("hash-bcrypt")
                .rol(Rol.USER)
                .area(request.getArea())
                .build();

        when(usuarioRepository.existsByEmail(request.getEmail())).thenReturn(Boolean.valueOf(false));
        when(passwordEncoder.encode(request.getPassword())).thenReturn("hash-bcrypt");
        when(usuarioRepository.save(any(Usuario.class))).thenReturn(usuarioGuardado);

        UsuarioResponse response = authService.register(request);

        assertNotNull(response);
        assertEquals(1L, response.getId());
        assertEquals("juan@uqai.pe", response.getEmail());
        assertEquals("USER", response.getRol());

        verify(passwordEncoder, times(1)).encode("User2026!");
        verify(usuarioRepository, times(1)).save(any(Usuario.class));
    }

    @Test
    void register_siEmailExisteDebeLanzarExcepcion() {
        RegisterRequest request = new RegisterRequest();
        request.setNombre("Juan");
        request.setApellidos("Perez");
        request.setEmail("juan@uqai.pe");
        request.setPassword("User2026!");
        request.setArea("Academy");

        when(usuarioRepository.existsByEmail(request.getEmail())).thenReturn(Boolean.valueOf(true));

        assertThrows(
                IllegalArgumentException.class,
                () -> authService.register(request)
        );

        verify(usuarioRepository, never()).save(any(Usuario.class));
    }

    @Test
    void login_debeRetornarTokenCuandoCredencialesSonCorrectas() {
        LoginRequest request = new LoginRequest();
        request.setEmail("admin@uqai.pe");
        request.setPassword("Admin2026!");

        Usuario usuario = Usuario.builder()
                .id(Long.valueOf(1L))
                .nombre("Administrador")
                .apellidos("UQ AI")
                .email("admin@uqai.pe")
                .password("hash")
                .rol(Rol.ADMIN)
                .area("Sistemas")
                .build();

        when(usuarioRepository.findByEmail(request.getEmail())).thenReturn(Optional.of(usuario));
        when(jwtService.generateToken(request.getEmail())).thenReturn("jwt-token-demo");

        AuthResponse response = authService.login(request);

        assertNotNull(response);
        assertEquals("jwt-token-demo", response.getToken());
        assertEquals("ADMIN", response.getRol());
        assertEquals("Login exitoso", response.getMensaje());

        verify(authenticationManager, times(1)).authenticate(any(UsernamePasswordAuthenticationToken.class));
        verify(jwtService, times(1)).generateToken("admin@uqai.pe");
    }

    @Test
    void login_debeRetornarCredencialesIncorrectasCuandoFalla() {
        LoginRequest request = new LoginRequest();
        request.setEmail("admin@uqai.pe");
        request.setPassword("passwordIncorrecto");

        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenThrow(new BadCredentialsException("Error"));

        AuthResponse response = authService.login(request);

        assertNull(response.getToken());
        assertNull(response.getRol());
        assertEquals("Credenciales incorrectas", response.getMensaje());
    }
}