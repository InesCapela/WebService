package com.esof.projeto.models;

import com.esof.projeto.models.builders.*;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import static org.springframework.test.util.AssertionErrors.assertFalse;
import static org.springframework.test.util.AssertionErrors.assertTrue;

class ExplicadorTest {

    @Test
    void addCadeira() {

        CadeiraBuilder cadeiraBuilder= new CadeiraBuilder();
        ExplicadorBuilder explicador1 = new ExplicadorBuilder().addCadeiras(cadeiraBuilder.build());

        assertTrue("Teste bem sucedido",explicador1.build().getCadeiras().contains(cadeiraBuilder.build()));

    }


    @Test
    void addIdioma() {

        IdiomaBuild idiomaBuild= new IdiomaBuild();

        ExplicadorBuilder explicador1 = new ExplicadorBuilder().addIdiomas(idiomaBuild.build());

        assertTrue("Teste bem sucedido",explicador1.build().getIdiomas().contains(idiomaBuild.build()));

    }

    @Test
    void addDisponibilidade() {

        Disponibilidade disponibilidade= new Disponibilidade();

        ExplicadorBuilder explicador1 = new ExplicadorBuilder().addDisponibilidade(disponibilidade);

        assertTrue("Teste bem sucedido",explicador1.build().getDisponibilidades().contains(disponibilidade));

    }

    @Test
    void addExplicacao() {

        ExplicacaoBuilder explicacaoBuilder= new ExplicacaoBuilder();

        ExplicadorBuilder explicador1 = new ExplicadorBuilder().addExplicacoes(explicacaoBuilder.build());

        assertTrue("Teste bem sucedido",explicador1.build().getExplicacoes().contains(explicacaoBuilder.build()));
    }

    @Test
    void desmarcarExplicacao() {

        AlunoBuilder alunoBuilder=new AlunoBuilder();
        ExplicacaoBuilder explicacaoBuilder1= new ExplicacaoBuilder().setAluno(alunoBuilder.build());
        ExplicacaoBuilder explicacaoBuilder2= new ExplicacaoBuilder().setAluno(alunoBuilder.build());


        Set<Explicacao> explicacoes= new HashSet<>();
        explicacoes.add(explicacaoBuilder1.build());
        explicacoes.add(explicacaoBuilder2.build());

        ExplicadorBuilder explicadorBuilder = new ExplicadorBuilder().setExplicacoes(explicacoes);


        explicadorBuilder.removeEXplicacoes(explicacaoBuilder1.build());
        assertFalse("Teste bem sucedido",explicadorBuilder.build().getExplicacoes().contains(explicacaoBuilder1.build()));

    }

    @Test
    void verfiAgend() {

        ExplicacaoBuilder explicacaoBuilder1= new ExplicacaoBuilder().setInicio(LocalDateTime.of(12,12,12,12,12));
        ExplicacaoBuilder explicacaoBuilder2= new ExplicacaoBuilder().setInicio(LocalDateTime.of(19,12,12,12,12));

        ExplicadorBuilder explicadorBuilder= new ExplicadorBuilder().addExplicacoes(explicacaoBuilder2.build());

        assertFalse("Teste bem sucedido",explicadorBuilder.build().verfiAgend(explicacaoBuilder1.build()));
    }
}