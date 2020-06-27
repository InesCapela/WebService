package com.esof.projeto.services;

import com.esof.projeto.models.Faculdade;
import com.esof.projeto.models.builders.FaculdadeBuilder;
import com.esof.projeto.repositories.FaculdadeRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@WebMvcTest(controllers = FaculdadeService.class)
class FaculdadeServiceTest {

    @Autowired
    private FaculdadeService faculdadeService;

    @MockBean
    private FaculdadeRepo faculdadeRepo;


    @Test
    void createFaculdade() {
        FaculdadeBuilder faculdadeBuilder = new FaculdadeBuilder().setName("fac");
        when(faculdadeRepo.save(faculdadeBuilder.build())).thenReturn(faculdadeBuilder.build());
        Optional<Faculdade> faculdade =this.faculdadeService.createFaculdade(faculdadeBuilder.build());
        assertTrue(faculdade.isPresent());

        when(faculdadeRepo.findByName("fac")).thenReturn(Optional.of(faculdadeBuilder.build()));
        assertTrue(this.faculdadeService.createFaculdade(faculdadeBuilder.build()).isEmpty());
    }

    @Test
    void findAll() {
        Set<Faculdade> faculdades = new HashSet<>() ;
        faculdades.add(new FaculdadeBuilder().setName("fac1").build());
        faculdades.add(new FaculdadeBuilder().setName("fac2").build());
        faculdades.add(new FaculdadeBuilder().setName("fac3").build());
        faculdades.add(new FaculdadeBuilder().setName("fac4").build());

        when(faculdadeRepo.findAll()).thenReturn(faculdades);

        assertEquals(4,faculdadeService.findAll().size());

    }

    @Test
    void findById() {
        FaculdadeBuilder faculdadeBuilder = new FaculdadeBuilder();
        when(faculdadeRepo.findById(1L)).thenReturn(Optional.of(faculdadeBuilder.build()));
        Optional<Faculdade> aluno =this.faculdadeService.findById(1L);

        assertTrue(aluno.isPresent());
    }

    @Test
    void findByName() {
        FaculdadeBuilder faculdadeBuilder = new FaculdadeBuilder().setName("fac");
        when(faculdadeRepo.findByName(faculdadeBuilder.build().getName())).thenReturn(Optional.of(faculdadeBuilder.build()));
        Optional<Faculdade> faculdade =this.faculdadeService.findByName("fac");

        assertTrue(faculdade.isPresent());
    }
}