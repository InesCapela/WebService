package com.esof.projeto.services;

import com.esof.projeto.models.Cadeira;
import com.esof.projeto.models.builders.CadeiraBuilder;
import com.esof.projeto.models.builders.CursoBuilder;
import com.esof.projeto.repositories.CadeiraRepo;

import com.esof.projeto.repositories.CursoRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@WebMvcTest(controllers = CadeiraService.class)

class CadeiraServiceTest {

    @Autowired
    private CadeiraService cadeiraService;

    @MockBean
    private CursoService cursoService;

    @MockBean
    private CadeiraRepo cadeiraRepo;

    @MockBean
    private CursoRepo cursoRepo;

    @Test
    void createdCadeira() {
        CadeiraBuilder cadeiraBuilder = new CadeiraBuilder().setName("cadeira");
        when(cadeiraRepo.save(cadeiraBuilder.build())).thenReturn(cadeiraBuilder.build());
        Optional<Cadeira> cadeira =this.cadeiraService.createdCadeira(cadeiraBuilder.build());
        assertTrue(cadeira.isPresent());

        when(cadeiraRepo.findByName("cadeira")).thenReturn(Optional.of(cadeiraBuilder.build()));
        assertTrue(this.cadeiraService.createdCadeira(cadeiraBuilder.build()).isEmpty());
    }

    @Test
    void findAll() {
        Set<Cadeira> cadeiras = new HashSet<>() ;
        cadeiras.add(new CadeiraBuilder().setName("cadeira1").build());
        cadeiras.add(new CadeiraBuilder().setName("cadeira2").build());
        cadeiras.add(new CadeiraBuilder().setName("cadeira3").build());
        cadeiras.add(new CadeiraBuilder().setName("aluno4").build());

        when(cadeiraRepo.findAll()).thenReturn(cadeiras);

        assertEquals(4,cadeiraService.findAll().size());
    }

    @Test
    void findById(){
        CadeiraBuilder cadeiraBuilder = new CadeiraBuilder();
        when(cadeiraRepo.findById(1L)).thenReturn(Optional.of(cadeiraBuilder.build()));
        Optional<Cadeira> cadeira =this.cadeiraService.findById(1L);

        assertTrue(cadeira.isPresent());
    }

    @Test
    void findByName() {
        CadeiraBuilder cadeiraBuilder = new CadeiraBuilder().setName("cadeira1");
        when(cadeiraRepo.findByName(cadeiraBuilder.build().getName())).thenReturn(Optional.of(cadeiraBuilder.build()));
        Optional<Cadeira> cadeira =this.cadeiraService.findByName(cadeiraBuilder.build().getName());

        assertTrue(cadeira.isPresent());
    }

    @Test
    void updateCursoCadeira() {
        CadeiraBuilder cadeiraBuilder = new CadeiraBuilder().setName("cadeira1");
        CursoBuilder cursoBuilder = new CursoBuilder().setName("curso");

        when(cadeiraService.updateCursoCadeira(cadeiraBuilder.build().getName(),cursoBuilder.build())).thenReturn(Optional.of(cadeiraBuilder.build()));
        Optional<Cadeira> cadeira =this.cadeiraService.updateCursoCadeira(cadeiraBuilder.build().getName(),cursoBuilder.build());

        assertTrue(cadeira.isPresent());
    }

    @Test
    void createCadeira() {
        CursoBuilder cursoBuilder = new CursoBuilder().setName("curso");
        CadeiraBuilder cadeiraBuilder = new CadeiraBuilder().setName("cadeira");

        cursoRepo.save(cursoBuilder.build());

        when(cadeiraRepo.findByName("cadeira")).thenReturn(Optional.of(cadeiraBuilder.build()));
        Optional<Cadeira> cadeira =this.cadeiraService.createCadeira(cadeiraBuilder.build(),cursoBuilder.build().getName());

        assertFalse(cadeira.isPresent());

        when(cadeiraRepo.findByName("cadeira")).thenReturn(Optional.of(cadeiraBuilder.build()));
        assertTrue(this.cadeiraService.createCadeira(cadeiraBuilder.build(),cursoBuilder.build().getName()).isEmpty());
    }
}