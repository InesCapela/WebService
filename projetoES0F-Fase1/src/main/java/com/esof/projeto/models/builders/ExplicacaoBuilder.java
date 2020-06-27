package com.esof.projeto.models.builders;

import com.esof.projeto.models.Aluno;
import com.esof.projeto.models.Cadeira;
import com.esof.projeto.models.Explicacao;
import com.esof.projeto.models.Explicador;

import java.time.LocalDateTime;

public class ExplicacaoBuilder {

    private LocalDateTime inicio;
    private Aluno aluno;
    private String name;
    private Explicador explicador;
    private Cadeira cadeira;


    public ExplicacaoBuilder setInicio(LocalDateTime inicio) {
        this.inicio = inicio;
        return this;
    }

    public ExplicacaoBuilder setAluno(Aluno aluno) {
        this.aluno = aluno;
        return this;
    }

    public ExplicacaoBuilder setExplicador(Explicador explicador) {
        this.explicador = explicador;
        return this;
    }

    public ExplicacaoBuilder setCadeira(Cadeira cadeira) {
        this.cadeira = cadeira;
        return this;
    }

    public Explicacao build() {
        return new Explicacao(inicio, aluno, explicador, cadeira, name);
    }
}
