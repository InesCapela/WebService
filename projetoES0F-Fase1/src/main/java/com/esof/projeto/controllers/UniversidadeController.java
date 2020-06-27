package com.esof.projeto.controllers;

import com.esof.projeto.models.Universidade;
import com.esof.projeto.services.UniversidadeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/universidade")
public class UniversidadeController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UniversidadeService universidadeService;

    @Autowired
    public UniversidadeController(UniversidadeService universidadeService) {
        this.universidadeService = universidadeService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Iterable<Universidade>> getAllUniversidades() {
        this.logger.info("Received a get request");
        return ResponseEntity.ok(this.universidadeService.findAll());
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Universidade> createUniversidade(@RequestBody Universidade universidade) {

        Optional<Universidade> universidadeOptional = this.universidadeService.createUniversidade(universidade);
        if (universidadeOptional.isPresent()) {
            return ResponseEntity.ok(universidadeOptional.get());
        }
        throw new UniversidadeAlreadyExistsExcpetion(universidade.getName());
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Universidade ja existe")
    private class UniversidadeAlreadyExistsExcpetion extends RuntimeException {

        public UniversidadeAlreadyExistsExcpetion(String name) {
            super("A universidade with name: " + name + " already exists");
        }
    }
}
