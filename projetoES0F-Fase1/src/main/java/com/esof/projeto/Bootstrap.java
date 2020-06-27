package com.esof.projeto;

import com.esof.projeto.models.*;
import com.esof.projeto.models.builders.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import com.esof.projeto.repositories.*;

import javax.transaction.Transactional;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Component
@Transactional
public class Bootstrap implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private AlunoRepo alunoRepo;

    @Autowired
    private CadeiraRepo cadeiraRepo;

    @Autowired
    private CursoRepo cursoRepo;

    @Autowired
    private FaculdadeRepo faculdadeRepo;

    @Autowired
    private ExplicadorRepo explicadorRepo;

    @Autowired
    private ExplicacaoRepo explicacaoRepo;

    @Autowired
    private UniversidadeRepo universidadeRepo;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        logger.info("Startup");

        UniversidadeBuilder universidadeBuilder = new UniversidadeBuilder().setName("uni1");

        AlunoBuilder alunoBuilder1 = new AlunoBuilder().setName("Rui").setNumero(1L).setPassword("asd");
        AlunoBuilder alunoBuilder = new AlunoBuilder().setName("Ines").setNumero(2L).setPassword("asd");

        CursoBuilder cursoBuilder = new CursoBuilder().setName("curso").setAno(3).setEcts(7);

        CadeiraBuilder cadeiraBuilder1 = new CadeiraBuilder().setName("cadeira1").setAno(2).setEcts(1);
        CadeiraBuilder cadeiraBuilder2 = new CadeiraBuilder().setName("cadeira2").setAno(3).setEcts(7);

        FaculdadeBuilder faculdadeBuilder = new FaculdadeBuilder().setName("fac");

        Disponibilidade disponibilidade = new Disponibilidade(LocalTime.of(16, 0, 0), LocalTime.of(20, 0, 0), DayOfWeek.FRIDAY, null);


        IdiomaBuild idiomaBuild = new IdiomaBuild().setIdioma("Português");

        ExplicadorBuilder explicadorBuilder = new ExplicadorBuilder().setName("Alessandro");
        explicadorBuilder.addIdiomas(idiomaBuild.build());

        cursoBuilder.addCadeira(cadeiraBuilder1.build());
        cursoBuilder.addCadeira(cadeiraBuilder2.build());

        explicadorBuilder.addCadeiras(cadeiraBuilder1.build());

        explicadorBuilder.addDisponibilidade(disponibilidade);
        explicadorBuilder.addIdiomas(idiomaBuild.build());

        universidadeBuilder.addFacs(faculdadeBuilder.build());

        this.alunoRepo.save(alunoBuilder.build());
        this.alunoRepo.save(alunoBuilder1.build());
        this.cadeiraRepo.save(cadeiraBuilder1.build());
        this.cadeiraRepo.save(cadeiraBuilder2.build());
        this.cursoRepo.save(cursoBuilder.build());
        this.faculdadeRepo.save(faculdadeBuilder.build());
        this.explicadorRepo.save(explicadorBuilder.build());
        this.universidadeRepo.save(universidadeBuilder.build());
    }
}
