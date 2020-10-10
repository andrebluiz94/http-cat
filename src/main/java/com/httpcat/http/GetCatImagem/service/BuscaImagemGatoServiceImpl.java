package com.httpcat.http.GetCatImagem.service;

import com.httpcat.generic.client.AbstractGetClient;
import com.httpcat.generic.config.HttpConfiguration;
import com.httpcat.http.GetCatImagem.config.BuscaImagemGatoConfiguration;
import com.httpcat.http.GetCatImagem.dto.RequestGatoImagem;
import com.httpcat.http.GetCatImagem.dto.ResponseRacaGatoImagem;
import org.springframework.web.client.RestTemplate;

public class BuscaImagemGatoServiceImpl
        extends AbstractGetClient<RequestGatoImagem, ResponseRacaGatoImagem, BuscaImagemGatoConfiguration>
        implements BuscaImagemGatoService {

    @Override
    public String buscar(RequestGatoImagem request) {
        this.configuration.setUrl(request.getExpand());
        return this.buscar(request);
    }
}
