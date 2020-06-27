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
@JsonIdentityInfo(property = "id", generator = ObjectIdGenerators.PropertyGenerator.class,scope = Aluno.class)
public class Aluno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String password;
    private String name;
    private Long numero;


    @OneToMany(mappedBy = "aluno", cascade = CascadeType.PERSIST)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<Explicacao> explicacacoes = new HashSet<>();

    public Aluno(String name, Long numero, String password) {
        this.name = name;
        this.numero = numero;
        this.password = password;
    }

    public void addExplicacao(Explicacao explicacao) {
        this.explicacacoes.add(explicacao);
        explicacao.setAluno(this);
    }

    public void desmarcarExplicacao(Explicacao explicacao) {
        this.explicacacoes.remove(explicacao);
        explicacao.setAluno(this);
    }
}
