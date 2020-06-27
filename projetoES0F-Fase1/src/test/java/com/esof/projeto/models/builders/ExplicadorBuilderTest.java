package com.esof.projeto.models.builders;

import com.esof.projeto.models.Explicador;
import com.esof.projeto.models.Idioma;
import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

class ExplicadorBuilderTest {

    @Test
    void testBuilder() {

        CadeiraBuilder cadeiraBuilder = new CadeiraBuilder().setName("cad1");

        DisponibilidadeBuilder disponibilidadeBuilder= new DisponibilidadeBuilder().setDayOfWeek(DayOfWeek.FRIDAY);

        ExplicacaoBuilder explicacaoBuilder = new ExplicacaoBuilder().setCadeira(cadeiraBuilder.build());

        ExplicadorBuilder explicadorBuilder= new ExplicadorBuilder();
        explicadorBuilder.setExplicador(new Explicador())
                .setName("expl1")
                .setDisponibilidades(new HashSet<>())
                .setExplicacoes(new HashSet<>())
                .setCadeiras(new HashSet<>())
                .setExplicador(explicadorBuilder.build())
                .setIdiomas(new HashSet<>())
                .addIdiomas(new Idioma())
                .addExplicacoes(explicacaoBuilder.build())
                .removeEXplicacoes(explicacaoBuilder.build())
                .addCadeiras(cadeiraBuilder.build())
                .removeCadeiras(cadeiraBuilder.build())
                .addDisponibilidade(disponibilidadeBuilder.build())
                .removeDisponibilidade(disponibilidadeBuilder.build());

        Explicador explicador = explicadorBuilder.build();

        assertEquals(0, explicador.getExplicacoes().size());
        assertEquals(0, explicador.getCadeiras().size());
        assertEquals(1, explicador.getIdiomas().size());
        assertEquals(0, explicador.getDisponibilidades().size());

        assertNotNull(explicador.getIdiomas());
        assertNotNull(explicador.getCadeiras());
        assertNotNull(explicador.getDisponibilidades());
        assertNotNull(explicador.getExplicacoes());
        assertNotNull(explicador.getName());

    }
}