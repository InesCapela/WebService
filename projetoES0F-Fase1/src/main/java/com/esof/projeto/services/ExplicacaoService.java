package com.esof.projeto.services;

import com.esof.projeto.models.*;
import com.esof.projeto.repositories.AlunoRepo;
import com.esof.projeto.repositories.CadeiraRepo;
import com.esof.projeto.repositories.ExplicadorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.esof.projeto.repositories.ExplicacaoRepo;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class ExplicacaoService {

    private ExplicacaoRepo explicacaoRepo;
    private AlunoService alunoService;
    private ExplicadorServices explicadorService;
    private CadeiraService cadeiraService;

    @Autowired
    public ExplicacaoService(ExplicadorServices explicadorService,ExplicacaoRepo explicacaoRepo,AlunoService alunoService,CadeiraService cadeiraService) {
        this.explicacaoRepo = explicacaoRepo;
        this.alunoService = alunoService;
        this.explicadorService = explicadorService;
        this.cadeiraService = cadeiraService;
    }

    public Optional<Explicacao> createdExplicacao(Explicacao explicacao) {
        if(explicacao.getAluno()!=null && explicacao.getExplicador()!=null && explicacao.getCadeira()!=null && explicacao.getInicio()!=null && explicacao.getAluno().getId()!=null && explicacao.getExplicador().getId()!=null && explicacao.getCadeira().getId()!=null){
            Optional<Aluno> alunoOptional = this.alunoService.findById(explicacao.getAluno().getId());
            Optional<Explicador> explicadorOptional = this.explicadorService.findById(explicacao.getExplicador().getId());
            Optional<Cadeira> cadeiraOptional = this.cadeiraService.findById(explicacao.getCadeira().getId());
            if (alunoOptional.isPresent() && explicadorOptional.isPresent() && cadeiraOptional.isPresent()) {
                Aluno a = alunoOptional.get();
                Explicador e = explicadorOptional.get();
                Cadeira c = cadeiraOptional.get();
                if(e.ExplicacaoExist(explicacao) && !e.DisponibilidadeInterference(explicacao)){
                    explicacao.insertExplicacao(a, e, c);
                    Explicacao created = this.explicacaoRepo.save(explicacao);
                    return Optional.of(created);
                }
            }
        }
        return Optional.empty();
    }

    public Set<Explicacao> findAll() {
        Set<Explicacao> explicacaos = new HashSet<>();
        for (Explicacao explicacao : this.explicacaoRepo.findAll()) {
            explicacaos.add(explicacao);
        }
        return explicacaos;
    }

    public Optional<Explicacao> findById(Long id) {
        return this.explicacaoRepo.findById(id);
    }

}
