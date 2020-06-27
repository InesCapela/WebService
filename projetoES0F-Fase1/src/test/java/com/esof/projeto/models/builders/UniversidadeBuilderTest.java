package com.esof.projeto.models.builders;

import com.esof.projeto.models.Faculdade;
import com.esof.projeto.models.Universidade;
import org.junit.jupiter.api.Test;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

class UniversidadeBuilderTest {

    @Test
    void testBuilder() {

        FaculdadeBuilder  faculdadeBuilder= new FaculdadeBuilder();
        UniversidadeBuilder universidadeBuilder= new UniversidadeBuilder();
        universidadeBuilder.setUniversidade(new Universidade())
                .setName("uni")
                .setFaculdades(new HashSet<>())
                .addFacs(faculdadeBuilder.build())
                .removeFacs(faculdadeBuilder.build());

        Universidade universidade= universidadeBuilder.build();

        assertEquals(0,universidade.getFaculdades().size());

        assertNotNull(universidade.getName());
        assertNotNull(universidade.getFaculdades());
    }

}