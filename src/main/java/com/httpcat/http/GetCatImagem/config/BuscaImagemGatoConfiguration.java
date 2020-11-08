package com.httpcat.http.GetCatImagem.config;

import com.httpcat.generic.config.HttpConfiguration;

import java.util.Map;

public interface BuscaImagemGatoConfiguration extends HttpConfiguration {
    void setUrlParams(Map<String, String> expand);
}
