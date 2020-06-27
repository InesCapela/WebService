package com.esof.projeto.services.filters.explicador;

import com.esof.projeto.models.Disponibilidade;
import com.esof.projeto.models.Explicador;
import com.esof.projeto.models.builders.DisponibilidadeBuilder;
import com.esof.projeto.models.builders.ExplicadorBuilder;
import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class ExplicadorFilterByDayTest {

    @Test
    void filter() {
        Set<Explicador> explicadors=new HashSet<>();

        DisponibilidadeBuilder disponibilidadeBuilder= new DisponibilidadeBuilder().setDayOfWeek(DayOfWeek.MONDAY);
        DisponibilidadeBuilder disponibilidadeBuilder1= new DisponibilidadeBuilder().setDayOfWeek(DayOfWeek.FRIDAY);

        Set<Disponibilidade> disponibilidades1=new HashSet<>();
        disponibilidades1.add(disponibilidadeBuilder.build());

        Set<Disponibilidade> disponibilidades2=new HashSet<>();
        disponibilidades1.add(disponibilidadeBuilder1.build());


        ExplicadorBuilder explicadorBuilder1 =new ExplicadorBuilder().setName("explicador1");
        ExplicadorBuilder explicadorBuilder2 =new ExplicadorBuilder().setName("explicador2");

        explicadorBuilder1.setDisponibilidades(disponibilidades1);
        explicadorBuilder2.setDisponibilidades(disponibilidades2);

        explicadors.add(explicadorBuilder1.build());
        explicadors.add(explicadorBuilder2.build());


        ExplicadorFilterByDay explicadorFilterByDay= new ExplicadorFilterByDay(disponibilidadeBuilder.build().getDiaDaSemana());
        assertEquals(1,explicadorFilterByDay.filter(explicadors).size());

        explicadorFilterByDay= new ExplicadorFilterByDay(null);
        assertEquals(2,explicadorFilterByDay.filter(explicadors).size());

    }
}