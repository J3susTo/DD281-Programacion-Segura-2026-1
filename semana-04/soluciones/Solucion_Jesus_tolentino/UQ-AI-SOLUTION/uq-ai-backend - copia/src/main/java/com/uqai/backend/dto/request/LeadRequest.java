package com.uqai.backend.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LeadRequest {

    @NotBlank
    private String nombre;

    @Email
    @NotBlank
    private String email;

    private String empresa;

    private String telefono;

    private String mensaje;
}