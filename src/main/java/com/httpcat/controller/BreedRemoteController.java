package com.httpcat.controller;

import com.httpcat.http.raca.dto.ResponseRacaGato;
import com.httpcat.http.raca.service.BuscarRacasGatoRemoteService;
import io.swagger.annotations.ApiOperation;
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
    @ApiOperation(value = "Retorna uma lista com todos os gatos da base de dados, para segunda chamada será retonado uma" +
            " lista armazena em cache de memoria")
    public List<ResponseRacaGato> getRacas() {
        return service.buscar();
    }

    @PostMapping
    @CacheEvict(cacheNames = {"todosGatosRemotos", "todosGatos"})
    @ApiOperation(value = "Retorna uma lista com todos os gatos da base de dados, popula a base de dados e reseta o cache das rotas")
    public ResponseEntity<List<ResponseRacaGato>> pupolarBaseDadosComGatos(){
        return service.buscaESalva();
    }

}
