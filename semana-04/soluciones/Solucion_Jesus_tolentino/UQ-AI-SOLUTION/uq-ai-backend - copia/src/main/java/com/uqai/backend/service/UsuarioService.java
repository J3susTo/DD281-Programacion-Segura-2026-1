package com.uqai.backend.service;

import com.uqai.backend.dto.response.UsuarioResponse;
import com.uqai.backend.entity.Usuario;
import com.uqai.backend.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import com.uqai.backend.exception.ResourceNotFoundException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public List<UsuarioResponse> listarUsuarios() {
        return usuarioRepository.findAll()
                .stream()
                .map(UsuarioResponse::from)
                .toList();
    }

    public UsuarioResponse obtenerUsuarioPorId(Long id, Authentication authentication) {

        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));

        String emailAutenticado = authentication.getName();

        boolean esAdmin = authentication.getAuthorities()
                .stream()
                .anyMatch(auth -> auth.getAuthority().equals("ROLE_ADMIN"));

        if (!esAdmin && !usuario.getEmail().equals(emailAutenticado)) {
            throw new AccessDeniedException("No tienes permiso para ver este usuario");
        }

        return UsuarioResponse.from(usuario);
    }

    public UsuarioResponse obtenerUsuarioActual(Authentication authentication) {

        String emailAutenticado = authentication.getName();

        Usuario usuario = usuarioRepository.findByEmail(emailAutenticado)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));

        return UsuarioResponse.from(usuario);
    }
}