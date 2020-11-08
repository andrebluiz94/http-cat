package com.httpcat.http.GetCatImagem.dto;

import lombok.Data;

import java.util.Map;


@Data
public class RequestGatoImagem {
	private Map<String, String> expand;
}
