package com.esof.projeto.models.builders;

import com.esof.projeto.models.Aluno;
import com.esof.projeto.models.Explicacao;

import java.util.HashSet;
import java.util.Set;

public class AlunoBuilder {

    private Aluno aluno;
    private String password;
    private String name;
    private Long numero;

    private Set<Explicacao> explicacacoes = new HashSet<>();

    public AlunoBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public AlunoBuilder setAluno(Aluno aluno) {
        this.aluno = aluno;
        return this;
    }

    public AlunoBuilder setPassword(String password) {
        this.password = password;
        return this;
    }

    public AlunoBuilder setNumero(Long numero) {
        this.numero = numero;
        return this;
    }

    public AlunoBuilder setExplicacacoes(Set<Explicacao> explicacacoes) {
        this.explicacacoes = explicacacoes;
        return this;
    }

    public AlunoBuilder addExplicacao(Explicacao explicacao) {
        this.explicacacoes.add(explicacao);
        return this;
    }

    public AlunoBuilder removeExplicacao(Explicacao explicacao) {
        this.explicacacoes.remove(explicacao);
        return this;
    }

    public Aluno build() {
        return new Aluno(name, numero, password);
    }

}
