package com.esof.projeto.models.builders;

import com.esof.projeto.models.Disponibilidade;
import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

class DisponibilidadeBuilderTest {

    @Test
    void testBuilder() {

        ExplicacaoBuilder  explicacaoBuilder= new ExplicacaoBuilder().setInicio(LocalDateTime.now());

        DisponibilidadeBuilder disponibilidadeBuilder= new DisponibilidadeBuilder();
        disponibilidadeBuilder
                .setInicio(LocalTime.now())
                .setDayOfWeek(DayOfWeek.FRIDAY)
                .setFim(LocalTime.now().plusHours(1))
                .setExplicadors(new HashSet<>());

        Disponibilidade disponibilidade= disponibilidadeBuilder.build();

        assertNotNull(disponibilidade.getInicio());
        assertNotNull(disponibilidade.getFim());
        assertNotNull(disponibilidade.getDiaDaSemana());
    }

}