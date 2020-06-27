package com.esof.projeto.models.builders;

import com.esof.projeto.models.*;

import java.util.HashSet;
import java.util.Set;

public class ExplicadorBuilder {

    private String name;
    private Explicador explicador;
    private Set<Idioma> idiomas = new HashSet<>();
    private Set<Disponibilidade> disponibilidades = new HashSet<>();
    private Set<Explicacao> explicacoes = new HashSet<>();
    private Set<Cadeira> cadeiras = new HashSet<>();

    public ExplicadorBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public ExplicadorBuilder setIdiomas(Set<Idioma> idiomas) {
        this.idiomas = idiomas;
        return this;
    }

    public ExplicadorBuilder setDisponibilidades(Set<Disponibilidade> disponibilidades) {
        this.disponibilidades = disponibilidades;
        return this;
    }

    public ExplicadorBuilder setExplicacoes(Set<Explicacao> explicacoes) {
        this.explicacoes = explicacoes;
        return this;
    }

    public ExplicadorBuilder setCadeiras(Set<Cadeira> cadeiras) {
        this.cadeiras = cadeiras;
        return this;
    }

    public ExplicadorBuilder setExplicador(Explicador explicador) {
        this.explicador = explicador;
        return this;
    }

    public ExplicadorBuilder addDisponibilidade(Disponibilidade disponibilidade) {
        this.disponibilidades.add(disponibilidade);
        return this;
    }

    public ExplicadorBuilder removeDisponibilidade(Disponibilidade disponibilidade) {
        this.disponibilidades.remove(disponibilidade);
        return this;
    }

    public ExplicadorBuilder addExplicacoes(Explicacao explicacao) {
        this.explicacoes.add(explicacao);
        return this;
    }

    public ExplicadorBuilder removeEXplicacoes(Explicacao explicacao) {
        this.explicacoes.remove(explicacao);
        return this;
    }

    public ExplicadorBuilder addCadeiras(Cadeira cadeira) {
        this.cadeiras.add(cadeira);
        return this;
    }

    public ExplicadorBuilder addIdiomas(Idioma idioma) {
        this.idiomas.add(idioma);
        return this;
    }

    public ExplicadorBuilder removeCadeiras(Cadeira cadeira) {
        this.cadeiras.remove(cadeira);
        return this;
    }

    public Explicador build() {
        return new Explicador(idiomas, disponibilidades, explicacoes, cadeiras, name);
    }
}
