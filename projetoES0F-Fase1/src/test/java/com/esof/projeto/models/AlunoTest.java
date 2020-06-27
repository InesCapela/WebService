package com.esof.projeto.models;

import com.esof.projeto.models.builders.AlunoBuilder;
import com.esof.projeto.models.builders.ExplicacaoBuilder;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class AlunoTest {

    @Test
    void addExplicacao() {
        ExplicacaoBuilder explicacaoBuilder= new ExplicacaoBuilder();
        AlunoBuilder alunoBuilder = new AlunoBuilder().addExplicacao(explicacaoBuilder.build());

        assertNotNull(alunoBuilder.build().getExplicacacoes());
    }

    @Test
    void desmarcarExplicacao() {

        ExplicacaoBuilder explicacaoBuilder = new ExplicacaoBuilder();

        Aluno aluno = new AlunoBuilder().setName("aluno").addExplicacao(explicacaoBuilder.build()).build();

        aluno.desmarcarExplicacao(explicacaoBuilder.build());

        assertFalse(aluno.getExplicacacoes().contains(explicacaoBuilder.build()));

    }
}