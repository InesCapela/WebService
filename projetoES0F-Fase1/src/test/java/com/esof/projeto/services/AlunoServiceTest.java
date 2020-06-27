package com.esof.projeto.services;

import com.esof.projeto.models.Aluno;
import com.esof.projeto.models.builders.AlunoBuilder;
import com.esof.projeto.repositories.AlunoRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


@WebMvcTest(controllers = AlunoService.class)
class AlunoServiceTest {

    @Autowired
    private AlunoService alunoService;

    @MockBean
    private AlunoRepo alunoRepo;


    @Test
    void createdAluno() {
        AlunoBuilder alunoBuilder = new AlunoBuilder().setName("aluno1");
        when(alunoRepo.save(alunoBuilder.build())).thenReturn(alunoBuilder.build());
        Optional<Aluno> aluno =this.alunoService.createdAluno(alunoBuilder.build());
        assertTrue(aluno.isPresent());

        when(alunoRepo.findByName("aluno1")).thenReturn(Optional.of(alunoBuilder.build()));
        assertTrue(this.alunoService.createdAluno(alunoBuilder.build()).isEmpty());
    }


    @Test
    void findAll() {
        Set<Aluno> alunos = new HashSet<>() ;
        alunos.add(new AlunoBuilder().setName("aluno1").setNumero(null).setPassword(null).build());
        alunos.add(new AlunoBuilder().setName("aluno2").setNumero(null).setPassword(null).build());
        alunos.add(new AlunoBuilder().setName("aluno3").setNumero(null).setPassword(null).build());
        alunos.add(new AlunoBuilder().setName("aluno4").setNumero(null).setPassword(null).build());

        when(alunoRepo.findAll()).thenReturn(alunos);

        assertEquals(4,alunoService.findAll().size());

    }

    @Test
    void findById() {
        AlunoBuilder alunoBuilder = new AlunoBuilder();
        when(alunoRepo.findById(1L)).thenReturn(Optional.of(alunoBuilder.build()));
        Optional<Aluno> aluno =this.alunoService.findById(1L);

        assertTrue(aluno.isPresent());
    }

    @Test
    void findByName() {
        AlunoBuilder alunoBuilder = new AlunoBuilder().setName("aluno1");
        when(alunoRepo.findByName(alunoBuilder.build().getName())).thenReturn(Optional.of(alunoBuilder.build()));
        Optional<Aluno> aluno =this.alunoService.findByName("aluno1");

        assertTrue(aluno.isPresent());
    }
}