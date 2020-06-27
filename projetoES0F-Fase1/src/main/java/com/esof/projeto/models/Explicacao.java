package com.esof.projeto.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
@JsonIdentityInfo(property = "id", generator = ObjectIdGenerators.PropertyGenerator.class,scope = Explicacao.class)
public class Explicacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private LocalDateTime inicio;

    @ManyToOne
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
     private Aluno aluno;

    @ManyToOne
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Explicador explicador;

    @OneToOne
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Cadeira cadeira;

    public Explicacao(LocalDateTime inicio, Aluno aluno, Explicador explicador, Cadeira cadeira, String name) {
        this.inicio = inicio;
        this.aluno = aluno;
        this.explicador = explicador;
        this.cadeira = cadeira;
        this.name = name;
    }

    public Explicacao(LocalDateTime inicio, Aluno aluno, Explicador explicador, Cadeira cadeira) {
        this.inicio = inicio;
        this.aluno = aluno;
        this.explicador = explicador;
        this.cadeira = cadeira;
    }

    /**
     * verifica sobreposicao
     *
     * @param explicacao explicacao
     * @return boolean
     */
    public boolean sobreposicao(Explicacao explicacao) {
        return this.isBetween(explicacao) || explicacao.isBetween(this) || (this.inicio.equals(explicacao.inicio) && this.inicio.plusHours(1).equals(explicacao.inicio.plusHours(1)));
    }

    /**
     * "entre"
     *
     * @param explicacao explicacao
     * @return boolean
     */
    private boolean isBetween(Explicacao explicacao) {
        LocalDateTime appointmentStartTime = explicacao.getInicio();
        LocalDateTime appointmentEndTime = explicacao.getInicio().plusHours(1);
        return this.isBetween(appointmentStartTime) || this.isBetween(appointmentEndTime);
    }

    /**
     * verificar hora
     *
     * @param timeToCheck tempo a verificar
     * @return boolean
     */
    private boolean isBetween(LocalDateTime timeToCheck) {
        return this.inicio.isBefore(timeToCheck) && this.inicio.plusHours(1).isAfter(timeToCheck);
    }

    public void insertExplicacao(Aluno aluno, Explicador explicador, Cadeira cadeira) {
        this.aluno=aluno;
        this.explicador = explicador;
        this.cadeira = cadeira;
        explicador.getExplicacoes().add(this);
        aluno.getExplicacacoes().add(this);
    }

}

