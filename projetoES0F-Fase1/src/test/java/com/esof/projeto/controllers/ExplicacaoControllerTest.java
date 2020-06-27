package com.esof.projeto.controllers;

import com.esof.projeto.models.Explicacao;
import com.esof.projeto.models.builders.ExplicacaoBuilder;
import com.esof.projeto.services.ExplicacaoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(controllers = ExplicacaoController.class)
class ExplicacaoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ExplicacaoService explicacaoService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void createExplicacao() throws Exception{
        Explicacao explicacao = new Explicacao();
        explicacao.setInicio(LocalDateTime.of(2020,1,12,12,30));

        String jsonRequest = this.objectMapper.writeValueAsString(explicacao);

        when(explicacaoService.createdExplicacao(explicacao)).thenReturn(Optional.of(explicacao));

        this.mockMvc.perform(
                post("/explicacao").contentType(MediaType.APPLICATION_JSON).content(jsonRequest)
        ).andExpect(
                status().isOk()
        );


        Explicacao existingExplicacao = new Explicacao();
        existingExplicacao.setInicio(LocalDateTime.of(2020,1,19,12,30));

        when(this.explicacaoService.createdExplicacao(explicacao)).thenReturn(Optional.empty());

        String existingCursoJson = this.objectMapper.writeValueAsString(existingExplicacao);

        this.mockMvc.perform(
                post("/explicacao").contentType(MediaType.APPLICATION_JSON).content(existingCursoJson)
        ).andExpect(
                status().isBadRequest()
        );
    }

    @Test
    void getAllExplicacao() throws Exception{
        Set<Explicacao> explicacoes=new HashSet<>();

        ExplicacaoBuilder explicacaoBuilder1 = new ExplicacaoBuilder().setInicio(LocalDateTime.of(12,12,12,12,12));
        ExplicacaoBuilder explicacaoBuilder2 = new ExplicacaoBuilder().setInicio(LocalDateTime.of(13,12,12,12,12));
        ExplicacaoBuilder explicacaoBuilder3 = new ExplicacaoBuilder().setInicio(LocalDateTime.of(14,12,12,12,12));


        explicacoes.add(explicacaoBuilder1.build());
        explicacoes.add(explicacaoBuilder2.build());
        explicacoes.add(explicacaoBuilder3.build());

        when(this.explicacaoService.findAll()).thenReturn(explicacoes);

        String responseJson=this.mockMvc.perform(
                get("/explicacao")
        ).andExpect(
                status().isOk()
        ).andReturn().getResponse().getContentAsString();

        List<Explicacao> explicacoes1=objectMapper.readValue(responseJson, ArrayList.class);
        assertEquals(3,explicacoes1.size());
    }

}