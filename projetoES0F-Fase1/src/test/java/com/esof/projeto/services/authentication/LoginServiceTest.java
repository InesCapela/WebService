package com.esof.projeto.services.authentication;

import com.esof.projeto.models.Aluno;
import com.esof.projeto.models.builders.AlunoBuilder;
import com.esof.projeto.repositories.AlunoRepo;
import com.esof.projeto.services.AlunoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@WebMvcTest(controllers = LoginService.class)

class LoginServiceTest {

    @Autowired
    private LoginService loginService;

    @MockBean
    private AlunoService alunoService;


    @Test
    void addUser() {

        LoginService loginService = new LoginService(alunoService);

        Aluno aluno = new Aluno();
        aluno.setName("aluno");
        aluno.setNumero(1L);

        Credentials credentials = new Credentials();
        credentials.setUsername("aluno");
        credentials.setPassword("aluno");

        loginService.addUser("aluno", "aluno");
        assertNotNull(loginService);

        loginService.authenticateUser(aluno, credentials.toString());

        loginService.generateToken(credentials);

        loginService.generateToken("aluno","aluno");
    }
}