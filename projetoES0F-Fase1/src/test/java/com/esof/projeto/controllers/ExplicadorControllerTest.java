package com.esof.projeto.controllers;

import com.esof.projeto.models.Explicador;
import com.esof.projeto.models.builders.*;
import com.esof.projeto.services.ExplicadorServices;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = ExplicadorController.class)
class ExplicadorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ExplicadorServices explicadorServices;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void createExplicador() throws Exception {

        ExplicadorBuilder explicadorBuilder = new ExplicadorBuilder().setName("explicador1");

        String jsonRequest = this.objectMapper.writeValueAsString(explicadorBuilder.build());

        when(explicadorServices.createExplicador(explicadorBuilder.build())).thenReturn(Optional.of(explicadorBuilder.build()));

        this.mockMvc.perform(
                post("/explicador").contentType(MediaType.APPLICATION_JSON).content(jsonRequest)
        ).andExpect(
                status().isOk()
        );


        ExplicadorBuilder existingExplicadorBuilder = new ExplicadorBuilder().setName("explicador2");

        when(this.explicadorServices.createExplicador(existingExplicadorBuilder.build())).thenReturn(Optional.empty());

        String existingExplicadorJson = this.objectMapper.writeValueAsString(existingExplicadorBuilder.build());

        this.mockMvc.perform(
                post("/explicador").contentType(MediaType.APPLICATION_JSON).content(existingExplicadorJson)
        ).andExpect(
                status().isBadRequest()
        );
    }


    @Test
    void getAllExplicadores() throws Exception {

        ExplicadorBuilder explicadorBuilder=new ExplicadorBuilder().setName("expl1");
        ExplicadorBuilder explicadorBuilder1=new ExplicadorBuilder().setName("expl2");
        ExplicadorBuilder explicadorBuilder3=new ExplicadorBuilder().setName("expl3");

        Set<Explicador> explicadors=new HashSet<>();
        explicadors.add(explicadorBuilder.build());
        explicadors.add(explicadorBuilder1.build());
        explicadors.add(explicadorBuilder3.build());

        when(this.explicadorServices.findAll()).thenReturn(explicadors);

        String responseJson=this.mockMvc.perform(
                get("/explicador")
        ).andExpect(
                status().isOk()
        ).andReturn().getResponse().getContentAsString();

        List<Explicador> explicadors1=objectMapper.readValue(responseJson, ArrayList.class);
        assertEquals(3,explicadors1.size());
    }

    @Test
    void putExplicaCurso() {

        CadeiraBuilder cadeiraBuilder=new CadeiraBuilder().setCurso(new CursoBuilder().setName("curso").build());
        ExplicadorBuilder explicadorBuilder = new ExplicadorBuilder().setName("explicador").addCadeiras(cadeiraBuilder.build());

        when(this.explicadorServices.putExplicaCurso(cadeiraBuilder.build().getCurso().getName(),explicadorBuilder.build())).thenReturn(Optional.of(explicadorBuilder.build()));
        assertEquals(1,explicadorBuilder.build().getCadeiras().size());
    }

    @Test
    void putExplicadorDisponibilidade() {
        DisponibilidadeBuilder disponibilidadeBuilder= new DisponibilidadeBuilder()
                .setDayOfWeek(DayOfWeek.FRIDAY)
                .setInicio(LocalTime.of(12,12,12))
                .setFim(LocalTime.of(13,12,12));

        ExplicadorBuilder explicadorBuilder = new ExplicadorBuilder().setName("explicador").addDisponibilidade(disponibilidadeBuilder.build());

        when(this.explicadorServices.putExplicadorDisponibilidade(disponibilidadeBuilder.build(),explicadorBuilder.build())).thenReturn(Optional.of(explicadorBuilder.build()));
        assertEquals(1,explicadorBuilder.build().getDisponibilidades().size());
    }
}