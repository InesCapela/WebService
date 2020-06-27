package com.esof.projeto.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Data
@Entity
@NoArgsConstructor
@JsonIdentityInfo(property = "id", generator = ObjectIdGenerators.PropertyGenerator.class,scope = Curso.class)
public class Cadeira {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private int ects;
    private int semestre;
    private int ano;

    @ManyToMany(mappedBy = "cadeiras")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<Explicador> explicadors = new HashSet<>();

    @ManyToOne
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Curso curso;

    public Cadeira(String name, int ects, int semestre, int ano, Curso curso) {
        this.name = name;
        this.ects = ects;
        this.semestre = semestre;
        this.ano = ano;
        this.curso = curso;
    }

    public void removeExplicador(Explicador explicador) {
        this.explicadors.remove(explicador);
    }
}
