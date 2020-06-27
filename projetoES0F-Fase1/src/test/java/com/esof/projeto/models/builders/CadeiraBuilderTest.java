package com.esof.projeto.models.builders;

import com.esof.projeto.models.Cadeira;
import com.esof.projeto.models.Curso;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CadeiraBuilderTest {

    @Test
    void testBuilder() {

        ExplicadorBuilder explicadorBuilder=new ExplicadorBuilder();

        CadeiraBuilder cadeiraBuilder = new CadeiraBuilder();
        cadeiraBuilder.setCadeira(new Cadeira())
                .setName("nome")
                .setAno(1)
                .setEcts(4)
                .setCurso(new Curso())
                .setSemestre(1)
                .setCadeira(new Cadeira())
                .addExplicadors(explicadorBuilder.build())
                .removeExplicadors(explicadorBuilder.build());

        Cadeira cadeira=cadeiraBuilder.build();

        assertEquals(0,cadeira.getExplicadors().size());

        assertNotNull(cadeira.getName());
        assertNotNull(cadeira.getCurso());
        assertNotNull(cadeira.getExplicadors());
    }

}