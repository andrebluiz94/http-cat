package com.httpcat.controller;

import com.httpcat.Entity.Cat;
import com.httpcat.http.raca.service.BuscarGatosLocalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/")
public class BreedLocalControllerImpl {

    private final BuscarGatosLocalService service;

    @Autowired
    public BreedLocalControllerImpl(BuscarGatosLocalService service) {
        this.service = service;
    }

    @GetMapping("/")
    @Cacheable("todosGatos")
    public ResponseEntity<List<Cat>> getRacas() {
        return service.getCats();
    }

    @GetMapping("/raca")
    public ResponseEntity<Optional<Cat>> getRaca(@RequestParam String nome) {
        return service.getCatByName(nome);
    }

    @GetMapping("/temperamento")
    public ResponseEntity<List<Cat>> getRacaByTemperamento(@RequestParam String temperamento) {
        return service.getCatByTemperamento(temperamento);
    }

    @GetMapping("/nomes")
    public ResponseEntity<List<Cat>> getRacaByNomes(@RequestParam List<String> lista) {
        return service.getCatsList(lista);
    }

}
