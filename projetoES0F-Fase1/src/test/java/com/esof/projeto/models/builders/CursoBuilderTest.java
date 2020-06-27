package com.esof.projeto.models.builders;

import com.esof.projeto.models.Cadeira;
import com.esof.projeto.models.Curso;
import com.esof.projeto.models.Faculdade;
import org.junit.jupiter.api.Test;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

class CursoBuilderTest {
    @Test
    void testBuilder() {

        CadeiraBuilder cadeiraBuilder= new CadeiraBuilder().setName("cad1");
        CadeiraBuilder cadeiraBuilder1= new CadeiraBuilder().setName("cad2");

        CursoBuilder cursoBuilder = new CursoBuilder();

        cursoBuilder.setCurso(new Curso())
                .setName("nome")
                .setAno(1)
                .setEcts(4)
                .setCurso(new Curso())
                .setCadeiras(new HashSet<>())
                .setFaculdade(new Faculdade());

        Curso curso = cursoBuilder.build();

        curso.addCadeira(cadeiraBuilder.build());
        curso.addCadeira(cadeiraBuilder1.build());

        curso.removeCadeira(cadeiraBuilder.build());

        assertEquals(1,curso.getCadeiras().size());

        assertNotNull(curso.getName());
        assertNotNull(curso.getCadeiras());
        assertNotNull(curso.getFaculdade());
    }
}