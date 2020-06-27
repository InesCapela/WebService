package com.esof.projeto.services.filters.explicador;

import com.esof.projeto.models.Disponibilidade;
import com.esof.projeto.models.Explicador;
import com.esof.projeto.models.builders.DisponibilidadeBuilder;
import com.esof.projeto.models.builders.ExplicadorBuilder;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class ExplicadorFilterByStartDataTest {

    @Test
    void filter() {
        Set<Explicador> explicadors=new HashSet<>();

        DisponibilidadeBuilder disponibilidadeBuilder= new DisponibilidadeBuilder().setInicio(LocalTime.of(12,12)).setFim(LocalTime.of(13,12));
        DisponibilidadeBuilder disponibilidadeBuilder1= new DisponibilidadeBuilder().setInicio(LocalTime.of(13,12)).setFim(LocalTime.of(14,12));

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


        ExplicadorFilterByStartData explicadorFilterByStartData= new ExplicadorFilterByStartData(disponibilidadeBuilder.build().getInicio());
        assertEquals(1,explicadorFilterByStartData.filter(explicadors).size());

        explicadorFilterByStartData= new ExplicadorFilterByStartData(null);
        assertEquals(2,explicadorFilterByStartData.filter(explicadors).size());
    }
}