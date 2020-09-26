package com.httpcat.http.raca.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseRacaGato {

    @JsonProperty("id")
    private String catID;

    @JsonProperty("name")
    private String nome;

    @JsonProperty("temperament")
    private String temperamento;

    @JsonProperty("origin")
    private String origem;
}
