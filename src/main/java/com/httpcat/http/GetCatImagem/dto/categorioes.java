package com.httpcat.http.GetCatImagem.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class categorioes {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("name")
    private String nome;
}
