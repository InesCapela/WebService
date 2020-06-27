package com.esof.projeto.models.builders;

import com.esof.projeto.models.Explicador;
import com.esof.projeto.models.Idioma;

import java.util.HashSet;
import java.util.Set;

public class IdiomaBuild {

    private String idioma;
    private Idioma idioma1;

    private Set<Explicador> explicadores = new HashSet<>();

    public IdiomaBuild setIdioma(String idioma) {
        this.idioma = idioma;
        return this;
    }

    public IdiomaBuild setIdioma1(Idioma idioma1) {
        this.idioma1 = idioma1;
        return this;
    }

    public IdiomaBuild addExplicadores(Explicador explicador) {
        this.explicadores.add(explicador);
        return this;
    }

    public IdiomaBuild removeExplicadores(Explicador explicador) {
        this.explicadores.remove(explicador);
        return this;
    }

    public Idioma build() {
        return new Idioma(idioma);
    }
}
