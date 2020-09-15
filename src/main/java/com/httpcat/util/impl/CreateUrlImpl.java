package com.httpcat.util.impl;

import com.httpcat.util.CreateUrl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Component
public class CreateUrlImpl implements CreateUrl {

	@Value("${cat.url.racas.dominio}")
	private String getUrlDominio;

	@Value("${cat.url.racas.path}")
	private String getUrlPath;

	@Value("${cat.url.racas.path.search-by-name}")
	private String bucasRacaByName;

	private static final String NAME_QUERY = "name";
	private static final String QUERY = "q";

	@Override
	public String getUrl() {
		UriComponentsBuilder url = UriComponentsBuilder
				.fromHttpUrl(getUrlDominio.trim())
				.path(getUrlPath.trim());
		return url.toUriString();
	}

	@Override
	public String getUrl(String buscaPeloNome) {
		String origin = UriComponentsBuilder
				.fromHttpUrl(getUrlDominio.trim())
				.path(bucasRacaByName.trim())
				.queryParam(QUERY, buscaPeloNome.trim())
				.toUriString();
		System.out.println(origin);
		return origin;
	}

	@Override
	public String getUrl(Map<String, String> raca) {
		Set<String> listParams = raca.keySet();
		UriComponentsBuilder urlBuilder = UriComponentsBuilder
				.fromHttpUrl(getUrlDominio.trim())
				.path(getUrlPath.trim());
		for (String key : listParams){
			urlBuilder.queryParam(key, raca.get(key));
		}
		return urlBuilder.toUriString();
	}
}
