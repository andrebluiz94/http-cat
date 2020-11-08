package com.httpcat.http.GetCatImagem.service;

import com.httpcat.http.GetCatImagem.dto.RequestGatoImagem;
import com.httpcat.http.GetCatImagem.dto.ResponseRacaGatoImagem;

import java.util.List;
import java.util.Optional;

public interface BuscaImagemGatoService {

    List<Optional<ResponseRacaGatoImagem>> buscar(RequestGatoImagem request);
    List<ResponseRacaGatoImagem> buscar();
}
