package com.esof.projeto.models.builders;

import com.esof.projeto.models.Faculdade;
import com.esof.projeto.models.Universidade;
import org.junit.jupiter.api.Test;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

class FaculdadeBuilderTest {

    @Test
    void testBuilder() {

        CursoBuilder cursoBuilder = new CursoBuilder();
        FaculdadeBuilder faculdadeBuilder = new FaculdadeBuilder();
        faculdadeBuilder.setFaculdade(new Faculdade())
                .setName("fac")
                .setCursos(new HashSet<>())
                .setUniversidade(new UniversidadeBuilder().build())
                .setCursos(new HashSet<>())
                .addCurso(cursoBuilder.build())
                .removeCurso(cursoBuilder.build());

        Faculdade faculdade = faculdadeBuilder.build();

        assertEquals(0, faculdade.getCursos().size());

        assertNotNull(faculdade.getCursos());
        assertNotNull(faculdade.getName());
    }
}