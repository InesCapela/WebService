package com.esof.projeto;

import com.esof.projeto.repositories.*;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest(controllers = Bootstrap.class)

class BootstrapTest {


    @MockBean
    private AlunoRepo alunoRepo;

    @MockBean
    private CadeiraRepo cadeiraRepo;

    @MockBean
    private CursoRepo cursoRepo;

    @MockBean
    private FaculdadeRepo faculdadeRepo;

    @MockBean
    private ExplicadorRepo explicadorRepo;

    @MockBean
    private ExplicacaoRepo explicacaoRepo;

    @MockBean
    private UniversidadeRepo universidadeRepo;


    @Test
    void onApplicationEvent() {

        assertNotNull(universidadeRepo.findByName("uni1"));
        assertNotNull(alunoRepo.findByName("Rui"));
        assertNotNull(cursoRepo.findByName("curso"));
        assertNotNull(cadeiraRepo.findByName("cadeira1"));
        assertNotNull(faculdadeRepo.findByName("fac"));
        assertNotNull(explicadorRepo.findByName("Alessandro"));
        assertNotNull(explicacaoRepo.findById(1L));
    }
}