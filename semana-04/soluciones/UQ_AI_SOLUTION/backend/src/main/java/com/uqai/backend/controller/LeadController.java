package com.uqai.backend.controller;

import com.uqai.backend.entity.Lead;
import com.uqai.backend.repository.LeadRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/leads")
@RequiredArgsConstructor
public class LeadController {

    private final LeadRepository leadRepository;

    @PostMapping
    public Lead crearLead(@Valid @RequestBody Lead lead) {
        return leadRepository.save(lead);
    }

    @GetMapping
    public List<Lead> listarLeads() {
        return leadRepository.findAll();
    }
}
