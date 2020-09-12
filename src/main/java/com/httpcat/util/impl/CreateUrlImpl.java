package com.httpcat.util.impl;

import com.httpcat.util.CreateUrl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Component
public class CreateUrlImpl implements CreateUrl {

	@Value("${cat.url.racas.dominio}")
	private String getUrlDominio;

	@Value("${cat.url.racas.path}")
	private String getUrlPath;

	@Value("${cat.url.racas.path.search-by-name}")
	private String bucasRacaByName;

	private static final String NAME_QUERY = "name";

	@Override
	public String getUrl() {
		UriComponentsBuilder url = UriComponentsBuilder
				.fromHttpUrl(getUrlDominio.trim())
				.path(getUrlPath.trim());
		return url.toUriString();
	}

	@Override
	public String getUrl(String buscaPeloNome) {
		UriComponentsBuilder url = UriComponentsBuilder
				.fromHttpUrl(getUrlDominio.trim())
				.path(bucasRacaByName.trim())
				.queryParam(NAME_QUERY, buscaPeloNome.trim());
		return url.toUriString();
	}

	@Override
	public String getUrl(Map<String, String> raca) {
		List racaLista = Arrays.asList(raca.keySet());
		UriComponentsBuilder urlBuilder = UriComponentsBuilder
				.fromHttpUrl(getUrlDominio.trim())
				.path(getUrlPath.trim());
		racaLista.stream().forEach((v) -> urlBuilder.queryParam(String.valueOf(v), raca.get(v)));
		return urlBuilder.toUriString();
	}
}
