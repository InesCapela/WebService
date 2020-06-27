package com.esof.projeto.models.builders;

import com.esof.projeto.models.Cadeira;
import com.esof.projeto.models.Curso;
import com.esof.projeto.models.Explicador;

import java.util.HashSet;
import java.util.Set;

public class CadeiraBuilder {

    private Cadeira cadeira;
    private String name;
    private int ects;
    private int semestre;
    private int ano;
    private Curso curso;


    private Set<Explicador> explicadors = new HashSet<>();

    public CadeiraBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public CadeiraBuilder setCadeira(Cadeira cadeira) {
        this.cadeira = cadeira;
        return this;
    }

    public CadeiraBuilder setEcts(int ects) {
        this.ects = ects;
        return this;
    }

    public CadeiraBuilder setSemestre(int semestre) {
        this.semestre = semestre;
        return this;
    }

    public CadeiraBuilder setAno(int ano) {
        this.ano = ano;
        return this;
    }

    public CadeiraBuilder setCurso(Curso curso) {
        this.curso = curso;
        return this;
    }

    public CadeiraBuilder addExplicadors(Explicador explicador) {
        this.explicadors.add(explicador);
        return this;
    }

    public CadeiraBuilder removeExplicadors(Explicador explicador) {
        this.explicadors.remove(explicador);
        return this;
    }

    public Cadeira build() {
        return new Cadeira(name, ects, semestre, ano, curso);
    }
}
