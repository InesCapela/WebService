package com.esof.projeto.models.builders;

import com.esof.projeto.models.Aluno;
import com.esof.projeto.models.Explicador;
import org.junit.jupiter.api.Test;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

class AlunoBuilderTest {

    @Test
    void testBuilder() {

        CadeiraBuilder cadeiraBuilder=new CadeiraBuilder().setName("cad1");
        CadeiraBuilder cadeiraBuilder1=new CadeiraBuilder().setName("cad2");

        ExplicacaoBuilder explicacaoBuilder= new ExplicacaoBuilder().setCadeira(cadeiraBuilder.build());

        AlunoBuilder alunoBuilder = new AlunoBuilder();
        alunoBuilder.setAluno(new Aluno())
                .setName("nome")
                .setNumero(1L)
                .setPassword("12345")
                .setExplicacacoes(new HashSet<>())
                .addExplicacao(explicacaoBuilder.build())
                .removeExplicacao(explicacaoBuilder.build());

        Aluno aluno=alunoBuilder.build();

        assertEquals(0,aluno.getExplicacacoes().size());

        assertNotNull(aluno.getName());
        assertNotNull(aluno.getPassword());
        assertNotNull(aluno.getNumero());
        assertNotNull(aluno.getExplicacacoes());
    }

}