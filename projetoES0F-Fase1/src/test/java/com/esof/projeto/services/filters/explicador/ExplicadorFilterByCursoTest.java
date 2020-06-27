package com.esof.projeto.services.filters.explicador;

import com.esof.projeto.models.Cadeira;
import com.esof.projeto.models.Curso;
import com.esof.projeto.models.Explicador;
import com.esof.projeto.models.builders.CadeiraBuilder;
import com.esof.projeto.models.builders.CursoBuilder;
import com.esof.projeto.models.builders.ExplicadorBuilder;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class ExplicadorFilterByCursoTest {

    @Test
    void filter() {

        Set<Explicador> explicadors=new HashSet<>();

        CursoBuilder cursoBuilder1=new CursoBuilder().setName("curso1");
        CursoBuilder cursoBuilder2=new CursoBuilder().setName("curso2");

        CadeiraBuilder cadeiraBuilder1= new CadeiraBuilder().setName("cadeira1").setCurso(cursoBuilder1.build());
        CadeiraBuilder cadeiraBuilder2= new CadeiraBuilder().setName("cadeira2").setCurso(cursoBuilder2.build());

        Set<Cadeira> cadeirase1=new HashSet<>();
        cadeirase1.add(cadeiraBuilder1.build());

        Set<Cadeira> cadeirase2 =new HashSet<>();
        cadeirase2.add(cadeiraBuilder2.build());

        ExplicadorBuilder explicadorBuilder1 =new ExplicadorBuilder().setName("explicador1");
        ExplicadorBuilder explicadorBuilder2 =new ExplicadorBuilder().setName("explicador2");

        explicadorBuilder1.setCadeiras(cadeirase1);
        explicadorBuilder2.setCadeiras(cadeirase2);

        explicadors.add(explicadorBuilder1.build());
        explicadors.add(explicadorBuilder2.build());

        ExplicadorFilterByCurso explicadorFilterByCurso= new ExplicadorFilterByCurso(cadeiraBuilder1.build().getCurso().getName());
        assertEquals(1,explicadorFilterByCurso.filter(explicadors).size());

        explicadorFilterByCurso= new ExplicadorFilterByCurso(null);
        assertEquals(2,explicadorFilterByCurso.filter(explicadors).size());
    }
}