package com.esof.projeto.services;

import com.esof.projeto.models.Disponibilidade;
import com.esof.projeto.models.builders.DisponibilidadeBuilder;
import com.esof.projeto.repositories.DisponibilidadeRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalTime;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@WebMvcTest(controllers = DisponibilidadeService.class)

class DisponibilidadeServiceTest {

    @Autowired
    private DisponibilidadeService disponibilidadeService;

    @MockBean
    private DisponibilidadeRepo disponibilidadeRepo;

    @Test
    void createdDisponibilidade() {
        DisponibilidadeBuilder disponibilidadeBuilder = new DisponibilidadeBuilder().setInicio(LocalTime.of(12,12));
        when(disponibilidadeRepo.save(disponibilidadeBuilder.build())).thenReturn(disponibilidadeBuilder.build());
        Optional<Disponibilidade> disponibilidade =this.disponibilidadeService.createdDisponibilidade(disponibilidadeBuilder.build());
        assertTrue(disponibilidade.isPresent());

        when(disponibilidadeRepo.findById(disponibilidadeBuilder.build().getId())).thenReturn(Optional.of(disponibilidadeBuilder.build()));
        assertTrue(this.disponibilidadeService.createdDisponibilidade(disponibilidadeBuilder.build()).isEmpty());
    }

    @Test
    void findAll() {
        Set<Disponibilidade> disponibilidades = new HashSet<>() ;
        disponibilidades.add(new DisponibilidadeBuilder().setInicio(LocalTime.of(12,12)).build());
        disponibilidades.add(new DisponibilidadeBuilder().setInicio(LocalTime.of(13,12)).build());
        disponibilidades.add(new DisponibilidadeBuilder().setInicio(LocalTime.of(14,12)).build());
        disponibilidades.add(new DisponibilidadeBuilder().setInicio(LocalTime.of(15,12)).build());

        when(disponibilidadeRepo.findAll()).thenReturn(disponibilidades);

        assertEquals(4,disponibilidadeService.findAll().size());
    }

    @Test
    void findById() {
        DisponibilidadeBuilder disponibilidadeBuilder = new DisponibilidadeBuilder();
        when(disponibilidadeRepo.findById(1L)).thenReturn(Optional.of(disponibilidadeBuilder.build()));
        Optional<Disponibilidade> disponibilidade =this.disponibilidadeService.findById(1L);

        assertTrue(disponibilidade.isPresent());
    }
}