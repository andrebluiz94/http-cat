package com.httpcat.http.GetCatImagem.service;

import com.httpcat.http.GetCatImagem.dto.RequestGatoImagem;

public interface BuscaImagemGatoService {

    String buscar(RequestGatoImagem request);
}
