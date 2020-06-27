package com.example.demo.services;

import com.example.demo.models.Explicacao;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class ExplicacaoService {

    private RestTemplate restTemplate=new RestTemplate();

    //POST /atendimento/{universidade}
    public Optional<Explicacao> createExplicacaoo(String universidade, Explicacao explicacao) {
        String universidadeURL=UniversidadeUrlService.getInstance().getUrl(universidade);
        if (universidade != null) {
            ResponseEntity<Explicacao> responseEntity=this.restTemplate.postForEntity(universidadeURL+"/explicacao",explicacao,Explicacao.class);
            return Optional.of(responseEntity.getBody());
        }
        return Optional.empty();
    }
}
