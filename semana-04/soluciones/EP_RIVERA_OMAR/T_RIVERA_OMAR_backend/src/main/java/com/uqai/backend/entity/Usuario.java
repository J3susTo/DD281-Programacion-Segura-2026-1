package com.uqai.backend.entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String apellidos;
    
    @Column(unique = true, nullable = false)
    private String email;
    
    private String password;

    @Enumerated(EnumType.STRING)
    private Rol rol;
}