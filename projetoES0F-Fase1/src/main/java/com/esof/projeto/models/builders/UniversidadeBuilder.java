package com.esof.projeto.models.builders;

import com.esof.projeto.models.Faculdade;
import com.esof.projeto.models.Universidade;

import java.util.HashSet;
import java.util.Set;

public class UniversidadeBuilder {

    private Universidade universidade;
    private String name;
    private Set<Faculdade> faculdades = new HashSet<>();

    public UniversidadeBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public UniversidadeBuilder setUniversidade(Universidade universidade) {
        this.universidade = universidade;
        return this;
    }

    public UniversidadeBuilder setFaculdades(Set<Faculdade> faculdades) {
        this.faculdades = faculdades;
        return this;
    }

    public UniversidadeBuilder addFacs(Faculdade faculdade) {
        this.faculdades.add(faculdade);
        return this;
    }

    public UniversidadeBuilder removeFacs(Faculdade faculdade) {
        this.faculdades.remove(faculdade);
        return this;
    }

    public Universidade build() {
        return new Universidade(name, faculdades);
    }
}
