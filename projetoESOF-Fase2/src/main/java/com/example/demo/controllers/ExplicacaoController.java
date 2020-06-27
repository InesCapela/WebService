package com.example.demo.controllers;

import com.example.demo.models.Explicacao;
import com.example.demo.services.ExplicacaoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/explicacao")
public class ExplicacaoController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private ExplicacaoService explicacaoService;

    //POST /atendimento/{universidade}
    @PostMapping(value = "/{universidade}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Explicacao> createCurso(@PathVariable("universidade") String universidade, @RequestBody Explicacao explicacao) {
        Optional<Explicacao> explicacaoOptional = this.explicacaoService.createExplicacaoo(universidade,explicacao);
        if (explicacaoOptional.isPresent()) {
            return ResponseEntity.ok(explicacaoOptional.get());
        }
        throw new ExplicacaooAlreadyExistsException(explicacao);
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Explicacao already exists or Universidade does not exist")
    private class ExplicacaooAlreadyExistsException extends RuntimeException {
        public ExplicacaooAlreadyExistsException(Explicacao name) {
            super("A explicacao with name: " + name + " already exists or universidade does not exist");
        }
    }

}
