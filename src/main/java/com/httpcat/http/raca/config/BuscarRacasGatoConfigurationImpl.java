package com.httpcat.http.raca.config;


import com.httpcat.http.generic.config.ConfigurationUrl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class BuscarRacasGatoConfigurationImpl extends ConfigurationUrl implements BuscarRacasGatoConfiguration {

    @Value("${cat.url.racas.dominio}")
    private String dominio;

    @Value("${cat.url.racas.path}")
    private String path;


    @Override
    public String getUrl() {
        this.url = UriComponentsBuilder
                .fromHttpUrl(dominio.trim())
                .path(path.trim())
                .toUriString();
        return super.getUrl();
    }

}
