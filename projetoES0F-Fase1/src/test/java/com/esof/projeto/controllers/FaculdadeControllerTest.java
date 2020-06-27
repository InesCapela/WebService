package com.esof.projeto.controllers;

import com.esof.projeto.models.Faculdade;
import com.esof.projeto.models.builders.FaculdadeBuilder;
import com.esof.projeto.services.FaculdadeService;
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


@WebMvcTest(controllers = FaculdadeController.class)
class FaculdadeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FaculdadeService faculdadeService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void createFaculdade() throws Exception {
        FaculdadeBuilder faculdadeBuilder= new  FaculdadeBuilder ().setName("faculdade1");

        String jsonRequest = this.objectMapper.writeValueAsString(faculdadeBuilder.build());

        when(faculdadeService.createFaculdade(faculdadeBuilder.build())).thenReturn(Optional.of(faculdadeBuilder.build()));

        this.mockMvc.perform(
                post("/faculdade").contentType(MediaType.APPLICATION_JSON).content(jsonRequest)
        ).andExpect(
                status().isOk()
        );


        Faculdade existingFaculdade = new Faculdade("faculdade2");

        when(this.faculdadeService.createFaculdade(faculdadeBuilder.build())).thenReturn(Optional.empty());

        String existingCursoJson = this.objectMapper.writeValueAsString(existingFaculdade);

        this.mockMvc.perform(
                post("/faculdade").contentType(MediaType.APPLICATION_JSON).content(existingCursoJson)
        ).andExpect(
                status().isBadRequest()
        );
    }

    @Test
    void getAllFac() throws Exception{

        Set<Faculdade> faculdades=new HashSet<>();
        FaculdadeBuilder faculdadeBuilder1= new FaculdadeBuilder().setName("fac1");
        FaculdadeBuilder faculdadeBuilder2= new FaculdadeBuilder().setName("fac2");
        FaculdadeBuilder faculdadeBuilder3= new FaculdadeBuilder().setName("fac3");

        faculdades.add(faculdadeBuilder1.build());
        faculdades.add(faculdadeBuilder2.build());
        faculdades.add(faculdadeBuilder3.build());

        when(this.faculdadeService.findAll()).thenReturn(faculdades);

        String responseJson=this.mockMvc.perform(
                get("/faculdade")
        ).andExpect(
                status().isOk()
        ).andReturn().getResponse().getContentAsString();

        List<Faculdade> faculdades1=objectMapper.readValue(responseJson, ArrayList.class);
        assertEquals(3,faculdades1.size());
    }

}