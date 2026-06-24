package com.uqai.backend.service;

import com.uqai.backend.dto.request.LeadRequest;
import com.uqai.backend.dto.response.LeadResponse;
import com.uqai.backend.entity.Lead;
import com.uqai.backend.repository.LeadRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LeadService {

    private final LeadRepository leadRepository;

    public LeadResponse guardarLead(LeadRequest request) {

        Lead lead = Lead.builder()
                .nombre(request.getNombre())
                .email(request.getEmail())
                .empresa(request.getEmpresa())
                .telefono(request.getTelefono())
                .mensaje(request.getMensaje())
                .build();

        return LeadResponse.from(leadRepository.save(lead));
    }

    public List<LeadResponse> listarLeads() {
        return leadRepository.findAll()
                .stream()
                .map(LeadResponse::from)
                .toList();
    }
}