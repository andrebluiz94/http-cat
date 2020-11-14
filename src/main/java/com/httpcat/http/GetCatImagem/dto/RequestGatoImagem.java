package com.httpcat.http.GetCatImagem.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;


@Getter
@Setter
public class RequestGatoImagem {
	private Map<String, String> expand;
}
