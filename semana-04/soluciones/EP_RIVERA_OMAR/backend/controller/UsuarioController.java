package com.uqai.backend.controller;

import com.uqai.backend.dto.UsuarioResponse;
import com.uqai.backend.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioRepository usuarioRepository;

    @GetMapping
    public ResponseEntity<List<UsuarioResponse>> listarUsuarios() {
        // Convertimos la lista de Usuarios de la BD a cajitas seguras (UsuarioResponse)
        List<UsuarioResponse> usuarios = usuarioRepository.findAll()
                .stream()
                .map(UsuarioResponse::from)
                .collect(Collectors.toList());
        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponse> verUsuario(@PathVariable Long id) {
        return usuarioRepository.findById(id)
                .map(u -> ResponseEntity.ok(UsuarioResponse.from(u)))
                .orElse(ResponseEntity.notFound().build());
    }
}