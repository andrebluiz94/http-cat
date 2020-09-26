package com.httpcat.http.GetCatImagem.service;

import com.httpcat.generic.client.AbstractGetClient;
import com.httpcat.generic.config.HttpConfiguration;
import com.httpcat.http.GetCatImagem.config.BuscaImagemGatoConfiguration;
import org.springframework.web.client.RestTemplate;

public class BuscaImagemGatoServiceImpl extends AbstractGetClient implements BuscaImagemGatoService {

    public BuscaImagemGatoServiceImpl(RestTemplate restTemplate, HttpConfiguration configuration) {
        super(restTemplate, configuration);
    }
}
