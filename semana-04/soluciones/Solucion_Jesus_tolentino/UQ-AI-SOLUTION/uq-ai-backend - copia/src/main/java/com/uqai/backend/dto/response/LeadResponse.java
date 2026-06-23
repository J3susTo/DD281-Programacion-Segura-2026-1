package com.uqai.backend.dto.response;

import com.uqai.backend.entity.Lead;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class LeadResponse {

    private Long id;
    private String nombre;
    private String email;
    private String empresa;
    private String telefono;
    private String mensaje;
    private LocalDateTime fechaRegistro;

    public static LeadResponse from(Lead lead) {
        return LeadResponse.builder()
                .id(lead.getId())
                .nombre(lead.getNombre())
                .email(lead.getEmail())
                .empresa(lead.getEmpresa())
                .telefono(lead.getTelefono())
                .mensaje(lead.getMensaje())
                .fechaRegistro(lead.getFechaRegistro())
                .build();
    }
}