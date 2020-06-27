package com.esof.projeto.services;

import com.esof.projeto.models.Curso;
import com.esof.projeto.models.Explicador;
import com.esof.projeto.models.builders.CadeiraBuilder;
import com.esof.projeto.models.builders.CursoBuilder;
import com.esof.projeto.models.builders.ExplicadorBuilder;
import com.esof.projeto.repositories.CadeiraRepo;
import com.esof.projeto.repositories.CursoRepo;
import com.esof.projeto.repositories.ExplicadorRepo;
import com.esof.projeto.services.filters.explicador.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@WebMvcTest(controllers = ExplicadorServices.class)

class ExplicadorServicesTest {

    @Autowired
    private ExplicadorServices explicadorServices;

    @MockBean
    private FilterExplicadorServices filterExplicadorServices;

    @MockBean
    private ExplicadorRepo explicadorRepo;

    @MockBean
    private CursoRepo cursoRepo;

    @MockBean
    private CadeiraRepo cadeiraRepo;


    @Test
    void createExplicador() {
        ExplicadorBuilder explicadorBuilder = new ExplicadorBuilder().setName("explicador1");
        when(explicadorRepo.save(explicadorBuilder.build())).thenReturn(explicadorBuilder.build());
        Optional<Explicador> explicadorOptional =this.explicadorServices.createExplicador(explicadorBuilder.build());
        assertTrue(explicadorOptional.isPresent());

        when(explicadorRepo.findByName("explicador1")).thenReturn(Optional.of(explicadorBuilder.build()));
        assertTrue(this.explicadorServices.createExplicador(explicadorBuilder.build()).isEmpty());
    }

    @Test
    void findAll() {
        Set<Explicador> explicadores = new HashSet<>() ;
        explicadores.add(new ExplicadorBuilder().setName("explicador1").build());
        explicadores.add(new ExplicadorBuilder().setName("explicador2").build());
        explicadores.add(new ExplicadorBuilder().setName("explicador3").build());
        explicadores.add(new ExplicadorBuilder().setName("explicador4").build());

        when(explicadorRepo.findAll()).thenReturn(explicadores);

        assertEquals(4,explicadorServices.findAll().size());
    }

    @Test
    void findById() {
        ExplicadorBuilder explicadorBuilder = new ExplicadorBuilder();
        when(explicadorRepo.findById(1L)).thenReturn(Optional.of(explicadorBuilder.build()));
        Optional<Explicador> explicadorOptional =this.explicadorServices.findById(1L);

        assertTrue(explicadorOptional.isPresent());
    }

    @Test
    void findByName() {
        ExplicadorBuilder explicadorBuilder = new ExplicadorBuilder().setName("explicador");
        when(explicadorRepo.findByName(explicadorBuilder.build().getName())).thenReturn(Optional.of(explicadorBuilder.build()));
        Optional<Explicador> explicadorOptional=this.explicadorServices.findByName("explicador");

        assertTrue(explicadorOptional.isPresent());
    }

    @Test
    void updateCurso() {

        CursoBuilder cursoBuilder = new CursoBuilder().setName("curso");

        when(cursoRepo.findByName("curso")).thenReturn(Optional.of(cursoBuilder.build()));
        Optional<Curso> curso =this.cursoRepo.findByName("curso");

        assertTrue(curso.isPresent());

    }

    @Test
    void updateDisciplina() {

        ExplicadorBuilder explicadorBuilder = new ExplicadorBuilder().setName("explicador");

        when(explicadorServices.updateDisciplina(explicadorBuilder.build())).thenReturn(Optional.of(explicadorBuilder.build()));
        Optional<Explicador> explicadorOptional =this.explicadorRepo.findByName("explicador");

        assertTrue(explicadorOptional.isPresent());
    }
}