package com.esof.projeto.services;

import com.esof.projeto.models.Curso;
import com.esof.projeto.models.builders.CursoBuilder;
import com.esof.projeto.models.builders.FaculdadeBuilder;
import com.esof.projeto.repositories.CursoRepo;
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

@WebMvcTest(controllers = CursoService.class)

class CursoServiceTest {

    @Autowired
    private CursoService cursoService;

    @MockBean
    private FaculdadeService faculdadeService;

    @MockBean
    private FaculdadeRepo faculdadeRepo;

    @MockBean
    private CursoRepo cursoRepo;


    @Test
    void createdCurso() {
        CursoBuilder cursoBuilder = new CursoBuilder().setName("curso");
        when(cursoRepo.save(cursoBuilder.build())).thenReturn(cursoBuilder.build());
        Optional<Curso> curso =this.cursoService.createdCurso(cursoBuilder.build());
        assertTrue(curso.isPresent());

        when(cursoRepo.findByName("curso")).thenReturn(Optional.of(cursoBuilder.build()));
        assertTrue(this.cursoService.createdCurso(cursoBuilder.build()).isEmpty());

    }

    @Test
    void findAll() {
         Set<Curso> cursos = new HashSet<>() ;
            cursos.add(new CursoBuilder().setName("curso1").build());
            cursos.add(new CursoBuilder().setName("curso2").build());
            cursos.add(new CursoBuilder().setName("curso3").build());
            cursos.add(new CursoBuilder().setName("curso4").build());

            when(cursoRepo.findAll()).thenReturn(cursos);

            assertEquals(4,cursoService.findAll().size());
    }

    @Test
    void createCurso() {
        CursoBuilder cursoBuilder = new CursoBuilder().setName("curso");
        FaculdadeBuilder faculdadeBuilder = new FaculdadeBuilder().setName("fac");

        faculdadeRepo.save(faculdadeBuilder.build());

        when(cursoRepo.findByName("curso")).thenReturn(Optional.of(cursoBuilder.build()));
        Optional<Curso> curso =this.cursoService.createCurso(faculdadeBuilder.build().getName(),cursoBuilder.build());

        assertFalse(curso.isPresent());

        when(cursoRepo.findByName("curso")).thenReturn(Optional.of(cursoBuilder.build()));
        assertTrue(this.cursoService.createCurso(faculdadeBuilder.build().getName(),cursoBuilder.build()).isEmpty());

    }

    @Test
    void findById() {
        CursoBuilder cursoBuilder = new CursoBuilder();
        when(cursoRepo.findById(1L)).thenReturn(Optional.of(cursoBuilder.build()));
        Optional<Curso> curso =this.cursoService.findById(1L);

        assertTrue(curso.isPresent());
    }

    @Test
    void findByName() {
        CursoBuilder cursoBuilder = new CursoBuilder().setName("curso");
        when(cursoRepo.findByName(cursoBuilder.build().getName())).thenReturn(Optional.of(cursoBuilder.build()));
        Optional<Curso> cursos =this.cursoService.findByName(cursoBuilder.build().getName());

        assertTrue(cursos.isPresent());
    }
}