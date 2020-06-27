package com.esof.projeto.services;

import com.esof.projeto.models.Universidade;
import com.esof.projeto.models.builders.UniversidadeBuilder;
import com.esof.projeto.repositories.UniversidadeRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;


@WebMvcTest( controllers = UniversidadeService.class)
class UniversidadeServiceTest {

    @Autowired
    private UniversidadeService universidadeService;

    @MockBean
    private UniversidadeRepo universidadeRepo;

    @Test
    void createUniversidade() {
        UniversidadeBuilder universidadeBuilder = new UniversidadeBuilder().setName("uni");
        when(universidadeRepo.save(universidadeBuilder.build())).thenReturn(universidadeBuilder.build());
        Optional<Universidade> universidade =this.universidadeService.createUniversidade(universidadeBuilder.build());
        assertTrue(universidade.isPresent());

        when(universidadeRepo.findByName("uni")).thenReturn(Optional.of(universidadeBuilder.build()));
        assertTrue(this.universidadeService.createUniversidade(universidadeBuilder.build()).isEmpty());
    }

    @Test
    void findAll() {
        Set<Universidade> universidades = new HashSet<>() ;
        universidades.add(new UniversidadeBuilder().setName("uni1").build());
        universidades.add(new UniversidadeBuilder().setName("uni2").build());
        universidades.add(new UniversidadeBuilder().setName("uni3").build());
        universidades.add(new UniversidadeBuilder().setName("uni4").build());


        when(universidadeRepo.findAll()).thenReturn(universidades);

        assertEquals(4,universidadeService.findAll().size());
    }

    @Test
    void findById() {
        UniversidadeBuilder universidadeBuilder = new UniversidadeBuilder();
        when(universidadeRepo.findById(1L)).thenReturn(Optional.of(universidadeBuilder.build()));
        Optional<Universidade> universidadeOptional =this.universidadeService.findById(1L);

        assertTrue(universidadeOptional.isPresent());
    }

    @Test
    void findByName() {
        UniversidadeBuilder universidadeBuilder = new UniversidadeBuilder().setName("uni");
        when(universidadeRepo.findByName(universidadeBuilder.build().getName())).thenReturn(Optional.of(universidadeBuilder.build()));
        Optional<Universidade> universidadeOptional =this.universidadeService.findByName("uni");

        assertTrue(universidadeOptional.isPresent());
    }
}