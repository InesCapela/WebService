package com.esof.projeto.controllers;

import com.esof.projeto.models.Universidade;
import com.esof.projeto.models.builders.UniversidadeBuilder;
import com.esof.projeto.services.UniversidadeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = UniversidadeController.class)
class UniversidadeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UniversidadeService universidadeService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void createUniversidade() throws Exception{
        UniversidadeBuilder universidadeBuilder=new UniversidadeBuilder().setName("uni1");

        String jsonRequest = this.objectMapper.writeValueAsString(universidadeBuilder.build());

        when(universidadeService.createUniversidade(universidadeBuilder.build())).thenReturn(Optional.of(universidadeBuilder.build()));

        this.mockMvc.perform(
                post("/universidade").contentType(MediaType.APPLICATION_JSON).content(jsonRequest)
        ).andExpect(
                status().isOk()
        );


        UniversidadeBuilder existingUniversidadeBuilder=new UniversidadeBuilder().setName("uni2");

        when(this.universidadeService.createUniversidade(universidadeBuilder.build())).thenReturn(Optional.empty());

        String existingUniversidadeJson = this.objectMapper.writeValueAsString(existingUniversidadeBuilder.build());

        this.mockMvc.perform(
                post("/universidade").contentType(MediaType.APPLICATION_JSON).content(existingUniversidadeJson)
        ).andExpect(
                status().isBadRequest()
        );
    }

    @Test
    void getAllUniversidades() throws Exception{
        Set<Universidade> universidades=new HashSet<>();
        UniversidadeBuilder universidadeBuilder1= new UniversidadeBuilder().setName("uni1");
        UniversidadeBuilder universidadeBuilder2= new UniversidadeBuilder().setName("uni2");
        UniversidadeBuilder universidadeBuilder3= new UniversidadeBuilder().setName("uni3");


        universidades.add(universidadeBuilder1.build());
        universidades.add(universidadeBuilder2.build());
        universidades.add(universidadeBuilder3.build());

        when(this.universidadeService.findAll()).thenReturn(universidades);

        String responseJson=this.mockMvc.perform(
                get("/universidade")
        ).andExpect(
                status().isOk()
        ).andReturn().getResponse().getContentAsString();

        List<Universidade> universidades1=objectMapper.readValue(responseJson, ArrayList.class);
        assertEquals(3,universidades1.size());
    }
}