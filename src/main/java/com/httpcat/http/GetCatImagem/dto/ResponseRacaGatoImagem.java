package com.httpcat.http.GetCatImagem.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.httpcat.http.raca.dto.ResponseRacaGato;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@ToString
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseRacaGatoImagem {

    @JsonProperty("id")
    private String idRaca;

    @JsonProperty("url")
    private String url;

    @JsonProperty("categories")
    private List<categorioes> categoria;

    @JsonProperty("breeds")
    private List<ResponseRacaGato> raca;
}
