package com.esof.projeto.models.builders;

import com.esof.projeto.models.Disponibilidade;
import com.esof.projeto.models.Explicacao;
import com.esof.projeto.models.Explicador;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Set;

public class DisponibilidadeBuilder {

    private LocalTime inicio;
    private LocalTime fim;
    private DayOfWeek dayOfWeek;

    private Set<Explicador> explicadors;

    public DisponibilidadeBuilder setExplicadors(Set<Explicador> explicadors) {
        this.explicadors = explicadors;
        return this;
    }

    public DisponibilidadeBuilder setInicio(LocalTime inicio) {
        this.inicio = inicio;
        return this;
    }

    public DisponibilidadeBuilder setFim(LocalTime fim) {
        this.fim = fim;
        return this;
    }

    public DisponibilidadeBuilder setDayOfWeek(DayOfWeek dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
        return this;

    }

    public DisponibilidadeBuilder contains(Explicacao explicacao) {
        this.contains(explicacao);
        return this;
    }

    public DisponibilidadeBuilder contains(LocalTime start, LocalTime end) {
        this.contains(inicio, fim);
        return this;
    }

    public Disponibilidade build() {
        return new Disponibilidade(inicio, fim, dayOfWeek, explicadors);
    }
}
