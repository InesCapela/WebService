package com.esof.projeto.models.builders;

import com.esof.projeto.models.Cadeira;
import com.esof.projeto.models.Curso;
import com.esof.projeto.models.Faculdade;

import java.util.HashSet;
import java.util.Set;

public class CursoBuilder {

    private Curso curso;
    private String name;
    private int ects;
    private int ano;
    private Faculdade faculdade;

    private Set<Cadeira> cadeiras = new HashSet<>();

    public CursoBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public CursoBuilder setCurso(Curso curso) {
        this.curso = curso;
        return this;
    }

    public CursoBuilder setEcts(int ects) {
        this.ects = ects;
        return this;
    }

    public CursoBuilder setAno(int ano) {
        this.ano = ano;
        return this;
    }

    public CursoBuilder setCadeiras(Set<Cadeira> cadeiras) {
        this.cadeiras = cadeiras;
        return this;
    }

    public CursoBuilder setFaculdade(Faculdade faculdade) {
        this.faculdade = faculdade;
        return this;
    }

    public CursoBuilder addCadeira(Cadeira cadeira) {
        this.cadeiras.add(cadeira);
        return this;
    }

    public CursoBuilder removeCadeira(Cadeira cadeira) {
        this.cadeiras.add(cadeira);
        return this;
    }

    public Curso build() {
        return new Curso(name, ects, ano, faculdade);
    }
}
