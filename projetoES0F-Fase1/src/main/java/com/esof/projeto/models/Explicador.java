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
@JsonIdentityInfo(property = "id", generator = ObjectIdGenerators.PropertyGenerator.class, scope = Explicador.class)
public class Explicador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<Idioma> idiomas = new HashSet<>();

    @OneToMany(mappedBy = "explicador", cascade = CascadeType.PERSIST)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<Disponibilidade> disponibilidades = new HashSet<>();

    @OneToMany(mappedBy = "explicador", cascade = CascadeType.PERSIST)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<Explicacao> explicacoes = new HashSet<>();

    @ManyToMany(cascade = CascadeType.PERSIST)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<Cadeira> cadeiras = new HashSet<>();

    public Explicador(Set<Idioma> idiomas, Set<Disponibilidade> disponibilidades, Set<Explicacao> explicacoes, Set<Cadeira> cadeiras, String name) {
        for(Disponibilidade disponibilidade:disponibilidades){
            this.addDisponibilidade(disponibilidade);
        }
        this.idiomas = idiomas;
//        this.disponibilidades = disponibilidades;
        for(Explicacao explicacao:explicacoes){
            this.addExplicacao(explicacao);
        }
//        this.explicacoes = explicacoes;
        this.cadeiras = cadeiras;
        this.name = name;
    }

    public void addCadeira(Cadeira cadeira) {
        this.cadeiras.add(cadeira);
        cadeira.getExplicadors().add(this);
    }

    public void addIdioma(Idioma idioma) {
        this.idiomas.add(idioma);
        idioma.addExplicador(this);
    }

    public void addDisponibilidade(Disponibilidade disponibilidade) {
        this.disponibilidades.add(disponibilidade);
        disponibilidade.setExplicador(this);
    }

    public void addExplicacao(Explicacao explicacao) {
        this.explicacoes.add(explicacao);
        explicacao.setExplicador(this);
        Aluno aluno = explicacao.getAluno();
        if (aluno != null && !aluno.getExplicacacoes().contains(explicacao)) {
            aluno.addExplicacao(explicacao);
        }
    }


    public void desmarcarExplicacao(Explicacao explicacao) {

        explicacao.getAluno().desmarcarExplicacao(explicacao);
        this.explicacoes.remove(explicacao);
    }

    /**
     * verificar se pode agendar
     *
     * @param explicacao explicacap
     * @return boolean
     */
    public boolean verfiAgend(Explicacao explicacao) {
        for (Disponibilidade agend : this.disponibilidades) {
            if (agend.contains(explicacao)) {
                return true;
            }
        }
        return false;
    }

    public boolean ExplicacaoExist(Explicacao explicacao){
        for(Explicacao auxexplicacao: this.getExplicacoes()) {
            if (((auxexplicacao.getInicio().equals(explicacao.getInicio()) || auxexplicacao.getInicio().isAfter(explicacao.getInicio())) && auxexplicacao.getInicio().isBefore(explicacao.getInicio().plusHours(1))) || ((auxexplicacao.getInicio().plusHours(1).equals(explicacao.getInicio().plusHours(1)) || auxexplicacao.getInicio().plusHours(1).isBefore(explicacao.getInicio().plusHours(1))) && auxexplicacao.getInicio().plusHours(1).isAfter(explicacao.getInicio()))) {
                return false;
            }
        }
        return true;
    }

    public boolean DisponibilidadeInterference(Explicacao explicacao){
        for (Disponibilidade auxdisponibilidade:this.getDisponibilidades()) {
            if(auxdisponibilidade.getDiaDaSemana().equals(explicacao.getInicio().getDayOfWeek()) && (auxdisponibilidade.getInicio().equals(explicacao.getInicio().toLocalTime()) || auxdisponibilidade.getInicio().isBefore(explicacao.getInicio().toLocalTime())) && (auxdisponibilidade.getFim().equals(explicacao.getInicio().plusHours(1).toLocalTime()) || auxdisponibilidade.getFim().isAfter(explicacao.getInicio().plusHours(1).toLocalTime()))){
                return false;
            }
        }
        return true;
    }

    public Set<Disponibilidade> getDisponibilidades() {
        return disponibilidades;
    }

    public void setDisponibilidade(Set<Disponibilidade> disponibilidades) {
        this.disponibilidades = disponibilidades;
    }
}
