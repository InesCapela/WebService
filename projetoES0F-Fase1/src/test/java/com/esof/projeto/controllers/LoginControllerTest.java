package com.esof.projeto.controllers;

import com.esof.projeto.services.authentication.Credentials;
import com.esof.projeto.services.authentication.LoginService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;


import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = LoginController.class)

class LoginControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LoginService loginService;

    @MockBean
    private ObjectMapper objectMapper;


    @Test
    void login() throws Exception {

        Credentials credentials= new Credentials();
        credentials.setPassword("aluno");
        credentials.setUsername("aluno");

        String tokenJason = "token";

        String credentialsJason = objectMapper.writeValueAsString(credentials);

        when(this.loginService.generateToken(credentials)).thenReturn(Optional.of("token"));
        this.mockMvc.perform(post("/login").contentType(MediaType.APPLICATION_JSON).content(tokenJason)).andExpect(
                status().isOk());

        when(this.loginService.generateToken(credentials)).thenReturn(Optional.empty());
        this.mockMvc.perform(post("/login").contentType(MediaType.APPLICATION_JSON).content(credentialsJason));

    }
}