package com.httpcat.util.impl;

import com.httpcat.generic.config.HttpConfiguration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;
import java.util.Set;


public abstract class ConfigurationImpl implements HttpConfiguration {

	@Value("${cat.url.racas.dominio}")
	protected String getUrlDominio;

	@Value("${cat.url.racas.path}")
	protected String getUrlPath;

	@Value("${cat.url.racas.path.search-by-name}")
	protected String bucasRacaByName;

	protected static final String QUERY = "q";

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
		for (String key : listParams) {
			urlBuilder.queryParam(key, raca.get(key));
		}
		return urlBuilder.toUriString();
	}

	public String builderQueryParams(Map<String, String> raca, UriComponentsBuilder url) {
		Set<String> listParams = raca.keySet();
		for (String key : listParams) {
			url.queryParam(key, raca.get(key));
		}
		return url.toUriString();
	}
}
