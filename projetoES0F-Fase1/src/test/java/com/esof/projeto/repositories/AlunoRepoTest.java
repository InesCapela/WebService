package com.esof.projeto.repositories;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class AlunoRepoTest {

    @Autowired
    private AlunoRepo alunoRepo;

    @Test
    void findById() {
        alunoRepo.findByName("aluno1");
    }

    @Test
    void findByName() {
    }
}