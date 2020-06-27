package com.esof.projeto.controllers;

import com.esof.projeto.models.Cadeira;
import com.esof.projeto.models.builders.CadeiraBuilder;
import com.esof.projeto.models.builders.CursoBuilder;
import com.esof.projeto.services.CadeiraService;
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

@WebMvcTest(controllers = CadeiraController.class)
class CadeiraControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CadeiraService cadeiraService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void createdCadeira() throws Exception {
        CadeiraBuilder cadeiraBuilder=new CadeiraBuilder().setName("cadeira");

        String jsonRequest = this.objectMapper.writeValueAsString(cadeiraBuilder.build());

        when(cadeiraService.createdCadeira(cadeiraBuilder.build())).thenReturn(Optional.of(cadeiraBuilder.build()));

        this.mockMvc.perform(
                post("/cadeira").contentType(MediaType.APPLICATION_JSON).content(jsonRequest)
        ).andExpect(
                status().isOk()
        );

        CadeiraBuilder existingCadeira=new CadeiraBuilder().setName("cadeira2");

        when(this.cadeiraService.createdCadeira(cadeiraBuilder.build())).thenReturn(Optional.empty());

        String existingCadeiraJson = this.objectMapper.writeValueAsString(existingCadeira.build());

        this.mockMvc.perform(
                post("/cadeira").contentType(MediaType.APPLICATION_JSON).content(existingCadeiraJson)
        ).andExpect(
                status().isBadRequest()
        );
    }

    @Test
    void getAllCadeira() throws Exception{

        Set<Cadeira> cadeiras=new HashSet<>();
        CadeiraBuilder cadeiraBuilder1= new CadeiraBuilder().setName("cadeira1");
        CadeiraBuilder cadeiraBuilder2= new CadeiraBuilder().setName("cadeira2");
        CadeiraBuilder cadeiraBuilder3= new CadeiraBuilder().setName("cadeira3");

        cadeiras.add(cadeiraBuilder1.build());
        cadeiras.add(cadeiraBuilder2.build());
        cadeiras.add(cadeiraBuilder3.build());

        when(this.cadeiraService.findAll()).thenReturn(cadeiras);

        String responseJson=this.mockMvc.perform(
                get("/cadeira")
        ).andExpect(
                status().isOk()
        ).andReturn().getResponse().getContentAsString();

        List<Cadeira> cadeiras1=objectMapper.readValue(responseJson, ArrayList.class);
        assertEquals(3,cadeiras1.size());
    }

    @Test
    void createCadeira() throws Exception{

        CursoBuilder cursoBuilder1= new CursoBuilder().setName("curso1");
        CursoBuilder cursoBuilder2= new CursoBuilder().setName("curso2");

        CadeiraBuilder cadeiraBuilder = new CadeiraBuilder().setName("cadeira");

        String jsonRequest = this.objectMapper.writeValueAsString(cadeiraBuilder.build());

        when(cadeiraService.createCadeira(cadeiraBuilder.build(),cursoBuilder1.build().getName())).thenReturn(Optional.of(cadeiraBuilder.build()));

        this.mockMvc.perform(
                post("/cadeira/curso1").contentType(MediaType.APPLICATION_JSON).content(jsonRequest)
        ).andExpect(
                status().isOk()
        );


        CadeiraBuilder existingCadeiraBuilder = new CadeiraBuilder().setName("cadeira").setCurso(cursoBuilder2.build());

        when(this.cadeiraService.createCadeira(cadeiraBuilder.build(),cursoBuilder2.build().getName())).thenReturn(Optional.empty());

        String existingCadeiraJson = this.objectMapper.writeValueAsString(existingCadeiraBuilder.build());

        this.mockMvc.perform(
                post("/cadeira/curso2").contentType(MediaType.APPLICATION_JSON).content(existingCadeiraJson)
        ).andExpect(
                status().isBadRequest()
        );
    }
}