package com.esof.projeto.services;

import com.esof.projeto.models.Explicacao;
import com.esof.projeto.models.builders.AlunoBuilder;
import com.esof.projeto.models.builders.ExplicacaoBuilder;
import com.esof.projeto.models.builders.ExplicadorBuilder;
import com.esof.projeto.repositories.AlunoRepo;
import com.esof.projeto.repositories.ExplicacaoRepo;
import com.esof.projeto.repositories.ExplicadorRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@WebMvcTest(controllers = ExplicacaoService.class)

class ExplicacaoServiceTest {

    @Autowired
    private ExplicacaoService explicacaoService;

    @MockBean
    private ExplicacaoRepo explicacaoRepo;

    @MockBean
    private AlunoRepo alunoRepo;

    @MockBean
    private ExplicadorRepo explicadorRepo;

    @Test
    void createdExplicacao() {

        AlunoBuilder alunoBuilder= new AlunoBuilder().setName("Rui");
        ExplicadorBuilder explicadorBuilder= new ExplicadorBuilder().setName("Alessandro");
        ExplicacaoBuilder explicacaoBuilder = new ExplicacaoBuilder().setInicio(LocalDateTime.of(12,12,12,12,12)).setAluno(alunoBuilder.build()).setExplicador(explicadorBuilder.build());
        when(explicacaoRepo.save(explicacaoBuilder.build())).thenReturn(explicacaoBuilder.build());
        Optional<Explicacao> explicacoes =this.explicacaoService.createdExplicacao(explicacaoBuilder.build());
        assertFalse(explicacoes.isPresent());

        when(explicacaoRepo.findById(explicacaoBuilder.build().getId())).thenReturn(Optional.of(explicacaoBuilder.build()));
        assertTrue(this.explicacaoService.createdExplicacao(explicacaoBuilder.build()).isEmpty());
    }

    @Test
    void findAll() {
        Set<Explicacao> explicacoes = new HashSet<>() ;
        explicacoes.add(new ExplicacaoBuilder().setInicio(LocalDateTime.of(12,12,12,12,12)).build());
        explicacoes.add(new ExplicacaoBuilder().setInicio(LocalDateTime.of(13,12,12,12,12)).build());
        explicacoes.add(new ExplicacaoBuilder().setInicio(LocalDateTime.of(14,12,12,12,12)).build());
        explicacoes.add(new ExplicacaoBuilder().setInicio(LocalDateTime.of(15,12,12,12,12)).build());

        when(explicacaoRepo.findAll()).thenReturn(explicacoes);

        assertEquals(4,explicacaoService.findAll().size());
    }

}