package com.uqai.backend.controller;

import com.uqai.backend.dto.response.UsuarioResponse;
import com.uqai.backend.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
//AUTORIA DE JESUSS TOLENTINO
@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    @GetMapping
    public List<UsuarioResponse> listarUsuarios() {
        return usuarioService.listarUsuarios();
    }

    @GetMapping("/{id}")
    public UsuarioResponse obtenerUsuarioPorId(
            @PathVariable Long id,
            Authentication authentication
    ) {
        return usuarioService.obtenerUsuarioPorId(id, authentication);
    }
    @GetMapping("/me")
    public UsuarioResponse obtenerUsuarioActual(Authentication authentication) {
        return usuarioService.obtenerUsuarioActual(authentication);
    }
}