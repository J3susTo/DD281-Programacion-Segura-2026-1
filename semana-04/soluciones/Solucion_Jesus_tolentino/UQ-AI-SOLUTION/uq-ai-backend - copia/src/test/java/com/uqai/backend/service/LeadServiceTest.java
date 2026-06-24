package com.uqai.backend.service;

import com.uqai.backend.dto.request.LeadRequest;
import com.uqai.backend.dto.response.LeadResponse;
import com.uqai.backend.entity.Lead;
import com.uqai.backend.repository.LeadRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LeadServiceTest {

    @Mock
    private LeadRepository leadRepository;

    @InjectMocks
    private LeadService leadService;

    @Test
    void guardarLead_debeGuardarLeadCorrectamente() {
        LeadRequest request = new LeadRequest();
        request.setNombre("Carlos Perez");
        request.setEmail("carlos@empresa.com");
        request.setEmpresa("Empresa Demo SAC");
        request.setTelefono("999888777");
        request.setMensaje("Estoy interesado en agentes IA");

        Lead leadGuardado = Lead.builder()
                .id(Long.valueOf(1L))
                .nombre(request.getNombre())
                .email(request.getEmail())
                .empresa(request.getEmpresa())
                .telefono(request.getTelefono())
                .mensaje(request.getMensaje())
                .build();

        when(leadRepository.save(any(Lead.class))).thenReturn(leadGuardado);

        LeadResponse response = leadService.guardarLead(request);

        assertNotNull(response);
        assertEquals(1L, response.getId());
        assertEquals("Carlos Perez", response.getNombre());
        assertEquals("carlos@empresa.com", response.getEmail());

        verify(leadRepository, times(1)).save(any(Lead.class));
    }

    @Test
    void listarLeads_debeRetornarListaDeLeads() {
        Lead lead = Lead.builder()
                .id(Long.valueOf(1L))
                .nombre("Ana Torres")
                .email("ana@empresa.com")
                .empresa("Tech SAC")
                .telefono("988777666")
                .mensaje("Quiero información sobre chatbots")
                .build();

        when(leadRepository.findAll()).thenReturn(List.of(lead));

        List<LeadResponse> response = leadService.listarLeads();

        assertEquals(1, response.size());
        assertEquals("Ana Torres", response.get(0).getNombre());

        verify(leadRepository, times(1)).findAll();
    }
}