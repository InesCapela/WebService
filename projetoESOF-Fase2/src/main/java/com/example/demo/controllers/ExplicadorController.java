package com.example.demo.controllers;

import com.example.demo.models.Explicador;
import com.example.demo.services.ExplicadorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/explicador")
public class ExplicadorController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private ExplicadorService explicadorService;

    // POST /explicador/{universidade}
    @PostMapping(value = "/{universidade}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Explicador> postExplicadorUni(@PathVariable("universidade") Explicador explicador, @RequestBody String universidade){
        this.logger.info("Received a put request");
        Optional<Explicador> optionalExplicador = this.explicadorService.createExplicador(explicador,universidade);
        if (optionalExplicador.isPresent()) {
            return ResponseEntity.ok(optionalExplicador.get());
        }
        throw new NoExplicadoressExcpetion(explicador);
    }

    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No such explicador")
    private static class NoExplicadoressExcpetion extends RuntimeException {
        public NoExplicadoressExcpetion(Explicador explicador) {
            super("No such explicador with id: " + explicador.getId());
        }
    }

    // PUT/explicador/{universidade}/{curso}
    @PutMapping(value = "/{universidade}/{curso}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Explicador> putExplicaUniversidadeCurso(@PathVariable("universidade/curso") Long idCurso, @RequestBody Explicador explicador,String idUni) throws NoExplicadorssExcpetion {
        this.logger.info("Received a put request");
        Optional<Explicador> optionalExplicador = this.explicadorService.updateExplicador(idCurso, explicador,idUni);
        if (optionalExplicador.isPresent()) {
            return ResponseEntity.ok(optionalExplicador.get());
        }
        throw new NoExplicadorssExcpetion(explicador.getId());
    }
    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No such explicador")
    private static class NoExplicadorssExcpetion extends RuntimeException {
        public NoExplicadorssExcpetion(Long id) {
            super("No such explicador with id: " + id);
        }
    }

}
