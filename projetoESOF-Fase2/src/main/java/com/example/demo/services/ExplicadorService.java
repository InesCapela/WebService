package com.example.demo.services;

import com.example.demo.models.Curso;
import com.example.demo.models.Explicador;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class ExplicadorService {

    private RestTemplate restTemplate=new RestTemplate();

    // POST /explicador/{universidade}
    public Optional<Explicador> createExplicador(Explicador explicador, String universidade) {
        String explicadorURL = UniversidadeUrlService.getInstance().getExplicadorUrl(universidade); //ExplicadorURLService.getInstance().getUrl(universidade);
        if (universidade != null) {
            ResponseEntity<Explicador> responseEntity=this.restTemplate.postForEntity(explicadorURL+"/exolicador",explicador,Explicador.class);
            if(responseEntity.getBody()!=null){
                return Optional.of(responseEntity.getBody());
            }
        }
        return Optional.empty();
    }

    // PUT /explicador/{universidade}/{curso}
    public Optional<Explicador> updateExplicador(Long idCurso,Explicador explicador,String universidade) {
        String explicadorURL = UniversidadeUrlService.getInstance().getExplicadorUrl(universidade)+"/name/"+explicador.getName();
        if(idCurso!=null ){
            Curso curso=new Curso();
            curso.setId(idCurso);
            ResponseEntity<Explicador> responseEntity=this.restTemplate.postForEntity(explicadorURL,curso,Explicador.class);
            if(responseEntity.getBody()!=null){
                return Optional.of(responseEntity.getBody());
            }
        }
        return Optional.empty();
    }
}
