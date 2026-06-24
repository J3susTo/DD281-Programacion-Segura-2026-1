package com.uqai.backend.service;

import com.uqai.backend.dto.response.UsuarioResponse;
import com.uqai.backend.entity.Usuario;
import com.uqai.backend.enums.Rol;
import com.uqai.backend.exception.ResourceNotFoundException;
import com.uqai.backend.repository.UsuarioRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.TestingAuthenticationToken;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UsuarioServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private UsuarioService usuarioService;

    @Test
    void listarUsuarios_debeRetornarUsuarios() {
        Usuario usuario = Usuario.builder()
                .id(Long.valueOf(1L))
                .nombre("Admin")
                .apellidos("UQ")
                .email("admin@uqai.pe")
                .password("hash")
                .rol(Rol.ADMIN)
                .area("Sistemas")
                .build();

        when(usuarioRepository.findAll()).thenReturn(List.of(usuario));

        List<UsuarioResponse> response = usuarioService.listarUsuarios();

        assertEquals(1, response.size());
        assertEquals("admin@uqai.pe", response.get(0).getEmail());

        verify(usuarioRepository, times(1)).findAll();
    }

    @Test
    void obtenerUsuarioPorId_adminPuedeVerCualquierUsuario() {
        Usuario usuario = Usuario.builder()
                .id(Long.valueOf(1L))
                .nombre("Usuario")
                .apellidos("Demo")
                .email("user@uqai.pe")
                .password("hash")
                .rol(Rol.USER)
                .area("Academy")
                .build();

        when(usuarioRepository.findById(Long.valueOf(1L))).thenReturn(Optional.of(usuario));

        TestingAuthenticationToken auth =
                new TestingAuthenticationToken("admin@uqai.pe", null, "ROLE_ADMIN");

        UsuarioResponse response = usuarioService.obtenerUsuarioPorId(Long.valueOf(1L), auth);

        assertEquals("user@uqai.pe", response.getEmail());
    }

    @Test
    void obtenerUsuarioPorId_userNoPuedeVerOtroUsuario() {
        Usuario usuario = Usuario.builder()
                .id(Long.valueOf(1L))
                .nombre("Usuario")
                .apellidos("Demo")
                .email("user@uqai.pe")
                .password("hash")
                .rol(Rol.USER)
                .area("Academy")
                .build();

        when(usuarioRepository.findById(Long.valueOf(1L))).thenReturn(Optional.of(usuario));

        TestingAuthenticationToken auth =
                new TestingAuthenticationToken("otro@uqai.pe", null, "ROLE_USER");

        assertThrows(
                AccessDeniedException.class,
                () -> usuarioService.obtenerUsuarioPorId(Long.valueOf(1L), auth)
        );
    }

    @Test
    void obtenerUsuarioPorId_siNoExisteLanzaResourceNotFound() {
        when(usuarioRepository.findById(Long.valueOf(99L))).thenReturn(Optional.empty());

        TestingAuthenticationToken auth =
                new TestingAuthenticationToken("admin@uqai.pe", null, "ROLE_ADMIN");

        assertThrows(
                ResourceNotFoundException.class,
                () -> usuarioService.obtenerUsuarioPorId(Long.valueOf(99L), auth)
        );
    }
}