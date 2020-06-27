package com.esof.projeto.models;

import com.esof.projeto.models.builders.CadeiraBuilder;
import com.esof.projeto.models.builders.ExplicadorBuilder;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

class CadeiraTest {

    @Test
    void removeExplicador(){

        CadeiraBuilder cadeiraBuilder=new CadeiraBuilder();

        ExplicadorBuilder explicadorBuilder1=new ExplicadorBuilder().setName("exp1");
        ExplicadorBuilder explicadorBuilder2=new ExplicadorBuilder().setName("exp2");

        Set<Explicador> explicadores= new HashSet<>();
        explicadores.add(explicadorBuilder1.build());
        explicadores.add(explicadorBuilder2.build());

        cadeiraBuilder.build().setExplicadors(explicadores);

        cadeiraBuilder.build().removeExplicador(explicadorBuilder1.build());

        if(!cadeiraBuilder.build().getExplicadors().contains(explicadorBuilder1.build())){
            System.out.println("Teste bem sucedido");
        }
    }
}