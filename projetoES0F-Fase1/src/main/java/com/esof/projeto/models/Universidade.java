package com.esof.projeto.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.OneToMany;
import javax.persistence.CascadeType;
import javax.persistence.Id;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
public class Universidade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "universidade", cascade = CascadeType.PERSIST)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonIdentityInfo(property = "id", generator = ObjectIdGenerators.PropertyGenerator.class,scope = Faculdade.class)
    private Set<Faculdade> faculdades = new HashSet<>();

    public Universidade(String name, Set<Faculdade> faculdades) {
        this.name = name;
    }

    public void addFaculdade(Faculdade faculdade) {
        this.faculdades.add(faculdade);
    }

    public void removeFaculdade(Faculdade faculdade) {
        this.faculdades.remove(faculdade);
    }

}
