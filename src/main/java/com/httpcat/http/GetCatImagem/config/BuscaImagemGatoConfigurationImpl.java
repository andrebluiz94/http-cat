package com.httpcat.http.GetCatImagem.config;


import com.httpcat.generic.config.ConfigurationUrl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;

public class BuscaImagemGatoConfigurationImpl extends ConfigurationUrl implements BuscaImagemGatoConfiguration{

    @Value("${cat.url.racas.dominio}")
    private String dominio;

    @Value("${cat.url.racas.path.imagem.categories}")
    private String path;


    @Override
    public void setUrl(Map<String, String> expand) {
        this.url = UriComponentsBuilder
                .fromHttpUrl(dominio.trim())
                .path(path)
                .buildAndExpand(expand)
                .toUriString();
    }
}
