package com.esof.projeto.models.builders;

import com.esof.projeto.models.Curso;
import com.esof.projeto.models.Faculdade;
import com.esof.projeto.models.Universidade;

import java.util.HashSet;
import java.util.Set;

public class FaculdadeBuilder {

    private Faculdade faculdade;

    public String name;
    private Set<Curso> cursos = new HashSet<>();

    private Universidade universidade;

    public FaculdadeBuilder setFaculdade(Faculdade faculdade) {
        this.faculdade = faculdade;
        return this;
    }

    public FaculdadeBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public FaculdadeBuilder setCursos(Set<Curso> cursos) {
        this.cursos = cursos;
        return this;
    }

    public FaculdadeBuilder setUniversidade(Universidade universidade) {
        this.universidade = universidade;
        return this;
    }

    public FaculdadeBuilder addCurso(Curso curso) {
        this.cursos.add(curso);
        return this;
    }

    public FaculdadeBuilder removeCurso(Curso curso) {
        this.cursos.remove(curso);
        return this;
    }

    public Faculdade build() {
        return new Faculdade(name);
    }
}
