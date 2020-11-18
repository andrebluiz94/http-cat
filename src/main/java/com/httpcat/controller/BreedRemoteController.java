package com.httpcat.controller;

import com.httpcat.http.raca.dto.ResponseRacaGato;
import com.httpcat.http.raca.service.BuscarRacasGatoRemoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/remote")
public class BreedRemoteController {

    private final BuscarRacasGatoRemoteService service;

    @Autowired
    public BreedRemoteController(BuscarRacasGatoRemoteService service) {
        this.service = service;
    }

    @GetMapping
    @Cacheable("todosGatosRemotos")
    public List<ResponseRacaGato> getRacas() {
        return service.buscar();
    }

    @PostMapping
    @CacheEvict(cacheNames = {"todosGatosRemotos", "todosGatos"})
    public ResponseEntity<List<ResponseRacaGato>> pupolarBaseDadosComGatos(){
        return service.buscaESalva();
    }

}
