package com.httpcat.util.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;
import java.util.Set;

@Component
public class ConfigurationCats extends ConfigurationImpl implements Configuration {

	@Value("${cat.url.racas.path.imagem}")
	private String imagem;

	@Value("${cat.url.racas.path.hats}")
	private String hats;

	@Value("${cat.url.racas.path.glasses}")
	private String glasses;


	public String getUrl(String racaGato, Map<String, String> raca, String categoria){
		Set<String> listParams = raca.keySet();
		UriComponentsBuilder urlBuilder = UriComponentsBuilder
				.fromHttpUrl(getUrlDominio.trim())
				.path(imagem.trim())
				.path(hats.trim().concat(categoria));
		for (String key : listParams) {
			urlBuilder.queryParam(key, raca.get(key));
		}
		urlBuilder.queryParam("breed_id",racaGato.trim());
		return urlBuilder.toUriString();
	}

	@Override
	public String getUrl( Map<String, String> raca, String categoria){
		Set<String> listParams = raca.keySet();
		UriComponentsBuilder urlBuilder = UriComponentsBuilder
				.fromHttpUrl(getUrlDominio.trim())
				.path(imagem.trim())
				.path(hats.trim().concat(categoria));
		for (String key : listParams) {
			urlBuilder.queryParam(key, raca.get(key));
		}
		return urlBuilder.toUriString();
	}

	@Override
	public String getUrl(Map<String, String> item, String s, String raca) {
		return null;
	}
}
