package com.esof.projeto.services.authentication;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CredentialsTest {

    @Test
    void test(){

        Credentials credentials= new Credentials();

        credentials.setPassword("aluno");
        credentials.setUsername("aluno");

        assertEquals("aluno",credentials.getUsername());
        assertEquals("aluno",credentials.getPassword());
    }

}