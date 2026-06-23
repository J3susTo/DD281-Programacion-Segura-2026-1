package com.uqai.backend.dto;

import com.uqai.backend.entity.Usuario;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UsuarioResponse {
    private Long id;
    private String nombre;
    private String apellidos;
    private String email;
    private String rol;
    private String area;

    public static UsuarioResponse from(Usuario usuario) {
        return UsuarioResponse.builder()
                .id(usuario.getId())
                .nombre(usuario.getNombre())
                .apellidos(usuario.getApellidos())
                .email(usuario.getEmail())
                .rol(usuario.getRol().name())
                .area(usuario.getArea())
                .build();
    }
}
