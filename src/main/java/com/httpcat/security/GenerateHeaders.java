package com.httpcat.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.util.UriComponentsBuilder;

public abstract class GenerateHeaders implements HeaderDeafault{

	@Value("${cat.url.racas.path}")
	private String getUrlPath;

	@Value("${cat.url.racas.dominio}")
	private String getUrlDominio;

	@Value("${cat.authrization.key}")
	private String authorizationKey;

	@Value("${cat.url.racas.path.search-by-name}")
	private String bucasRacaByName;

	private static final String NAME_QUERY = "name";

	@Override
	public String getUrl(){
		UriComponentsBuilder url = UriComponentsBuilder
				.fromHttpUrl(getUrlDominio.trim())
				.path(getUrlPath.trim());
		return url.toUriString();
	}

	@Override
	public HttpEntity getRequest(){
		HttpHeaders httpHeaders = new HttpHeaders();
		getRequestAuthorization(httpHeaders);
		return new HttpEntity(httpHeaders);
	}

	public String getUrl(String buscaPeloNome){
		UriComponentsBuilder url = UriComponentsBuilder
				.fromHttpUrl(getUrlDominio.trim())
				.path(bucasRacaByName.trim())
				.queryParam(NAME_QUERY,buscaPeloNome.trim());
		return url.toUriString();
	}

	private HttpHeaders getRequestAuthorization(HttpHeaders httpHeaders){
		httpHeaders.add("x-api-key", authorizationKey.trim());
		return httpHeaders;
	}
}
