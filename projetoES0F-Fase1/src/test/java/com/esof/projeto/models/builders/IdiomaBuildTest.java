package com.esof.projeto.models.builders;

import com.esof.projeto.models.Idioma;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IdiomaBuildTest {

    @Test
    void testBuilder() {

        ExplicadorBuilder  explicadorBuilder= new ExplicadorBuilder();

        IdiomaBuild idiomaBuild= new IdiomaBuild();
        idiomaBuild.setIdioma1(new Idioma())
                .setIdioma("idioma")
                .addExplicadores(explicadorBuilder.build())
                .removeExplicadores(explicadorBuilder.build());

        Idioma idioma= idiomaBuild.build();

        assertEquals(0,idioma.getExplicadores().size());

        assertNotNull(idioma.getExplicadores());
        assertNotNull(idioma.getIdioma());
    }

}