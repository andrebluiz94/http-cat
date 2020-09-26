package com.httpcat.controller.impl;

import com.httpcat.http.raca.dto.ResponseRacaGato;
import com.httpcat.http.GetCatImagem.dto.ResponseRacaGatoImagem;
import com.httpcat.http.raca.service.BuscarRacasGatoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1")
public class BreedControllerImpl {

    private final BuscarRacasGatoService service;

    public BreedControllerImpl(BuscarRacasGatoService service) {
        this.service = service;
    }

    @GetMapping("/racas")
    public List<ResponseRacaGato> getRacas() {
        return service.buscar();
    }

    @GetMapping("/imagem/chapeu")
    public List<ResponseRacaGatoImagem> getAndSalvem(){
        return service.getAndSave();
    }

}
