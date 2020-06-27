package com.esof.projeto.models;

import com.esof.projeto.models.builders.CadeiraBuilder;
import com.esof.projeto.models.builders.CursoBuilder;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class CursoTest {

    @Test
    void addCadeira() {

        CursoBuilder cursoBuilder= new CursoBuilder();

        CadeiraBuilder cadeiraBuilder1= new CadeiraBuilder();

        cursoBuilder.addCadeira(cadeiraBuilder1.build()).build();

        if(cursoBuilder.build().getCadeiras().contains(cadeiraBuilder1.build())){
            System.out.println("Teste bem sucedido");
        }
    }

    @Test
    void removeCadeira() {

        CursoBuilder cursoBuilder= new CursoBuilder();

        CadeiraBuilder cadeiraBuilder1= new CadeiraBuilder();
        CadeiraBuilder cadeiraBuilder2= new CadeiraBuilder();

        Set<Cadeira> cadeiras= new HashSet<>();
        cadeiras.add(cadeiraBuilder1.build());
        cadeiras.add(cadeiraBuilder2.build());

        cursoBuilder.setCadeiras(cadeiras).build();

        cursoBuilder.removeCadeira(cadeiraBuilder1.build()).build();

        if(!cursoBuilder.build().getCadeiras().contains(cadeiraBuilder1.build())){
            System.out.println("Teste bem sucedido");
        }
    }
}