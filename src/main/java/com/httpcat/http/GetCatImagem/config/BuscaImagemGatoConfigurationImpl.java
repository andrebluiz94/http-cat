package com.httpcat.http.GetCatImagem.config;


import com.httpcat.generic.config.ConfigurationUrl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.Map;

@Component
public class BuscaImagemGatoConfigurationImpl extends ConfigurationUrl implements BuscaImagemGatoConfiguration{

    @Value("${cat.url.racas.dominio}")
    private String dominio;

    @Value("${cat.url.racas.path.imagem}")
    private String path;

    private Map<String, String> params = new HashMap<>();

    @Override
    public void setUrlParams(Map<String, String> expand) {
        params.put("breed_id", expand.get("raca"));
        params.put("category_ids", expand.get("categoria"));
    }

    @Override
    public String getUrl() {
        this.url = buildUrl();
        return super.getUrl();
    }

    private String buildUrl(){
        return UriComponentsBuilder
                .fromHttpUrl(dominio.trim())
                .path(path)
                .buildAndExpand(params)
                .toUriString();
    }
}
