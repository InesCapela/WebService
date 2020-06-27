package com.esof.projeto.models;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class UniversidadeTest {

    @Test
    void addFaculdade() {
        Universidade universidade= new Universidade();

        Faculdade faculdade= new Faculdade();

        universidade.addFaculdade(faculdade);

        if(universidade.getFaculdades().contains(faculdade)){
            System.out.println("Teste bem sucedido");
        }
    }

    @Test
    void removeFaculdade() {

        Universidade universidade= new Universidade();

        Faculdade faculdade1= new Faculdade();
        Faculdade faculdade2= new Faculdade();


        Set<Faculdade> faculdades= new HashSet<>();
        faculdades.add(faculdade1);
        faculdades.add(faculdade2);

        universidade.setFaculdades(faculdades);

        universidade.removeFaculdade(faculdade1);

        if(!universidade.getFaculdades().contains(faculdade1)){
            System.out.println("Teste bem sucedido");
        }
    }
}