package com.httpcat.http.raca.service;

import com.httpcat.http.raca.dto.ResponseRacaGato;
import com.httpcat.http.GetCatImagem.dto.ResponseRacaGatoImagem;

import java.util.List;

public interface BuscarRacasGatoService {
    List<ResponseRacaGato> buscar();

    List<ResponseRacaGatoImagem> getAndSave();
}
