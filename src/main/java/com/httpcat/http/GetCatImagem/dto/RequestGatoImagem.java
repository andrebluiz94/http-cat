package com.httpcat.http.GetCatImagem.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Map;

@Getter
@Setter
@ToString
public class RequestGatoImagem {
    private Map<String, String> expand;
}
