package com.esof.projeto.controllers;

import com.esof.projeto.models.Aluno;
import com.esof.projeto.models.builders.AlunoBuilder;
import com.esof.projeto.services.AlunoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = AlunoController.class)
class AlunoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AlunoService alunoService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void createAluno() throws Exception {
        AlunoBuilder alunoBuilder=new AlunoBuilder().setName("aluno1").setNumero(null).setPassword(null);

        String jsonRequest = this.objectMapper.writeValueAsString(alunoBuilder.build());

        when(alunoService.createdAluno(alunoBuilder.build())).thenReturn(Optional.of(alunoBuilder.build()));

        this.mockMvc.perform(
                post("/aluno").contentType(MediaType.APPLICATION_JSON).content(jsonRequest)
        ).andExpect(
                status().isOk()
        );


        AlunoBuilder existingAluno=new AlunoBuilder().setName("aluno2").setNumero(null).setPassword(null);

        when(this.alunoService.createdAluno(existingAluno.build())).thenReturn(Optional.empty());

        String existingAlunoJson = this.objectMapper.writeValueAsString(existingAluno.build());

        this.mockMvc.perform(
                post("/aluno").contentType(MediaType.APPLICATION_JSON).content(existingAlunoJson)
        ).andExpect(
                status().isBadRequest()
        );

    }

    @Test
    void getAlunosById() throws Exception {

        AlunoBuilder alunoBuilder=new AlunoBuilder().setName("aluno1").setNumero(1L).setPassword(null);

        when(this.alunoService.findById(1L)).thenReturn(Optional.of(alunoBuilder.build()));

        String responseJson = this.mockMvc.perform(
                get("/aluno/1")
        ).andExpect(
                status().isOk()
        ).andReturn().getResponse().getContentAsString();

        Aluno responseAluno = this.objectMapper.readValue(responseJson, Aluno.class);
        assertEquals(alunoBuilder.build(), responseAluno);

        this.mockMvc.perform(
                get("/aluno/2")
        ).andExpect(
                status().isNotFound()
        );
    }

    @Test
    void getAlunosByNome() throws Exception {

        AlunoBuilder alunoBuilder=new AlunoBuilder().setName("aluno1").setNumero(null).setPassword(null);

        when(this.alunoService.findByName(alunoBuilder.build().getName())).thenReturn(Optional.of(alunoBuilder.build()));

        String responseJson = this.mockMvc.perform(
                get("/aluno/name/aluno1")
        ).andExpect(
                status().isOk()
        ).andReturn().getResponse().getContentAsString();

        Aluno responseAluno = this.objectMapper.readValue(responseJson, Aluno.class);
        assertEquals(alunoBuilder.build(), responseAluno);

        this.mockMvc.perform(
                get("/aluno/name/aluno2")
        ).andExpect(
                status().isNotFound()
        );

    }

    @Test
    void getAllAlunos() throws Exception {

        Set<Aluno> alunos=new HashSet<>();
        AlunoBuilder alunoBuilder1= new AlunoBuilder().setName("aluno1");
        AlunoBuilder alunoBuilder2= new AlunoBuilder().setName("aluno2");
        AlunoBuilder alunoBuilder3= new AlunoBuilder().setName("aluno3");

        alunos.add(alunoBuilder1.build());
        alunos.add(alunoBuilder2.build());
        alunos.add(alunoBuilder3.build());

        when(this.alunoService.findAll()).thenReturn(alunos);

        String responseJson=this.mockMvc.perform(
                get("/aluno")
        ).andExpect(
                status().isOk()
        ).andReturn().getResponse().getContentAsString();

        List<Aluno> alunos1=objectMapper.readValue(responseJson, ArrayList.class);
        assertEquals(3,alunos1.size());
    }
}