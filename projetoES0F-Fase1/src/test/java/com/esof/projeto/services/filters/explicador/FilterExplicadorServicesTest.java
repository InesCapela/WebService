package com.esof.projeto.services.filters.explicador;

import com.esof.projeto.models.Explicador;
import com.esof.projeto.models.builders.CadeiraBuilder;
import com.esof.projeto.models.builders.CursoBuilder;
import com.esof.projeto.models.builders.DisponibilidadeBuilder;
import com.esof.projeto.models.builders.ExplicadorBuilder;
import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class FilterExplicadorServicesTest {

    @Test
    void filter() {

        ExplicadorBuilder explicadorBuilder= new ExplicadorBuilder().setName("explicador");
        Set<Explicador> explicadores = new HashSet<>();
        explicadores.add(explicadorBuilder.build());


        CadeiraBuilder cadeiraBuilder1= new CadeiraBuilder().setName("cadeira1");
        CadeiraBuilder cadeiraBuilder2= new CadeiraBuilder().setName("cadeira2");
        CadeiraBuilder cadeiraBuilder3= new CadeiraBuilder().setName("cadeira3");
        CadeiraBuilder cadeiraBuilder4= new CadeiraBuilder().setName("cadeira4");

        CursoBuilder cursoBuilder1 = new CursoBuilder().setName("curso1").addCadeira(cadeiraBuilder1.build());
        CursoBuilder cursoBuilder2 = new CursoBuilder().setName("curso2").addCadeira(cadeiraBuilder2.build());
        CursoBuilder cursoBuilder3 = new CursoBuilder().setName("curso3").addCadeira(cadeiraBuilder3.build());
        CursoBuilder cursoBuilder4 = new CursoBuilder().setName("curso4").addCadeira(cadeiraBuilder4.build());

        explicadorBuilder.addCadeiras(cadeiraBuilder1.build());

        DisponibilidadeBuilder disponibilidadeBuilder1= new DisponibilidadeBuilder().setDayOfWeek(DayOfWeek.FRIDAY).setInicio(LocalTime.of(12,0));
        DisponibilidadeBuilder disponibilidadeBuilder2= new DisponibilidadeBuilder().setDayOfWeek(DayOfWeek.MONDAY).setInicio(LocalTime.of(13,0));

        explicadorBuilder.addDisponibilidade(disponibilidadeBuilder1.build());

        FilterExplicadorObject filterExplicadorObject = new FilterExplicadorObject(explicadorBuilder.build().getName(),cadeiraBuilder1.build().getName(),disponibilidadeBuilder1.build().getDiaDaSemana(),disponibilidadeBuilder1.build().getInicio());
        FilterExplicadorServices filterExplicadorServices= new FilterExplicadorServices();
        assertEquals(1,filterExplicadorServices.filter(explicadores,filterExplicadorObject).size());
    }
}